package com.aire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/30 15:13
 */
public class Exercise0812 {
    public static void main(String[] args) {
        List<List<String>> res = new Exercise0812().solveNQueens(4);
        for (List<String> list : res) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str).append(", ");
            }
            System.out.println(sb);
            System.out.println();
        }
    }

    // 面试题 08.12. 八皇后
    public List<List<String>> solveNQueens(int n) {
        List<List<int[]>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    List<int[]> arr = new ArrayList();
                    arr.add(new int[]{0, j});
                    res.add(arr);
                }
            } else {
                res = solveNext(res, i, n);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<int[]> list : res) {
            List<String> ans1 = new ArrayList<>();
            for (int[] ll : list) {
                int num = ll[1];
                sb.setCharAt(num, 'Q');
                ans1.add(sb.toString());
                sb.setCharAt(num, '.');
            }
            ans.add(ans1);
        }
        return ans;
    }

    // 之前I-1行的各种方法
    public List<List<int[]>> solveNext(List<List<int[]>> preList, int I, int n) {
        List<List<int[]>> res = new ArrayList<>();
        for (List<int[]> list : preList) {
            for (int j = 0; j < n; j++) {
                if (isSatisified(list, I, j)) {
                    List<int[]> l = new ArrayList();
                    l.addAll(list);
                    l.add(new int[]{I, j});
                    res.add(l);
                }
            }
        }
        return res;
    }

    private boolean isSatisified(List<int[]> list, int i, int j) {
        for (int[] arr : list) {
            if (arr[0] == i || arr[1] == j) {
                return false;
            }
            if (arr[1] - arr[0] == j - i) {
                return false;
            }
            if (arr[1] + arr[0] == i + j) {
                return false;
            }
        }
        return true;
    }
}
