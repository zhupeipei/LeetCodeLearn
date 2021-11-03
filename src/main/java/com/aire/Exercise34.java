package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/3 15:11
 */
public class Exercise34 {
    public static void main(String[] args) {
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 8;
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 6;
        int[] nums = {};
        int target = 6;
        new Exercise34().searchRange(nums, target);
    }

    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int leftIndex = binarySearch(nums, target, false);
        int rightIndex = binarySearch(nums, target, true);
        if (leftIndex == -1 || rightIndex == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{leftIndex, rightIndex};
    }

    private int binarySearch(int[] nums, int target, boolean bigger) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (bigger) {
                // 找值右端
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    left = mid;
                    if (right - left == 1) {
                        // 防止死循环
                        return nums[right] == target ? right : left;
                    }
                }
            } else {
                // 找值左端
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
