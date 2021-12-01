package com.aire;

import java.util.*;

/**
 * @author ZhuPeipei
 * @date 2021/12/1 10:13
 */
public class Exercise387 {
    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(new Exercise387().firstUniqChar(s));
    }

    // 387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer val = map.get(ch);
            if (val == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, val + 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
