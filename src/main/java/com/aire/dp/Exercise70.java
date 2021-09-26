package com.aire.dp;

/**
 * Created on 2021/9/26 10:11 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise70 {
    public static void main(String[] args) {
        int res = new Exercise70().climbStairs(3);
        System.out.println(res);
    }

    // 70. 爬楼梯
    // 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    // 注意：给定 n 是一个正整数。
    // f(n) = f(n-1) + 1 / f(n-2) + 2
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
}
