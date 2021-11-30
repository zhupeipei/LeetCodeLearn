package com.aire;

import java.util.*;

/**
 * @author ZhuPeipei
 * @date 2021/11/30 17:17
 */
public class Exercise139 {
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList() {
            {
                add("apple");
                add("pen");
            }
        };

//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//        List<String> wordDict = new ArrayList() {
//            {
//                add("a");
//                add("aa");
//                add("aaa");
//                add("aaaa");
//                add("aaaaa");
//                add("aaaaaa");
//                add("aaaaaaa");
//                add("aaaaaaaa");
//                add("aaaaaaaaa");
//                add("aaaaaaaaaa");
//            }
//        };

//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        List<String> wordDict = new ArrayList() {
//            {
//                add("aa");
//                add("aaa");
//                add("aaaa");
//                add("aaaaa");
//                add("aaaaaa");
//                add("aaaaaaa");
//                add("aaaaaaaa");
//                add("aaaaaaaaa");
//                add("aaaaaaaaaa");
//                add("ba");
//            }
//        };

//        String s = "aaaaaaaa";
//        List<String> wordDict = new ArrayList() {
//            {
//                add("aaaa");
//                add("aa");
//                add("a");
//            }
//        };

        long startTime = System.currentTimeMillis();
        boolean canBreak = new Exercise139().wordBreak(s, wordDict);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(canBreak);
    }

    // 139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        if (s.length() == 1) {
            return set.contains(s);
        }
        boolean[] dp = new boolean[s.length()];
        dp[0] = set.contains(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    if (dp[j] && set.contains(s.substring(j + 1, i + 1))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDict1 = optDict(wordDict);
        List<String> words = new ArrayList<>();
        words.addAll(wordDict1);
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() > o2.length()) ? -1 : 1;
            }
        });
        for (String str : words) {
            System.out.println(str);
        }
        return wordBreakInner(s, words);
    }

    private Set<String> optDict(List<String> dicts) {
        List<String[]> list = new ArrayList<>();
        for (String word : dicts) {
            list.add(new String[]{null, word});
        }

        for (int i = 0; i < dicts.size(); i++) {
            String word1 = dicts.get(i);
            for (int j = 0; j < dicts.size(); j++) {
                if (i != j) {
                    String[] words = list.get(j);
                    while (words[1] != null && words[1].startsWith(word1)) {
                        words[0] = word1;
                        words[1] = words[1].substring(word1.length());
                        if (words[1].length() == 0) {
                            words[0] = null;
                            words[1] = null;
                            break;
                        }
                    }
                }
            }
        }

        Set<String> set = new HashSet<>();
        for (String[] str : list) {
            if (str[0] == null || str[0].length() == 0) {
                if (str[1] != null && str[1].length() > 0) {
                    set.add(str[1]);
                }
            } else {
                if (str[1] != null && str[1].length() > 0) {
                    set.add(str[0] + str[1]);
                }
            }
        }
        return set;
    }

    private boolean wordBreakInner(String s, List<String> wordDict) {
        if (wordDict.size() <= 0 || s.length() == 0) {
            return false;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (s.length() == word.length()) {
                    return true;
                }
                if (wordBreakInner(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
}
