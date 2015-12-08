package com.github.longqiany.fastdev.core.cache;

import android.content.Context;

import com.github.longqiany.fastdev.core.io.SafaFileNameGenerator;
import com.github.longqiany.fastdev.core.utils.IOUtils;
import com.github.longqiany.fastdev.core.utils.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by zzz on 12/7/15.
 */
public class DiskCache implements IDiskCache {

    private Context mContext;
    public static final String DIR_NAME_DEFAULT = ".disc";
    public String mCacheDirName;
    File mCacheFile;
    public static final int MODE_INTERNAL = 0;
    public static final int MODE_EXTERNAL = 1;
    public static final int MODE_AUTO = 2;
    private Charset utf8 = Charset.forName("UTF-8");
    SafaFileNameGenerator mGenerator = new SafaFileNameGenerator();
    public DiskCache(Context context) {
        this(context, DIR_NAME_DEFAULT);
    }

    public DiskCache(Context context ,String dirName) {
        this(context, dirName, MODE_AUTO);

    }

    public DiskCache(Context context, String dirName, int mode) {
        mContext = context;
        creatCache(dirName, mode);
    }

    private void creatCache(String dirName, int mode) {
        if (dirName == null) {
            mCacheDirName = DIR_NAME_DEFAULT;
        } else {
            mCacheDirName = dirName;
        }
        checkExist(mCacheDirName, mode);
    }

    private void checkExist(String dirName, int mode) {
        if (mCacheFile == null) {
            mCacheFile = new File(getBaseCacheDir(mode), dirName);
        }
        if (!mCacheFile.exists()) {
            mCacheFile.mkdirs();
        }
    }

    @Override
    public void put(String key, byte[] bytes) {
        try {
            IOUtils.writeBytes(getFile(key), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, InputStream stream) {

        try {
            IOUtils.writeStream(getFile(key), stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, String string) {
        try {
            IOUtils.writeString(getFile(key), string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        try {
            return IOUtils.readString(getFile(key),utf8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File getFile(String key) {
        return getCacheFile(key);
    }

    @Override
    public byte[] getByte(String key) {
        File file = getFile(key);
        try {
            return IOUtils.readBytes(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(String key) {
        File file = getFile(key);
        return IOUtils.delete(file);
    }

    @Override
    public boolean clear() {
        return IOUtils.delete(mCacheFile);
    }

    @Override
    public int delete(FileFilter filter) {
        File cacheFile = getCacheDir();
        File[] files = cacheFile.listFiles();
        if (files == null || files.length == 0) {
            return 0;
        }
        int count = 0;
        for (File file : files) {
            if (filter.accept(file)) {
                count++;
                file.delete();
            }
        }
        return count;
    }

    @Override
    public File getCacheDir() {
        return mCacheFile;
    }

    @Override
    public long getCacheSize() {
        return IOUtils.sizeOf(getCacheDir());
    }

    private File getBaseCacheDir(int mode) {
        File baseCacheDir;
        switch (mode) {
            case MODE_INTERNAL: {
                baseCacheDir = mContext.getCacheDir();
            }
            break;
            case MODE_EXTERNAL: {
                baseCacheDir = mContext.getExternalCacheDir();
            }
            break;
            case MODE_AUTO:
            default: {
                if (Utils.checkSDCard()) {
                    baseCacheDir = mContext.getExternalCacheDir();
                } else {
                    baseCacheDir = mContext.getCacheDir();
                }
                break;
            }
        }
        return baseCacheDir;
    }

    private File getCacheFile(String key) {

        String fileName = mGenerator.generate(key);
        return new File(mCacheFile, fileName);
    }
}
