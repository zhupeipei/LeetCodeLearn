package com.aire;

public class Exercise005Official1 {
    public static void main(String[] args) {
        System.out.println(new Exercise005Official1().longestPalindrome("babad"));
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
        int maxLeft = 0, maxRight = 1, maxLen = 0;
        for (int i = 0; i < len; i++) {
            // 取间隙位置 查看 是否 有回文
            if (i != 0) {
                int left = i - 1, right = i;
                while (true) {
                    if (left < 0 || right >= len) {
                        break;
                    }
                    Character chl = s.charAt(left);
                    Character chr = s.charAt(right);
                    if (chl == chr) {
                        left--;
                        right++;
                        continue;
                    } else {
                        break;
                    }
                }
                if (right - left - 1 > maxLen) {
                    maxLeft = left + 1;
                    maxRight = right - 1;
                    maxLen = right - left - 1;
                }
            }
            int left = i - 1, right = i + 1;
            while (true) {
                if (left < 0 || right >= len) {
                    break;
                }
                Character chl = s.charAt(left);
                Character chr = s.charAt(right);
                if (chl == chr) {
                    left--;
                    right++;
                    continue;
                } else {
                    break;
                }
            }
            if (right - left - 1 > maxLen) {
                maxLeft = left + 1;
                maxRight = right - 1;
                maxLen = right - left - 1;
            }
        }
        return s.substring(maxLeft, maxRight + 1);
    }
}
