package com.aire.dp;

/**
 * Created on 2021/9/26 10:19 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise746 {
    public static void main(String[] args) {
//        int[] cost = new int[]{10, 15, 20};
        int[] cost = new int[]{1, 100};
//        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int res = new Exercise746().minCostClimbingStairs(cost);
        System.out.println(res);
    }

    // 746. 使用最小花费爬楼梯
    // 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
    // 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
    // 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
    // f(n) = min(f(n-1),f(n-2))
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return 0;
        } else if (len == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int[] f = new int[len + 1];
        f[0] = f[1] = 0;
        for (int i = 2; i < len + 1; i++) {
            f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
        }
        return f[len];
    }
}
