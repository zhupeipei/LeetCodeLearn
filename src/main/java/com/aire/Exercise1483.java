package com.aire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2021/10/20 上午1:09.
 *
 * @Author ZhuPeipei
 */
public class Exercise1483 {
    public static void main(String[] args) {
        System.out.println(new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2}).getKthAncestor(5, 2));
    }

    // 1483. 树节点的第 K 个祖先
    // 我这个方法太简单了 需要借助logn这种快速收敛 以后碰到这种距离为k要立马想到这种解决方法
    // dp[node][j] = dp[dp[node][j - 1]][j - 1]
    // dp[node][0] 就是 parent[node]，即 node 的距离为 1 的祖先是 parent[node]
    // j=1时表示 node距离为2的祖先是 节点（node距离为1的祖先）距离为1的祖先
    // j=2时表示 node距离为4的祖先时 节点（node距离为2的祖先）距离为2的祖先
    static class TreeAncestor {
        int[] parent;
        Map<Integer, List<Integer>> map = new HashMap<>();

        public TreeAncestor(int n, int[] parent) {
            this.parent = parent;
            for (int i = 0; i < parent.length; i++) {
                int finalI = i;
                map.put(i, new ArrayList<Integer>() {
                    {
                        add(parent[finalI]);
                    }
                });
            }
        }

        public int getKthAncestor(int node, int k) {
            List<Integer> list = map.get(node);
            if (k - 1 < list.size()) {
                return list.get(k - 1);
            }
            for (int i = list.size() - 1; i < k; i++) {
                int val = list.get(i);
                if (val < 0 || val >= parent.length) {
                    return -1;
                }
                int key = parent[list.get(i)];
                list.add(key);
            }
            return list.get(k - 1);
        }
    }

    static class TreeAncestor1 {
        int[] parent;

        public TreeAncestor1(int n, int[] parent) {
            this.parent = parent;
        }

        public int getKthAncestor(int node, int k) {
            int key = node;
            for (int i = 0; i < k; i++) {
                if (key < 0 || key >= parent.length) {
                    return -1;
                }
                key = parent[key];
            }
            return key;
        }
    }
}
