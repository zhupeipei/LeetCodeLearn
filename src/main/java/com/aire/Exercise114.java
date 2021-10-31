package com.aire;

import com.aire.base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created on 2021/10/31 下午12:34.
 *
 * @Author ZhuPeipei
 */
public class Exercise114 {
    public static void main(String[] args) {

    }

    // 114. 二叉树展开为链表
    // 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    // 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    // 展开后的单链表应该与二叉树 先序遍历 顺序相同。
    public void flatten(TreeNode root) {
        preorderTree(root);
    }

    private void preorderTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode head = root;
        Deque<TreeNode> stack =new ArrayDeque<>();
        if (root.right != null) {
            stack.push(root.right);
        }
        if (root.left != null) {
            stack.push(root.left);
        }
        TreeNode curNode = head;
        curNode.left = null;

        TreeNode node = null;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            curNode.left = null;
            curNode.right = node;
            curNode = node;
        }
    }

    // 中序遍历 没有验证 可以参考 二叉树 双向链表 剑指offer 36题
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
    private void inorderTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode node = root; // node表示这个节点的左节点没有处理过
        TreeNode popNode = null;
        Deque<TreeNode> stack = new ArrayDeque<>(); // 这里存储的节点 都是左节点被处理过的

        TreeNode preNode = null;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            popNode = stack.pop(); // 这里表示左节点都处理过了
            list.add(popNode);
            if (preNode == null) {
                preNode = popNode;
            } else {
                preNode.left = null;
                preNode.right = popNode;
            }
            if (popNode.right == null) {
                node = null;
            } else {
                node = popNode.right;
            }
        }
    }

    // 后序遍历
    private void postOrderTree(TreeNode root) {
        TreeNode node = root; // node表示没有处理过左侧节点
        TreeNode preNode = null; // preNode 记录的是stack中被添加的right!=null的节点的右侧子节点 可以理解为一直是一个往上循环的过程

        Deque<TreeNode> stack = new ArrayDeque<>();
        List<TreeNode> list = new ArrayList<>();
        TreeNode popNode = null;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            popNode = stack.pop();
            /**
             *   1
             *      2
             *   3     4
             *   这里preNode会从4 -> 2 -> 1
             */
            if (popNode.right == null || preNode == popNode.right) {
                list.add(popNode);
                node = null;
                preNode = popNode; // 如果是popNode.right == null 并且popNode 还没有左侧节点 那这个节点是一个叶子节点了
            } else {
                stack.push(popNode);
                node = popNode.right;
                preNode = null;
            }
        }
    }
}
