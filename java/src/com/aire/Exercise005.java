package com.aire;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Exercise005 {
    public static void main(String[] args) {
        System.out.println(new Exercise005().longestPalindrome("ccc"));
    }

    // 最长回文子串
    // 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    /**
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     **/

    // 题解思路：
    // 采用dp方法 定义dp[i]为从i开始的最大回文子串
    // 遍历过程中 dp[i+1] 是基于dp[i] 的子串 去掉开始的字符之后的最大子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> chs = new HashMap<>();
        int[] dp = new int[s.length()];
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            Character chi = s.charAt(i);
            if (i != 0) {
                Character chii = s.charAt(i - 1);
                int num = chs.get(chii);
                if (num == 1) {
                    chs.remove(chii);
                } else {
                    chs.put(chii, num - 1);
                }
                len--;
            } else {
                chs.put(s.charAt(0), 1);
                len = 1;
                dp[0] = 1;
            }
            int num = chs.get(chi);
            if (num > 1) {
                int j = i + 1;
                for (; j < s.length(); j++) {
                    Character chj = s.charAt(j);
                    if (chj == chi) {
                        dp[i] = j - i + 1;
                        break;
                    }
                }
            } else {
                int j = i + len;
                for (; j < s.length(); j++) {
                    Character chj = s.charAt(j);
                    int num1 = chs.containsKey(chj) ? chs.get(chj) : 0;
                    chs.put(chj, num1 + 1);
                    len++;
                    if (chj == chi) {
                        dp[i] = j - i + 1;
                        break;
                    }
                }
//                if (j == s.length()) {
//                    break;
//                }
            }
        }
        int index = 0;
        for (int l = 0; l < dp.length; l++) {
            if (dp[l] > dp[index]) {
                index = l;
            }
        }
        return s.substring(index, dp[index] + index);
    }
}
