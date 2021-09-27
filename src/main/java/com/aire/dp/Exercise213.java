package com.aire.dp;

/**
 * Created on 2021/9/27 9:50 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise213 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2};
        int res = new Exercise213().rob(nums);
        System.out.println(res);
    }

    // 213. 打家劫舍 II
    // 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着
    // 第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
    // 被小偷闯入，系统会自动报警 。
    // 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] nums1 = new int[len - 1];
        System.arraycopy(nums, 0, nums1, 0, len - 1);
        int res1 = maxRob(nums1);

        System.arraycopy(nums, 1, nums1, 0, len - 1);
        int res2 = maxRob(nums1);
        return Math.max(res1, res2);
    }

    private int maxRob(int[] nums) {
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
