package com.aire;

import java.util.Arrays;
import java.util.HashMap;

public class Execise001 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Execise001().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer val = map.get(target - nums[i]);
            if (val != null && val != i) {
                return new int[]{i, val};
            }
        }
        return null;
    }
}
