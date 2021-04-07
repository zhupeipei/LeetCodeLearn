package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test01 {
    public static void main(String[] args) {
        HashMap<Key, String> hm = new HashMap<>();
        hm.put(new Key(1), "1");
        hm.put(new Key(2), "2");
        hm.put(new Key(3), "3");
        hm.put(new Key(4), "4");
        hm.put(new Key(5), "5");
        hm.put(new Key(6), "6");
        hm.put(new Key(7), "7");
        hm.put(new Key(8), "8");
        hm.put(new Key(9), "9");
        hm.put(new Key(10), "10");
        hm.put(new Key(11), "11");
    }
}
