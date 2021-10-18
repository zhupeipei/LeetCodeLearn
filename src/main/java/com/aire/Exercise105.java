package com.aire;

import com.aire.base.TreeNode;

/**
 * Created on 2021/10/12 10:34 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise105 {
    public static void main(String[] args) {

    }

    // 105. 从前序与中序遍历序列构造二叉树
    // 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // preOrder的特点为先根节点 再左子树 最后右子树
        // inOrder的特点为左-根-右
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = preorder[0];
        int leftNum = findIndex(inorder, root.val);
        int rightNum = preorder.length - leftNum - 1;
        int[] preLeft = new int[leftNum];
        int[] preRight = new int[rightNum];

        int[] inLeft = new int[leftNum];
        int[] inRight = new int[rightNum];
        System.arraycopy(preorder, 1, preLeft, 0, leftNum);
        System.arraycopy(inorder, 0, inLeft, 0, leftNum);
        System.arraycopy(preorder, 1 + leftNum, preRight, 0, rightNum);
        System.arraycopy(inorder, leftNum + 1, inRight, 0, rightNum);
        root.left = buildTree(preLeft, inLeft);
        root.right = buildTree(preRight, inRight);
        return root;
    }

    private int findIndex(int[] order, int val) {
        for (int i = 0; i < order.length; i++) {
            if (val == order[i]) {
                return i;
            }
        }
        return 0;
    }
}
