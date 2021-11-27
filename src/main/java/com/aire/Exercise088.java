package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/27 00:29
 */
public class Exercise088 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        new Exercise088().merge(nums1, m, nums2, n);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + ", ");
        }
    }

    // 88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int num2Index = n - 1;
        int num1Index = m - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (num1Index >= 0 && num2Index >= 0) {
                if (nums1[num1Index] > nums2[num2Index]) {
                    nums1[i] = nums1[num1Index--];
                } else {
                    nums1[i] = nums2[num2Index--];
                }
            } else if (num1Index >= 0) {
                nums1[i] = nums1[num1Index--];
            } else if (num2Index >= 0) {
                nums1[i] = nums2[num2Index--];
            }
        }
    }
}
