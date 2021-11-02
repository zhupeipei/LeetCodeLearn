package com.aire;

/**
 * Created on 2021/11/2 上午1:41.
 *
 * @Author ZhuPeipei
 */
public class Exercise153_1 {
    public static void main(String[] args) {
//        int[] nums = {11, 13, 15, 17};
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(new Exercise153_1().findMin(nums));
    }

    // 153. 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int num1 = nums[i];
            int num2 = nums[i + 1];
            if (num1 > num2) {
                return num2;
            }
        }
        return nums[0];
    }
}
