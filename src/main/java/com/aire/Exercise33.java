package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/2 21:25
 */
public class Exercise33 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {5, 1, 3};
        System.out.println(new Exercise33().search(nums, 5));
    }

    // 33. 搜索旋转排序数组
    // 整数数组 nums 按升序排列，数组中的值 互不相同 。
    // 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
    // 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;




        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) { // mid<right处的值 只有一种情况 就是 mid到right是升序排列的 不会出现中间有下降
                // 1. left -- mid -- right 2. left -- x -- mid -- right
                if (nums[mid] < target) {
                    // 1. left -- mid -- target -- right
                    // 2. left -- x -- mid -- target -- right
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    // 1. left -- target -- mid -- right
                    // 2. left -- x -- target -- mid -- right
                    right = mid - 1;
                } else {
                    return mid;
                }
            } else if (nums[mid] > nums[right]) {
                // right < mid 一种为升序 left - right 一种为中间有断层 left --- x;x+1 --- right
                // 对升序 则 mid在right右侧 这个不可能，对中间有断层 mid在left---x之间
                if (nums[mid] < target) { // left --- mid --- target --- x --- right
                    left = mid + 1;
                } else if (nums[mid] > target) { // 1. left --- target --- mid --- x --- right 2. left -- mid -- x -- target -- right
                    if (nums[left] < target) {
                        right = mid - 1;
                    } else if (nums[left] > target) {
                        left = mid + 1;
                    } else {
                        return left;
                    }
                } else {
                    return mid;
                }
            } else {
                return right;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
