package com.aire.queue;

import java.util.*;

public class Exercise002 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(new Exercise002().ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> used = new HashSet<>();
        used.add(beginWord);

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                boolean found = updateQueue(queue, wordList, endWord, word, used);
                if (found) {
                    return step + 1;
                }
            }
        }
        return 0;
    }

    private boolean updateQueue(Queue<String> queue, List<String> wordList, String targetWord, String word, Set<String> usedWords) {
        if (canTranslate(word, targetWord)) {
            return true;
        }
        for (int i = 0; i < wordList.size(); i++) {
            String wordi = wordList.get(i);
            if (usedWords.contains(wordi) || targetWord.equals(word)) {
                continue;
            }
            if (canTranslate(word, wordi)) {
                queue.add(wordi);
                usedWords.add(wordi);
            }
        }
        return false;
    }

    private Set<String> foundHitWords(List<String> wordList, String word) {
        Set<String> list = null;
        for (int i = 0; i < wordList.size(); i++) {
            String wordi = wordList.get(i);
            if (canTranslate(wordi, word)) {
                if (list == null) list = new HashSet<>();
                list.add(wordi);
            }
        }
        return list;
    }

    /**
     * 从word1到word2能否一次完成转换
     *
     * @param word1
     * @param word2
     * @return
     */
    private boolean canTranslate(String word1, String word2) {
        if (word1.length() > word2.length()) {
            return (word1.length() == word2.length() + 1) && word1.contains(word2);
        } else if (word1.length() < word2.length()) {
            return (word1.length() + 1 == word2.length()) && word2.contains(word1);
        } else {
            int count = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                }
            }
            return count == 1;
        }
    }
}
