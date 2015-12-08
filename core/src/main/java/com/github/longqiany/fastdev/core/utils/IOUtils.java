package com.github.longqiany.fastdev.core.utils;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.charset.Charset;

/**
 * Created by zzz on 12/8/15.
 */
public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;
    private static final int EOF = -1;


    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        File fileInCanonicalFile = null;
        if (file.getParent() == null) {
            fileInCanonicalFile = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalFile = new File(canonicalDir, file.getName());
        }
        return !fileInCanonicalFile.getCanonicalFile().equals(fileInCanonicalFile.getAbsoluteFile());
    }

    public static long sizeOf(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        if (file.isDirectory()) {
            return sizeOfDirectory(file);
        } else {
            return file.length();
        }
    }

    private static long sizeOfDirectory(File directory) {
        final File[] files = directory.listFiles();
        if (files == null) {
            return 0L;
        }
        long size = 0;
        for (final File file : files) {
            try {
                if (!isSymlink(file)) {
                    size += sizeOf(file);
                    if (size < 0) {
                        break;
                    }
                }
            } catch (IOException ignored) {
            }
        }
        return size;
    }

    public static boolean delete(File file) {
        if (file == null) {
            return true;
        }
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                delete(f);
            }
        }
        return file.delete();
    }

    public static byte[] readBytes(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    public static long copyLarge(InputStream input, OutputStream output, byte[] buffer)
            throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static String readString(File file, Charset charset) throws IOException {
        if (file == null || !file.isFile()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bReader;

        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), charset);
        bReader = new BufferedReader(isr);
        String s;
        while ((s = bReader.readLine()) != null) {
            if (!sb.toString().equals("")) {
                sb.append("\r\n");
            }
            sb.append(s);
        }
        bReader.close();
        return sb.toString();
    }

    public static boolean writeString(String filePath, String content, boolean append) throws IOException {
        return writeString(filePath != null ? new File(filePath) : null, content, append);
    }

    public static boolean writeString(File file, String content) throws IOException {
        return writeString(file, content, false);
    }

    public static boolean writeString(File file, String content, boolean append) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } finally {
            closeQuietly(fileWriter);
        }
    }

    public static boolean writeBytes(File file, byte[] data) throws IOException {
        return writeBytes(file, data, false);
    }

    public static boolean writeBytes(File file, byte[] data, boolean append) throws IOException {
        OutputStream o = null;

        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            o.write(data);
            o.flush();
            return true;
        }finally {
            closeQuietly(o);
        }
    }


    public static boolean writeStream(File file, InputStream stream) throws IOException {
        return writeStream(file, stream, false);
    }

    /**
     * write file
     *
     * @param file   the file to be opened for writing.
     * @param stream the input stream
     * @param append if <code>true</code>, then bytes will be written to the end of the file rather than the beginning
     * @return return true
     * @throws IOException if an error occurs while operator FileOutputStream
     */
    public static boolean writeStream(File file, InputStream stream, boolean append) throws IOException {
        OutputStream os = null;
        try {
            makeDirs(file.getAbsolutePath());
            os = new FileOutputStream(file, append);
            byte[] data = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                os.write(data, 0, length);
            }
            os.flush();
            return true;
        } finally {
            closeQuietly(os);
            closeQuietly(stream);
        }

    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isNull(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory() || folder.mkdirs());
    }


    public static String getFolderName(String filePath) {
        if (StringUtils.isNull(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }


    public static void closeQuietly(Reader input) {
        closeQuietly((Closeable) input);
    }

    public static void closeQuietly(Writer output) {
        closeQuietly((Closeable) output);
    }

    public static void closeQuietly(InputStream input) {
        closeQuietly((Closeable) input);
    }

    public static void closeQuietly(OutputStream output) {
        closeQuietly((Closeable) output);
    }

    // read readString
    //-----------------------------------------------------------------------

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ignored) {
            // ignore
        }
    }

    public static void closeQuietly(Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException ignored) {
                // ignored
            }
        }
    }

    public static void closeQuietly(Selector selector) {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException ignored) {
                // ignored
            }
        }
    }

    public static void closeQuietly(ServerSocket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException ignored) {
                // ignored
            }
        }
    }




    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pumpStream(in, out, null);
        return out.toByteArray();
    }

    public static void writeByteArrayToFile(File file, byte[] bytes) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        try {
            fout.write(bytes);
        } finally {
            fout.close();
        }
    }

    public static void copyFile(File src, File dest) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

    }

    public interface DataProgressCallback {
        public void onDataProgress(int bytesWritten);
    }

    public static void pumpStream(InputStream in, OutputStream out, DataProgressCallback callback) throws IOException {
        byte[] buff = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buff, 0, buff.length)) != -1) {
            out.write(buff, 0, bytesRead);
            if (callback != null) {
                callback.onDataProgress(bytesRead);
            }
        }
        out.close();
        in.close();
    }

    public static abstract class BackgroundTask implements Runnable {
        public Handler mHandler = new Handler();

        public abstract void doInBackground();

        public void onFinished() {

        }

        public final void run() {
            doInBackground();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFinished();
                }
            });
        }

    }

    public static abstract class ProgressBackgroundTask extends BackgroundTask {

        public interface ProgressListener {
            public void onProgress(ProgressBackgroundTask task, int value, int max, String description);
        }

        private ProgressListener mListener;

        public void setProgressListener(ProgressListener listener) {
            mListener = listener;
        }

        public void postProgress(final int value, final int max, final String description) {
            if (mListener != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onProgress(ProgressBackgroundTask.this, value, max, description);
                    }
                });
            }
        }
    }

    public static InputStream getPhoneLogs() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("logcat", "-d");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        //process.waitFor();
        return process.getInputStream();
    }
}
