package com.aire;

import com.aire.base.TreeNode;

/**
 * Created on 2021/9/22 10:58 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise124 {
    private int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) {
//        TreeNode left = new TreeNode(2, null, null);
//        TreeNode right = new TreeNode(3, null, null);
//        TreeNode root = new TreeNode(1, left, right);

        TreeNode root = new TreeNode(-3, null, null);

//        TreeNode left = new TreeNode(2, null, null);
//        TreeNode right = new TreeNode(3, null, null);
//        TreeNode root = new TreeNode(1, left, right);

        System.out.println(new Exercise124().maxPathSum(root));
    }

    // 124. 二叉树中的最大路径和
    public int maxPathSum(TreeNode root) {
        getMaxVal(root);
        return maxValue;
    }

    // 这个方法是获取包含根节点的最大值
    private int getMaxVal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = 0;
        if (root.left != null) {
            maxLeft = getMaxVal(root.left);
            maxValue = Math.max(maxLeft, maxValue);
        }
        int maxRight = 0;
        if (root.right != null) {
            maxRight = getMaxVal(root.right);
            maxValue = Math.max(maxRight, maxValue);
        }
        maxValue = Math.max(maxValue, root.val + maxLeft + maxRight);
        int maxRoot = Math.max(root.val, Math.max(root.val + maxLeft, root.val + maxRight));
        maxValue = Math.max(maxValue, maxRoot);
        return maxRoot;
    }

}
