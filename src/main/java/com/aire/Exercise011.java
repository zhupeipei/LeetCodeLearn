package com.aire;

public class Exercise011 {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(new Exercise011.Solution().maxArea(height));
    }

    // 只有耐心的思考下去才能找到问题的答案 自己还是太浮躁看了下 感觉就有思路了 但是并没有深入的思考
    static class Solution {
        public int maxArea(int[] height) {
            if (height == null || height.length <= 0) {
                return 0;
            }
            int maxArea = 0;
            int maxI = 0, maxJ = 0;
            int j = height.length - 1;
            for (int i = 0; i < height.length; ) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (maxArea < area) {
                    maxI = i;
                    maxJ = j;
                    maxArea = area;
                }
                if (height[i] >= height[j]) {
                    j--;
                } else {
                    i++;
                }
                if (j <= i) {
                    break;
                }
            }
            System.out.println(maxI + "," + maxJ + "");
            return maxArea;
        }
    }
}
