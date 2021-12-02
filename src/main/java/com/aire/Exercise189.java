package com.aire;

import java.util.Arrays;

/**
 * @author ZhuPeipei
 * @date 2021/12/2 13:27
 */
public class Exercise189 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        new Exercise189().rotate(nums, k);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }

    // 189. 轮转数组
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int blockNum = nums.length / k + (nums.length % k == 0 ? 0 : 1);
        int[] block = new int[k]; // 写入的
        int[] tmp = new int[k]; // 临时块
        for (int i = 0; i < blockNum; i++) {
            boolean rotate = false;
            for (int j = 0; j < k; j++) {
                int readIndex = i * k + j;
                if (readIndex >= nums.length) {
                    if (!rotate) {
                        rotate = true;
                        int num = k * blockNum - nums.length;
                        System.arraycopy(tmp, 0, tmp, num, k - num);
                    }
                    tmp[readIndex - nums.length] = block[j];
                } else {
                    tmp[j] = nums[readIndex];
                    if (i != 0) {
                        nums[readIndex] = block[j];
                    }
                }
            }
            int[] temp = block;
            block = tmp;
            tmp = temp;
            Arrays.fill(tmp, 0);
        }
        for (int i = 0; i < k; i++) {
            nums[i] = block[i];
        }
    }
}
