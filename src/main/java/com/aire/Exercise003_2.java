package com.aire;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhuPeipei
 * @date 2021/11/26 23:45
 */
public class Exercise003_2 {
    public static void main(String[] args) {
        String s = "abcacbcbb";
//        String s = "pwwkew";
        int res = new Exercise003_2().lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int right = 0;
        int maxNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (right < s.length()) {
                Character ch = s.charAt(right);
                if (!set.contains(ch)) {
                    set.add(ch);
                    right++;
                } else {
                    // 这里right字符需要for循环一直remove到不contains才可以
                    break;
                }
            }
            maxNum = Math.max(maxNum, set.size());
            if (right == s.length() - 1) {
                break;
            }
        }
        return maxNum;
    }
}
