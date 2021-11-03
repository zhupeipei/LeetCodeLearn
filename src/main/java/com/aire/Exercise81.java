package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/3 14:40
 */
public class Exercise81 {
    public static void main(String[] args) {

    }

    // 81. 搜索旋转排序数组 II
    // 面试题 10.03. 搜索旋转数组
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] < nums[right]) {
                // 一定是上升序列
                if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 分段序列 或者所有值都一样
                if (nums[right] == nums[left]) {
                    right--;
                } else {
                    if (nums[mid] >= nums[left]) {
                        if (target > nums[mid] || target <= nums[right]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    } else {
                        if (target >= nums[mid] && target <= nums[right]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                }
            }
        }
        return nums[left] == target ? true : false;
    }
}
