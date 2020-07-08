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
    // 采用dp方法 定义dp[i,j] 表示 i 到 j 是否是 回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            Character chi = s.charAt(i);
            for (int j = len - 1; j >= i; j--) {
                Character chj = s.charAt(j);
                if (j - i <= 2) {
                    dp[i][j] = chj == chi;
                } else {
                    dp[i][j] = (chj == chi) && dp[i + 1][j - 1];
                }
            }
        }
        int left = 0, right = 0, max = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                if (dp[i][j]) {
                    if (max < j - i + 1) {
                        left = i;
                        right = j;
                        max = j - i + 1;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
