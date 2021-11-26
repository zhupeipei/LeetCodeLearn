package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/26 22:26
 */
public class Exercise912_1 {
    public static void main(String[] args) {
//        int[] nums = {5, 2, 3, 1};
        int[] nums = {5, 1, 1, 2, 0, 0};
        nums = new Exercise912_1().sortArray(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }

    // 912. 排序数组
    public int[] sortArray(int[] nums) {
//        return quickSort(nums);
//        return heapSort(nums);
        return shellSort(nums);
    }

    // 1. 冒泡排序 快速排序
    private int[] quickSort(int[] nums) {
        quickSortInner(nums, 0, nums.length);
        return nums;
    }

    private void quickSortInner(int[] nums, int left, int right) {
        int index = findBaseIndex(nums, left, right);
        if (left < index) {
            quickSortInner(nums, left, index);
        }
        if (index + 1 < right) {
            quickSortInner(nums, index + 1, right);
        }
    }

    private int findBaseIndex(int[] nums, int left, int right) {
        int baseVal = nums[left];
        int index = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] < baseVal) {
                swap(nums, i, ++index);
            }
        }
        swap(nums, index, left);
        return index;
    }

    // 2. 选择排序 堆排序
    private int[] heapSort(int[] nums) {
        buildHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
        return nums;
    }

    // 建立大顶堆
    private void buildHeap(int[] nums) {
        int index = nums.length / 2;
        int left, right, maxIndex;
        for (int i = index; i >= 0; i--) {
            left = 2 * i + 1;
            right = 2 * i + 2;
            maxIndex = i;
            if (left < nums.length && nums[left] > nums[i]) {
                maxIndex = left;
            }
            if (right < nums.length && nums[right] > nums[maxIndex]) {
                maxIndex = right;
            }
            if (maxIndex != i) {
                swap(nums, i, maxIndex);
                heapify(nums, maxIndex, nums.length);
            }
        }
    }

    // 大顶堆排序
    private void heapify(int[] nums, int index, int maxLen) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int maxIndex = index;
        if (left < maxLen && nums[left] > nums[index]) {
            maxIndex = left;
        }
        if (right < maxLen && nums[right] > nums[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != index) {
            swap(nums, index, maxIndex);
            heapify(nums, maxIndex, maxLen);
        }
    }

    // 3. 插入排序 希尔排序
    private int[] shellSort(int[] nums) {
        for (int step = nums.length / 2; step > 0; step = step / 2) {
            for (int i = step; i < nums.length; i++) {
                int index = i;
                for (int j = i - step; j >= 0; j -= step) {
                    if (nums[index] < nums[j]) {
                        swap(nums, index, j);
                        index = j;
                    } else {
                        break;
                    }
                }
            }
        }
        return nums;
    }

    // common function
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
