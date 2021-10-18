package com.aire;

import com.aire.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2021/10/19 上午12:39.
 *
 * @Author ZhuPeipei
 */
public class Exercise297 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);

        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);

        TreeNode right = new TreeNode(3, left1, right1);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(new Exercise297().serialize(root));

        String abc = "[1,2,3,null,null,4,5]";
        new Exercise297().deserialize(abc);
        int i = 0;
    }

    // 297. 二叉树的序列化与反序列化
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder("[").append(root.val).append(",");
        // 获取当前所有节点子节点的值，并存储孙节点
        while (!queue.isEmpty()) {
            int size = queue.size();
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null) {
                    sb1.append("null,");
                } else {
                    sb1.append(node.left.val).append(",");
                    queue.add(node.left);
                }
                if (node.right == null) {
                    sb1.append("null,");
                } else {
                    sb1.append(node.right.val).append(",");
                    queue.add(node.right);
                }
            }
            if (!queue.isEmpty()) {
                sb.append(sb1);
            }
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.substring(1, data.length() - 1).split(",");
        if (datas.length <= 0) {
            return null;
        }
        if (datas.length == 1 && (datas[0].equals("null") || datas[0].equals(""))) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        if (datas.length == 1) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (index + 1 < datas.length) {
                    String val = datas[index++];
                    if (val.equals("null")) {
                        node.left = null;
                    } else {
                        node.left = new TreeNode(Integer.parseInt(val));
                        queue.add(node.left);
                    }
                }
                if (index < datas.length) {
                    String val1 = datas[index++];
                    if (val1.equals("null")) {
                        node.right = null;
                    } else {
                        node.right = new TreeNode(Integer.parseInt(val1));
                        queue.add(node.right);
                    }
                }
            }
        }
        return root;
    }
}
