package com.aire.queue;

/**
 * Created on 2021/9/15 8:35 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise003_offical2 {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(new Exercise003_offical2().numSquares(n));
    }

    // 1 <= n <= 10^4
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
