package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/15 23:36
 */
public class Exercise053 {
    public static void main(String[] args) {
        int[] nums = {-1};
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new Exercise053().maxSubArray(nums));
    }

    // 53. 最大子序和
    public int maxSubArray(int[] nums) {
        int[] res = new int[nums.length]; // 代表以i结束的最大子串的和
        for (int i = 0; i < nums.length; i++) {
            int preRes = i > 0 ? res[i - 1] : 0;
            if (preRes < 0) {
                res[i] = nums[i];
            } else {
                res[i] = nums[i] + preRes;
            }
        }
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < res.length; i++) {
            maxNum = Math.max(res[i], maxNum);
        }
        return maxNum;
    }
}
