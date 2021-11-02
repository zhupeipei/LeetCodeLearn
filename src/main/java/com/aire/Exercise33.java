package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/2 21:25
 */
public class Exercise33 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {5, 1, 3};
        int[] nums = {3, 1};
        System.out.println(new Exercise33().search(nums, 3));
    }

    // 33. 搜索旋转排序数组
    // 整数数组 nums 按升序排列，数组中的值 互不相同 。
    // 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
    // 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        if (nums[0] < nums[nums.length - 1]) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
        // 这里为分段的情况
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[0] < target) {
                left
            }
        }
    }

    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        if (nums[0] < nums[nums.length - 1]) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int minIndex = left;

        if (target > nums[0]) {
            return binarySearch(nums, 0, minIndex - 1, target);
        } else if (target == nums[0]) {
            return 0;
        } else {
            return binarySearch(nums, minIndex, nums.length - 1, target);
        }
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
