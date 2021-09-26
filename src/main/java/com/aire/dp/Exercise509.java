package com.aire.dp;

/**
 * Created on 2021/9/26 10:01 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise509 {
    public static void main(String[] args) {
        int res = new Exercise509().fib(4);
        System.out.println(res);
    }

    // 509. 斐波那契数
    // F(0) = 0，F(1) = 1
    // F(n) = F(n - 1) + F(n - 2)，其中 n > 1
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}
