package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/2 20:26
 */
public class Exercise153 {

    // 153. 寻找旋转排序数组中的最小值
    // 参考 154 题、33 题
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        int val0 = nums[0], valn = nums[nums.length - 1];
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (valn < nums[mid]) { // 分段
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else { // 这里说明left == right 不可能发生
                return nums[left];
            }
        }
        return nums[left];
    }
}
