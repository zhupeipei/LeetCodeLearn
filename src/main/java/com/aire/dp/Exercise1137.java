package com.aire.dp;

/**
 * Created on 2021/9/26 10:06 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise1137 {
    public static void main(String[] args) {
        int res = new Exercise1137().tribonacci(25);
        System.out.println(res - 1389537);
    }

    // 1137. 第 N 个泰波那契数
    // 泰波那契序列Tn定义如下：
    // T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int[] t = new int[n + 1];
        t[0] = 0;
        t[1] = t[2] = 1;
        for (int i = 3; i <= n; i++) {
            t[i] = t[i - 1] + t[i - 2] + t[i - 3];
        }
        return t[n];
    }
}
