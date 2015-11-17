package com.github.longqiany.fastdev.core.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzz on 11/16/15.
 */
public class LRUCache<K, T> extends LinkedHashMap<K, T> {

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, T> eldest) {
        return size() >= cacheSize;
    }
}
