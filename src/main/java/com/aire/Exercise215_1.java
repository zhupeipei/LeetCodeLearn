package com.aire;

/**
 * Created on 2021/10/27 8:06 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise215_1 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 3;
        int res = new Exercise215_1().findKthLargest(nums, k);
        System.out.println(res);
    }

    // 215. 数组中的第K个最大元素
    // 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    // https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
    // 这里基于堆排序
    public int findKthLargest(int[] nums, int k) {
        buildHeap(nums);

        int size = nums.length - 1;
        for (int i = 0; i < k; i++) {
            swap(nums, 0, size);
            heapify(nums, 0, size);
            size--;
        }
        return nums[nums.length - k];
    }

    private void heapify(int[] nums, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largst = i;
        if (left < size && nums[left] > nums[largst]) {
            largst = left;
        }
        if (right < size && nums[right] > nums[largst]) {
            largst = right;
        }
        if (largst != i) {
            swap(nums, i, largst);
            heapify(nums, largst, size);
        }
    }

    private void buildHeap(int[] nums) {
        int len = nums.length;
        for (int i = len / 2; i >= 0; i--) {
            heapify(nums, i, len);
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
