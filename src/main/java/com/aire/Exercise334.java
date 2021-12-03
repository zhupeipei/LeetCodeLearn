package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/12/3 10:24
 */
public class Exercise334 {
    public static void main(String[] args) {
        int[] nums = {3, 5, 1, 6};
        System.out.println(new Exercise334().increasingTriplet(nums));
    }

    // 334. 递增的三元子序列
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }
        int minVal = Integer.MAX_VALUE;
        int middleVal = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val > middleVal) {
                return true;
            } else if (val < minVal) {
                minVal = val;
            } else if (val < middleVal && val > minVal) {
                middleVal = val;
            } else {
                // val == middleVal || val == minVal
                continue;
            }
        }
        return false;
    }
}
