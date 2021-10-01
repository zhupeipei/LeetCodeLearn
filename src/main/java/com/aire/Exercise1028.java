package com.aire;

import com.aire.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 2021/10/2 上午12:50.
 *
 * @Author ZhuPeipei
 */
public class Exercise1028 {
    public static void main(String[] args) {
//        String str = "1-2--3--4-5--6--7";
//        String str = "1-2--3---4-5--6---7";
        String str = "1-401--349---90--88";

        new Exercise1028().recoverFromPreorder(str);
    }

    // 1028. 从先序遍历还原二叉树
    // 我们从二叉树的根节点 root开始进行深度优先搜索。
    // 在遍历中的每个节点处，我们输出D条短划线（其中D是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
    // 如果节点只有一个子节点，那么保证该子节点为左子节点。
    // 给出遍历输出S，还原树并返回其根节点root。
    public TreeNode recoverFromPreorder(String traversal) {
        if (traversal == null || traversal.length() == 0) {
            return null;
        }
        int index = 0;
        String firstNum = getNextInt(traversal, index);
        if (firstNum.length() == traversal.length()) {
            return new TreeNode(Integer.parseInt(firstNum));
        }
        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[] {0, Integer.parseInt(firstNum)});
        index = firstNum.length();
        do {
            int num = getNextNum(traversal, index);
            String str = getNextInt(traversal, index + num);
            index = index + num + str.length();
            queue.add(new int[] {num, Integer.parseInt(str)});
        } while (index < traversal.length());
        TreeNode root = new TreeNode(Integer.parseInt(firstNum));
        List<List<TreeNode>> list = new ArrayList<>();
        list.add(new ArrayList() {
            {
                add(root);
            }
        });
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] res = queue.poll();
            List<TreeNode> listNodes = list.get(res[0] - 1);
            TreeNode treeNode = listNodes.get(listNodes.size() - 1);
            TreeNode node = new TreeNode(res[1]);
            if (treeNode.left == null) {
                treeNode.left = node;
            } else {
                treeNode.right = node;
            }
            if (res[0] >= list.size()) {
                list.add(new ArrayList<>());
            }
            list.get(res[0]).add(node);
        }
        return root;
    }

    private String getNextInt(String traversal, int index) {
        StringBuilder valStr = new StringBuilder();
        Character ch = null;
        while (index < traversal.length()) {
            ch = traversal.charAt(index++);
            if (ch - '0' >= 0 && ch - '0' <= 9) {
                valStr.append(ch);
                continue;
            }
            break;
        }
        return valStr.toString();
    }

    // 返回'-'的长度
    private int getNextNum(String traversal, int index) {
        int num = 0;
        while (index < traversal.length()) {
            if (traversal.charAt(index++) == '-') {
                num++;
            } else {
                break;
            }
        }
        return num;
    }
}
