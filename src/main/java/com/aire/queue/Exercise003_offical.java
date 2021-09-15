package com.aire.queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created on 2021/9/15 8:35 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise003_offical {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(new Exercise003_offical().numSquares(n));
    }

    // 1 <= n <= 10^4
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int nodeVal = queue.poll();
                for (int j = 1; j < n; j++) {
                    int val = nodeVal + j * j;
                    if (val > n) {
                        continue;
                    }
                    if (val == n) {
                        return step;
                    }
                    if (visited.contains(val)) {
                        continue;
                    }
                    visited.add(val);
                    queue.add(val);
                }
            }
        }
        return step;
    }
}
