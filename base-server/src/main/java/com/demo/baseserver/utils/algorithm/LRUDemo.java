package com.demo.baseserver.utils.algorithm;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/9/29 16:22
 **/
public class LRUDemo {

    class Node {
        private String key;

        private Object value;

        private Node pre;

        private Node next;

        private Node(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        private Node() {
        }
    }

    private int cacheSize;

    private int currentSize;

    private Node first;

    private Node last;

    private HashMap<String, Node> hashMap;

    public LRUDemo(int cacheSize) {
        this.cacheSize = cacheSize;
        this.currentSize = 0;

        hashMap = new HashMap<>();
        first = new Node();
        last = new Node();

        first.pre = null;
        first.next = last;

        last.pre = first;
        last.next = null;
    }

    public Object get(String key) {
        Node node = hashMap.get(key);
        if (null == node) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(String key, Object object) {
        Node node = hashMap.get(key);
        if (null != node) {
            node.value = object;
            moveToHead(node);
            return;
        }


        Node newNode = new Node(key, object);
        addNode(newNode);
        hashMap.put(key, newNode);
        ++currentSize;

        if (currentSize > cacheSize) {
            Node last = this.popTail();
            this.hashMap.remove(last.key);
            --currentSize;
        }

        StringBuilder sb = new StringBuilder();
        Node pnode = first;
        while (pnode != null) {
            sb.append(String.format("%s:%s ", pnode.key, pnode.value));
            pnode = pnode.next;
        }
        System.out.println(sb.toString());
    }

    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    private void addNode(Node node) {
        node.pre = first;
        node.next = first.next;

        first.next.pre = node;
        first.next = node;
    }

    private void moveToHead(Node node) {
        if (null == first || null == last) {
            first = node;
            last = node;
            return;
        }
        if (first == node) {
            return;
        }
        if (null != node.next) {
            node.next.pre = node.pre;
        }
        if (null != node.pre) {
            node.pre.next = node.next;
        }
        if (last == node) {
            last = node.pre;
        }
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }

    // pop the current tail.
    private Node popTail() {
        Node res = last.pre;
        this.removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        LRUDemo demo = new LRUDemo(2);
        for (int i = 0; i < 3; i++) {
            demo.put("key" + i, i);
        }
    }

}
