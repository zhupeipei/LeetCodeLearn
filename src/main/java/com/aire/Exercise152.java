package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/12/2 10:27
 */
public class Exercise152 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println(new Exercise152().maxProduct(nums));
    }

    // 152. 乘积最大子数组
    public int maxProduct(int[] nums) {
        // dp[i] 表示包含自身的最大值/最小值
        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i][0] = nums[0];
                dp[i][1] = nums[0];
            } else {
                int val1 = nums[i] * dp[i - 1][0];
                int val2 = nums[i] * dp[i - 1][1];
                int val3 = nums[i];
                dp[i][0] = Math.max(Math.max(val1, val2), val3);
                dp[i][1] = Math.min(Math.min(val1, val2), val3);
            }
        }
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            maxNum = Math.max(dp[i][0], maxNum);
        }
        return maxNum;
    }
}
