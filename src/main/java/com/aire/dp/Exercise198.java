package com.aire.dp;

/**
 * Created on 2021/9/27 9:36 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise198 {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 1};
        int[] nums = new int[]{2, 1, 1, 2};
        int res = new Exercise198().rob(nums);
        System.out.println(res);
    }

    // 198. 打家劫舍
    // 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    // 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    // 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] money = new int[len];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            money[i] = Math.max(money[i - 1], money[i - 2] + nums[i]);
        }
        return money[len - 1];
    }
}
