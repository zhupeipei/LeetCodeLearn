package com.aire;

public class Exercise004 {
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
        System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));
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
            int left1 = 0;
            int right1 = nums1.length - 1;
            int left2 = 0;
            int right2 = nums2.length - 1;
            int index1, index2;
            int middle = (nums1.length + nums2.length) / 2;
            while (left1 + left2 + 2 < middle) {
                index1 = (left1 + right1) / 2;
                index2 = (left2 + right2) / 2;
                int val1 = nums1[index1];
                int val2 = nums2[index2];

                if (val1 > val2) {
                    if (left2 == index2) { // 这一块代码主要是为了让每次循环有效 避免死循环
                        if (index1 == left1) {
                            left1++;
                            if (left1 >= nums1.length) {
                                break;
                            }
                        }
                        left1 = index1;
                    }
                    left2 = index2;
                } else if (val1 < val2) {
                    if (left1 == index1) {
                        if (index2 == left2) {
                            left2++;
                            if (left2 >= nums2.length) {
                                break;
                            }
                        }
                        left2 = index2;
                    }
                    left1 = index1;
                } else {
                    left1 = index1;
                    left2 = index2;
                }
            }
            if (left1 + left2 == middle - 2) {
                if (left1 + 1 < nums1.length) {
                    left1++;
                }
                if (left2 + 1 < nums2.length) {
                    left2++;
                }
            }
            if (nums1[left1] > nums2[left2]) {
                while (true) {
                    left2++;
                    if (left2 >= nums2.length) {
                        left2 = nums2.length - 1;
                        int l1 = middle - left2 - 1 - 1;
                        if (left1 < l1) {
                            left1 = l1;
                        }
                        break;
                    }
                    if (nums2[left2] >= nums1[left1]) {
                        break;
                    }
                }
            } else if (nums1[left1] < nums2[left2]) {
                while (true) {
                    left1++;
                    if (left1 >= nums1.length) {
                        left1 = nums1.length - 1;
                        int l2 = middle - left1 - 1 - 1;
                        if (left2 < l2) {
                            left2 = l2;
                        }
                        break;
                    }
                    if (nums1[left1] >= nums2[left2]) {
                        break;
                    }
                }
            }
            int num = left1 + left2 + 2 - middle;
            int v1 = 0;
            while (--num >= 0) {
                boolean le = getPrevVal(left1, nums1, left2, nums2);
                if (le) {
                    v1 = nums1[left1--];
                } else {
                    v1 = nums2[left2--];
                }
            }
            if ((nums1.length + nums2.length) % 2 == 0) {
                int v2;
                boolean le = getPrevVal(left1, nums1, left2, nums2);
                if (le) {
                    v2 = nums1[left1--];
                } else {
                    v2 = nums2[left2--];
                }
                return (v1 + v2) / 2d;
            } else {
                return v1;
            }
        }

        // true 表示左边数据 false表示右边数据
        private boolean getPrevVal(int left1, int[] nums1, int left2, int[] nums2) {
            if (left1 < 0) {
                return false;
            }
            if (left2 < 0) {
                return true;
            }
            if (nums1[left1] < nums2[left2]) {
                return false;
            } else {
                return true;
            }
        }
    }
}
