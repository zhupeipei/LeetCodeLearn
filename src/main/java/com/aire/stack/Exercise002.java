package com.aire.stack;

import java.util.Stack;

/**
 * Created on 2021/9/15 10:32 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise002 {
    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        // [1,1,4,2,1,1,0,0]
        System.out.println(new Exercise002().dailyTemperatures(temperatures));
    }

    // 你来这里 其实和你的能力是不匹配的
    // 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }
        return result;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int val = temperatures[i];
            if (stack.isEmpty() || val <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                int top = stack.pop();
                result[top] = i - top;
                i--;
            }
        }
        return result;
    }
}
