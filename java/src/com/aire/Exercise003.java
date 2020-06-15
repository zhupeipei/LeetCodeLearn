package com.aire;

import java.util.HashSet;
import java.util.Set;

public class Exercise003 {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("dvdf"));
    }

    // 基于查找地方法 可以采用hashmap会更加快速
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int n = s.length();
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                char ch = s.charAt(i);
                int j = i - 1;
                boolean flag = false;
                for (; j >= i - dp[i - 1]; j--) {
                    char c = s.charAt(j);
                    if (c == ch) {
                        flag = true;
                        dp[i] = i - j;
                        break;
                    }
                }
                if (!flag) {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            int max = 1;
            for (int i = 0; i < n; i++) {
                if (max < dp[i]) {
                    max = dp[i];
                }
            }
            return max;
        }
    }
}