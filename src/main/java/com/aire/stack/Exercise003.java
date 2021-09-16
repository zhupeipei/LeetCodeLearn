package com.aire.stack;

import java.util.Stack;

/**
 * Created on 2021/9/16 9:22 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise003 {
    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
//        int[] heights = new int[]{2, 1, 2};
        System.out.println(new Exercise003().largestRectangleArea(heights));
    }

    // 84. 柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int[] area = new int[heights.length];

        int size = heights.length;
        for (int i = 0; i <= size; i++) {
            int height = i == size ? -1 : heights[i];
            if (stack.isEmpty() || height >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int idx = stack.pop();
                int startIndex = stack.isEmpty() ? -1 : stack.peek();
                area[idx] = heights[idx] * (i - startIndex - 1); // 这个宽度的计算是比较重要的
                maxArea = Math.max(area[idx], maxArea);
                i--;
            }
        }
        return maxArea;
    }
}
