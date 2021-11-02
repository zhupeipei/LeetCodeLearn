package com.aire;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created on 2021/11/2 上午12:33.
 *
 * @Author ZhuPeipei
 */
public class ExerciseLruCache {
    private LinkedHashMap<Integer, Integer> mLruCache;
    private int mCapacity = Integer.MAX_VALUE;

    public ExerciseLruCache(int capacity) {
        mCapacity = capacity;
        mLruCache = new LinkedHashMap(0, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > mCapacity;
            }
        };
    }

    public int get(int key) {
        Integer val = mLruCache.get(key);
        if (val != null) {
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        mLruCache.put(key, value);
    }
}
