package com.aire;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

public class Exercise004Official2 {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{4};
//        int[] nums2 = new int[]{1, 2, 3};

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

        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3, 4, 5, 6};
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

            int len1 = nums1.length;
            int len2 = nums2.length;

            int totalNum = (len1 + len2) / 2;
            if (len1 > len2) { // 保证数组 nums1长度小于nums2
                int[] tmp = nums1;
                nums1 = nums2;
                nums2 = tmp;
            }
            int index1 = nums1.length / 2;
            int index2 = totalNum - index1 - 1;

            int val11 = nums1[index1];
            int val12 = index1 + 1 == nums1.length ? Integer.MAX_VALUE : nums1[index1 + 1];

            int val21 = nums2[index2];
            int val22 = index2 + 1 == nums2.length ? Integer.MAX_VALUE : nums2[index2 + 1];

            if (val11 > val22) {

            }
            if (val11 <= val22 && val21 <= val12) {
                break;
            }



        }

    }
}
