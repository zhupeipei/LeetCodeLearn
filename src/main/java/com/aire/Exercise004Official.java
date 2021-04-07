package com.aire;

public class Exercise004Official {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3, 4};
        System.out.println(new Exercise004Official.Solution().findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 看过官方解答 反思自己地一些问题
     * 1. 二分查找没必要一个数组用left和right两个标志位 一个就可以了
     * 2. 只是有这么个想法 但是写的时候发现有很多bug 因此在写之前应该考虑清楚
     */
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
            int index1 = -1, index2 = -1, len1, len2;
            int left1 = -1;
            int left2 = -1;
            len1 = nums1.length;
            len2 = nums2.length;
            while (totalNum >= 1) {
                if (left1 == nums1.length - 1) {
                    left2 = left2 + totalNum;
                    break;
                } else if (left2 == nums2.length - 1) {
                    left1 = left1 + totalNum;
                    break;
                }
                int num = totalNum / 2;
                if (num == 0) {
                    num = 1;
                }
                index1 = left1 + num;
                index2 = left2 + num;
                if (index1 >= len1) {
                    index1 = len1 - 1;
                }
                if (index2 >= len2) {
                    index2 = len2 - 1;
                }
                if (nums1[index1] <= nums2[index2]) {
                    totalNum -= index1 - left1;
                    left1 = index1;
                } else {
                    totalNum -= index2 - left2;
                    left2 = index2;
                }
            }
            // 此时totalNum为0
            index1 = left1;
            index2 = left2;
            int val1;

            if (index1 + 1 >= nums1.length) {
                val1 = nums2[index2 + 1];
            } else if (index2 + 1 >= nums2.length) {
                val1 = nums1[index1 + 1];
            } else {
                val1 = nums1[index1 + 1] > nums2[index2 + 1] ? nums2[1 + index2] : nums1[1 + index1];
            }
            if ((nums1.length + nums2.length) % 2 == 1) {
                return val1;
            }
            int val2;
            if (index1 == -1) {
                val2 = nums2[index2];
            } else if (index2 == -1) {
                val2 = nums1[index1];
            } else {
                val2 = nums1[index1] > nums2[index2] ? nums1[index1] : nums2[index2];
            }
            return (val1 + val2) / 2f;
        }
    }
}
