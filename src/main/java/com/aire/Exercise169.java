package com.aire;

import java.util.Arrays;

/**
 * @author ZhuPeipei
 * @date 2021/12/2 11:24
 */
public class Exercise169 {
    public static void main(String[] args) {

    }

    // 169. 多数元素
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
