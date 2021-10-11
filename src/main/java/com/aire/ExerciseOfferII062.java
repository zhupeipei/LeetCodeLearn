package com.aire;

/**
 * Created on 2021/10/11 10:20 下午.
 *
 * @Author ZhuPeipei
 */
public class ExerciseOfferII062 {
    public static void main(String[] args) {
    }

    // 剑指 Offer II 062. 实现前缀树
    // Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
    // 请你实现 Trie 类：
    // Trie() 初始化前缀树对象。
    // void insert(String word) 向前缀树中插入字符串 word 。
    // boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
    // boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
    class Trie {

        private TreeNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TreeNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() <= 0) {
                return;
            }

            TreeNode node = root;
            for (Character ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.val[index] == null) {
                    node.val[index] = new TreeNode();
                }
                node = node.val[index];
            }
            node.isEnd = true; // 这里的end是在哪里赋值的很重要
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (word == null || word.length() <= 0) {
                return false;
            }
            TreeNode node = root;
            for (Character ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.val[index] == null) {
                    return false;
                }
                node = node.val[index];
            }
            return node.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() <= 0) {
                return false;
            }
            TreeNode node = root;
            for (Character ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (node.val[index] == null) {
                    return false;
                }
                node = node.val[index];
            }
            return true;
        }
    }

    class TreeNode {
        boolean isEnd; // 是否是结尾
        TreeNode[] val = new TreeNode[26];
    }

}
