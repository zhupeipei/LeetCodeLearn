package com.aire;

import com.aire.base.TreeNode;

import java.util.*;

/**
 * Created on 2021/10/12 11:02 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise814 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(0);
        TreeNode a1 = new TreeNode(0);
        TreeNode a2 = new TreeNode(1);
        root.right = a;
        a.left = a1;
        a.right = a2;
        new Exercise814().pruneTree(root);
    }

    // 剑指 Offer II 047. 二叉树剪枝
    // 814. 二叉树剪枝
    // 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
    // 返回移除了所有不包含 1 的子树的原二叉树。
    // 节点 node 的子树为 node 本身加上所有 node 的后代。
    public TreeNode pruneTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode preNode = null;
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, Boolean> map = new HashMap<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == preNode) {
                res.add(node.val);
                if (node.left != null && map.get(node.left) != null) {
                    node.left = null;
                }
                if (node.right != null && map.get(node.right) != null) {
                    node.right = null;
                }
                if (node.val == 0 && node.left == null && node.right == null) {
                    map.put(node, true);
                }
                preNode = node;
                node = null;
            } else {
                stack.push(node);
                node = node.right;
                preNode = null;
            }
        }
        if (map.get(root) != null) {
            return null;
        }
        return root;
    }
}
