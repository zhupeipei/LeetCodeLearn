package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/15 21:46
 */
public class Exercise912 {
    public static void main(String[] args) {
//        int[] nums = {5, 2, 3, 1};
        int[] nums = {5, 1, 1, 2, 0, 0};
        System.out.println(new Exercise912().sortArray(nums));
    }

    // 912. 排序数组
    // 快速排序
    public int[] sortArray(int[] nums) {
        sortArr(nums, 0, nums.length);
        return nums;
    }

    private void sortArr(int[] nums, int left, int right) {
        int index = findAndSetBaseIndex(nums, left, right);
        if (left + 1 < index) {
            sortArr(nums, left, index);
        }
        if (index < right - 1) {
            sortArr(nums, index + 1, right);
        }
    }

    private int findAndSetBaseIndex(int[] nums, int left, int right) {
        int num = nums[left];
        int index = left + 1;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] < num) {
                swap(nums, index++, i);
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
