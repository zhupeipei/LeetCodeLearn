package com.aire;

import java.util.*;

/**
 * Created on 2021/10/19 8:16 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise834 {
    private Map<Integer, Integer> cnt = new HashMap<>();

    public static void main(String[] args) {
        int N = 6;
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};

//        int N = 1;
//        int[][] edges = new int[][]{{1,0}};

        System.out.println(new Exercise834().sumOfDistancesInTree(N, edges));
    }

    // 834. 树中距离之和
    // 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1条边。
    // 第 i 条边连接节点edges[i][0] 和 edges[i][1]。
    // 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
    // A和B是相邻节点，ans[A] = sum[A] + sum[B] + cnt[B]，ans[B] = sum[A] + sum[B] + cnt[A]，cnt[A] + cnt[B] = N
    // ans[A] - ans[B] = cnt[B] - cnt[A] -> ans[A] = ans[B] + N - 2 * cnt[A]
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int node0 = edge[0];
            int node1 = edge[1];

            if (map.get(node0) == null) {
                List<Integer> list = new ArrayList<>();
                list.add(node1);
                map.put(node0, list);
            } else {
                map.get(node0).add(node1);
            }

            if (map.get(node1) == null) {
                List<Integer> list = new ArrayList<>();
                list.add(node0);
                map.put(node1, list);
            } else {
                map.get(node1).add(node0);
            }

        }
        int rootIndex = 0;
        int[] ans = new int[n];
        ans[rootIndex] = ansVal(map, rootIndex, -1, 1)[0];

        ans(ans, ans[rootIndex], n, rootIndex, map);

        return ans;
    }

    private void ans(int[] ans, int parentAns, int n, int index, Map<Integer, List<Integer>> map) {
        List<Integer> list = map.get(index);
        if (list == null || list.size() == 0) {
            return;
        }
        for (Integer val : list) {
            ans[val] = parentAns + n - 2 * (cnt.get(val)/**1+cnt(val, map)**/);
            ans(ans, ans[val], n, val, map);
        }
    }

    private int cnt(int index, Map<Integer, List<Integer>> map) {
        List<Integer> list = map.get(index);
        if (list == null || list.size() == 0) {
            return 0;
        }
        int cnt = 0;
        for (Integer val : list) {
            cnt += 1 + cnt(val, map);
        }
        return cnt;
    }

    // 第二个为不包括自身的子树的个数
    private int[] ansVal(Map<Integer, List<Integer>> map, int index, int parentIndex, int cengshu) {
        List<Integer> list = map.get(index);
        if (list != null) {
            // 这里顺带 优化了下数据结构
            list.remove((Integer) parentIndex);
        }
        if (list == null || list.size() == 0) {
            cnt.put(index, 1);
            return new int[]{0, 0};
        }
        int ans = 0;
        int num = list.size();
        for (Integer val : list) {
            int[] vals = ansVal(map, val, index, cengshu + 1);
            ans += cengshu * 1 + vals[0];
            num += vals[1];
        }
        cnt.put(index, 1 + num);
        return new int[]{ans, num};
    }
}
