package com.github.longqiany.fastdev.core.cache;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;

/**
 * Created by zzz on 12/7/15.
 */
public interface IDiskCache {

    void put(String key, byte[] bytes);
    void put(String key, InputStream stream);
    void put(String key, String string);

    String get(String key);
    File getFile(String key);
    byte[] getByte(String key);

    boolean remove(String key);

    boolean clear();

    int delete(FileFilter filter);

    File getCacheDir();

    long getCacheSize();


}
