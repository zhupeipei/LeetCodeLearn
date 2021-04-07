package com.aire;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

public class Exercise004Official2 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4, 5};

//        int[] nums1 = new int[]{1, 1, 1};
//        int[] nums2 = new int[]{1, 1, 1};

//        int[] nums1 = new int[]{1, 3};
//        int[] nums2 = new int[]{2};

//        int[] nums1 = new int[]{5};
//        int[] nums2 = new int[]{1, 2, 3, 4, 6};

//        int[] nums1 = new int[]{1, 2};
//        int[] nums2 = new int[]{3, 4};

//        int[] nums1 = new int[]{1, 2};
//        int[] nums2 = new int[]{-1, 3};

//        int[] nums1 = new int[]{1};
//        int[] nums2 = new int[]{2, 3, 4, 5, 6};
        System.out.println(new Exercise004Official2.Solution().findMedianSortedArrays(nums1, nums2));
    }

    static class Solution {
        // 两个数组不会同时为空
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0) {
                int i2 = nums2.length / 2;
                return nums2.length % 2 == 1 ? nums2[i2] : (nums2[i2] + nums2[i2 - 1]) / 2d;
            } else if (nums2 == null || nums2.length == 0) {
                int i1 = nums1.length / 2;
                return nums1.length % 2 == 1 ? nums1[i1] : (nums1[i1] + nums1[i1 - 1]) / 2d;
            }

            int totalNum = (nums1.length + nums2.length) / 2;
            if (nums1.length > nums2.length) { // 保证数组 nums1长度小于nums2
                int[] tmp = nums1;
                nums1 = nums2;
                nums2 = tmp;
            }
            int left1 = -1;
            int right1 = nums1.length;
            int index1;
            int index2;
            int val11, val12, val21, val22;
            while (true) {
                index1 = (left1 + right1) / 2;
                if (left1 == -1 && right1 == 0) {
                    index1 = -1;
                }
                if (right1 == nums1.length && left1 == nums1.length - 1) {
                    index1 = nums1.length;
                }
                index2 = totalNum - index1 - 2;

                val11 = index1 == -1 ? Integer.MIN_VALUE : (index1 == nums1.length ? Integer.MAX_VALUE : nums1[index1]);
                val12 = index1 + 1 == nums1.length ? Integer.MAX_VALUE : nums1[index1 + 1];

                val21 = index2 == -1 ? Integer.MIN_VALUE : (index2 == nums2.length ? Integer.MAX_VALUE : nums2[index2]);
                val22 = index2 + 1 == nums2.length ? Integer.MAX_VALUE : nums2[index2 + 1];

                if (val11 > val22) {
                    right1 = index1;
                } else if (val21 > val12) {
                    left1 = index1;
                }
                if (val11 <= val22 && val21 <= val12) {
                    if ((nums1.length + nums2.length) % 2 == 1) {
                        return Math.min(val12, val22);
                    } else {
                        return (Math.max(val11, val21) + Math.min(val12, val22)) / 2d;
                    }
                }
            }
        }

    }
}
