package com.aire;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhuPeipei
 * @date 2021/11/15 20:49
 */
public class Exercise003_1 {
    public static void main(String[] args) {
        String s = "dvdf";
//        String s = "abcabcbb";
        System.out.println(new Exercise003_1().lengthOfLongestSubstring(s));
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxNum = 0;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            int curLen = set.size();
            set.add(ch);
            int size = set.size();
            if (curLen != size) {
                if (maxNum < size) {
                    maxNum = size;
                }
            } else {
                int j = i;
                set.clear();
                while (true) {
                    set.add(s.charAt(j));
                    j--;
                    if (j < 0 || s.charAt(j) == ch) {
                        break;
                    }
                }
            }
        }
        return maxNum;
    }
}
