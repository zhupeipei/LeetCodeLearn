package com.aire.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class Java007BinaryTreeTest {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        System.out.println(new Solution().inorderTraversal1(node));
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // 前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> pendingNodes = new ArrayList<>();
        pendingNodes.add(root);
        TreeNode node;
        do {
            node = pendingNodes.remove(pendingNodes.size() - 1);
            if (node != null) {
                result.add(node.val);
                if (node.right != null) {
                    pendingNodes.add(node.right);
                }
                if (node.left != null) {
                    pendingNodes.add(node.left);
                }
            }
        } while (pendingNodes.size() >= 1);
        return result;
    }

    // 递归方法调用
    // 前序遍历
    private void getRootVal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        getRootVal(node.left, list);
        getRootVal(node.right, list);
    }

    // 中序遍历 这个会破坏源数据结构
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> pendingNodes = new ArrayList<>();
        pendingNodes.add(root);
        TreeNode node = null;
        while (true) {
            int size = pendingNodes.size();
            if (size > 0) {
                node = pendingNodes.remove(size - 1);
                if (node == null) {
                    continue;
                } else {
                    if (node.left == null) {
                        result.add(node.val);
                        pendingNodes.add(node.right);
                    } else {
                        TreeNode leftNode = node.left;
                        node.left = null;
                        pendingNodes.add(node);
                        pendingNodes.add(leftNode);
                    }
                }
            } else {
                break;
            }
        }
        return result;
    }

    // 中序遍历 优化
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> pendingNodes = new ArrayList<>();
        TreeNode node = root;
        while (node != null || pendingNodes.size() > 0) {
            while (node != null) { // 这里一直加到左边节点为null的节点
                pendingNodes.add(node);
                node = node.left;
            }
            // 这是node==null
            if (pendingNodes.size() > 0) {
                node = pendingNodes.remove(pendingNodes.size() - 1); // 此时这个node1.left=null
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    // 右序遍历 方法1
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> pendingNodes = new ArrayList<>();
        pendingNodes.add(root);
        TreeNode node;
        while (true) {
            if (pendingNodes.size() > 0) {
                node = pendingNodes.remove(pendingNodes.size() - 1);
                if (node == null) {
                    continue;
                } else {
                    if (node.left == null) {
                        if (node.right == null) {
                            result.add(node.val);
                        } else {
                            TreeNode nod1 = node.right;
                            node.right = null;
                            pendingNodes.add(node);
                            pendingNodes.add(nod1);
                        }
                    } else { // left不为null
                        TreeNode node1 = node.left;
                        node.left = null;
                        pendingNodes.add(node);
                        pendingNodes.add(node1);
                    }
                }
            } else {
                break;
            }
        }
        return result;
    }

    // 右序遍历 方法2
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> pendingNodes = new ArrayList<>();
        TreeNode node = root;
        while (node != null || pendingNodes.size() > 0) {
            while (node != null) {
                pendingNodes.add(node);
                node = node.left;
            }
            if (pendingNodes.size() > 0) {
                node = pendingNodes.remove(pendingNodes.size() - 1); // 这里node.left==null
                if (node.right == null) {
                    result.add(node.val);
                    node = null;
                } else {
                    TreeNode node1 = node.right;
                    node.right = null;
                    pendingNodes.add(node);
                    node = node1;
                }
            }
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
