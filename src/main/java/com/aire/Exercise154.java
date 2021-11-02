package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/2 16:22
 */
public class Exercise154 {
    public static void main(String[] args) {
//        int[] nums = {3, 4, 5, 1, 2};
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(new Exercise154().findMin(nums));
    }

    // 154. 寻找旋转排序数组中的最小值 II
    // 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
    // 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
    // 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
    // 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    //
    // 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            // 1. left -- right
            // 2. left -- x -- right
            if (nums[mid] < nums[right]) {
                // 为什么不会在左侧上升区域？
                // 1. 如果在左侧上升区域，说明计算已经错误了，假设计算正确，说明right被修改了，
                // 但是从代码中可以看出right修改只在当前条件下才会被修改，因此不可能出现在左侧区域的状态
                // 2. 考虑问题的时候，应该是从更普遍的情况，也就是二分查找到最后，结构还是只会存在两种要么上升，
                // 要么两端区域，不需要考虑特别的情况，如果出现特别情况，至少说明其他地方的逻辑是错误的
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else { // mid和right处的值相等 表示
                right--;
            }
        }
        return nums[left];
    }
}
