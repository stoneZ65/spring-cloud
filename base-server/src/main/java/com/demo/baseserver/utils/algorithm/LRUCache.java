package com.demo.baseserver.utils.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: LRU (最近最少使用) 缓存 约束的数据结构
 * @author: zhanglei
 * @date: 2022/9/9 17:28
 **/
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    int cap;

    public LRUCache(int cap) {
        super(16, 0.75f, false);
        this.cap = cap;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.cap;
    }

}
