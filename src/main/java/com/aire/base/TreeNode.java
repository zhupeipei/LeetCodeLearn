package com.aire.base;

import com.aire.Exercise124;

/**
 * Created on 2021/10/2 上午12:52.
 *
 * @Author ZhuPeipei
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
