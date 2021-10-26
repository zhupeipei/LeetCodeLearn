package com.aire;

/**
 * Created on 2021/10/26 上午12:49.
 *
 * @Author ZhuPeipei
 */
public class Exercise215 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        int res = new Exercise215().findKthLargest(nums, k);
        System.out.println(res);
    }

    // 215. 数组中的第K个最大元素
    // 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    // https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k) {

        int num = nums[0];
        int index = getIndex(num, nums, 1, nums.length);
        if (index)

    }

    // 获取index位置数据在nums中的位置 同时数据进行调整
    // 包含left 不包含right
    private int getPivotIndex(int[] arr, int left, int right) {

    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
