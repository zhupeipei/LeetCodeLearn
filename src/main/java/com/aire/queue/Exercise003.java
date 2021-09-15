package com.aire.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 2021/9/15 7:58 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise003 {
    // 完全平方数
    public static void main(String[] args) {
        int n = 12;
        System.out.println(new Exercise003().numSquares(n));
    }

    // 1 <= n <= 10^4
    public int numSquares(int n) {
        if (n == 1) {
            return 1;
        }
        int size = (int) Math.sqrt(n);
        List<Integer> allVals = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            allVals.add(i * i);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, n}); // 第一位 剩余的值 第二位 上一个列表使用的完全平方数
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int[] num = queue.poll();
                for (Integer val : allVals) {
                    if (val <= num[1]) {
                        if (num[0] - val == 0) {
                            return step;
                        } else if (num[0] - val < 0) {
                            continue;
                        } else {
                            queue.add(new int[]{num[0] - val, val});
                        }
                    }
                }
            }
        }
        return step;
    }
}
