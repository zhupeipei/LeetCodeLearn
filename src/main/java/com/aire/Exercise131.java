package com.aire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/27 15:01
 */
public class Exercise131 {
    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = new Exercise131().partition(s);
        for (int i = 0; i < res.size(); i++) {
            List<String> list = res.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + ", ");
            }
            System.out.println();
        }
    }

    // 131. 分割回文串
    public List<List<String>> partition(String s) {
        boolean[][] f = new boolean[s.length()][s.length()];
        // fij = f(i+1)(j-1) && ch(i)==ch(j)
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    f[i][j] = true;
                } else {
                    boolean res = i + 1 >= j - 1 || i + 1 >= s.length() || j - 1 < 0 ? true : f[i + 1][j - 1];
                    f[i][j] = (s.charAt(i) == s.charAt(j)) && res;
                }
            }
        }

        List<List<String>> list = partionInner(s, 0, f);

        return list;
    }

    private List<List<String>> partionInner(String s, int index, boolean[][] f) {
        List<List<String>> res = new ArrayList<>();
        for (int i = index; i < s.length(); i++) {
            if (f[index][i]) {
                String str = s.substring(index, i + 1);
                if (i == s.length() - 1) {
                    ArrayList<String> l = new ArrayList<>();
                    l.add(str);
                    res.add(l);
                    return res;
                }
                List<List<String>> list2 = partionInner(s, i + 1, f);
                if (list2 != null && list2.size() > 0) {
                    for (int k = 0; k < list2.size(); k++) {
                        list2.get(k).add(0, str);
                    }
                    res.addAll(list2);
                }
            }
        }
        return res;
    }
}
