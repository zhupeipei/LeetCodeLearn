package com.aire;

import com.aire.base.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created on 2021/11/1 上午1:01.
 *
 * @Author ZhuPeipei
 */
public class Exercise036Offer {
    public static void main(String[] args) {

    }

    // 剑指 Offer 36. 二叉搜索树与双向链表
    // 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        return inorderTree(root);
    }

    private Node inorderTree(Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        Node node = root; // node表示未处理过左边节点
        List<Node> list = new ArrayList<>();
        Node popNode = null;

        Node head = null;
        Node preNode = null;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            popNode = stack.pop();
            if (preNode == null) {
                head = popNode;
                preNode = popNode;
            } else {
                preNode.right = popNode;
                popNode.left = preNode;
                preNode = popNode;
            }
            if (popNode.right != null) {
                node = popNode.right;
            } else {
                node = null;
            }
        }
        preNode.right = head;
        head.left = preNode;
        return head;
    }
}
