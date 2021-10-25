package com.aire;

import com.aire.base.TreeNode;

import java.util.*;

/**
 * Created on 2021/10/25 9:43 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise094 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        root.left = left;
        root.right = right;
        new Exercise094().inorderTraversal(root);
    }

    // 94. 二叉树的中序遍历
    // 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode node = root;
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque();

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                node = node.right;
            } else {
                node = null;
            }
        }
        return list;
    }

    // 后序遍历，未验证，应该没啥问题
    public List<Integer> postOrder(TreeNode root) {
        TreeNode node = root;
        TreeNode preNode = null;
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        // 这里node不会为空时代表左边节点没有遍历过
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == preNode) {
                list.add(node.val);
                node = null;
                preNode = node;
            } else {
                stack.push(node);
                preNode = null;
                node = node.right;
            }
        }
        return list;
    }
}
