package com.aire;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZhuPeipei
 * @date 2021/11/30 20:44
 */
public class Exercise140 {
    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> list = new ArrayList() {
            {
                add("apple");
                add("pen");
                add("applepen");
                add("pine");
                add("pineapple");
            }
        };
        List<String> res = new Exercise140().wordBreak(s, list);
        for (String str : res) {
            System.out.println(str);
        }
    }

    // 140. 单词拆分 II
    public List<String> wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];

        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }

        List<String>[] list = new ArrayList[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (list[i] == null) {
                list[i] = new ArrayList<>();
            }
            if (set.contains(s.substring(0, i + 1))) {
                dp[i] = true;
                list[i].add(s.substring(0, i + 1) + (i == s.length() - 1 ? "" : " "));
            }
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j + 1, i + 1))) {
                    dp[i] = true;
                    for (int k = 0; k < list[j].size(); k++) {
                        list[i].add(list[j].get(k) + s.substring(j + 1, i + 1) + (i == s.length() - 1 ? "" : " "));
                    }
                }
            }
        }

        return list[s.length() - 1];
    }
}
