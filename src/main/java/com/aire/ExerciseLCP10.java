package com.aire;

import com.aire.base.TreeNode;

import java.util.*;

/**
 * Created on 2021/10/12 8:50 下午.
 *
 * @Author ZhuPeipei
 */
public class ExerciseLCP10 {
    public static void main(String[] args) {

    }

    // 这里有个重点 就是后序遍历 一定要搞清楚

    // LCP 10. 二叉树任务调度
    // 任务调度优化是计算机性能优化的关键任务之一。在任务众多时，不同的调度策略可能会得到不同的总体执行时间，因此寻求一个最优的调度方案是非常有必要的。
    // 通常任务之间是存在依赖关系的，即对于某个任务，你需要先完成他的前导任务（如果非空），才能开始执行该任务。我们保证任务的依赖关系是一棵二叉树，其中 root 为根任务，root.left 和 root.right 为他的两个前导任务（可能为空），root.val 为其自身的执行时间。
    // 在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。在下次继续执行该任务时，会从之前停留的进度开始继续执行。暂停的时间可以不是整数。
    // 现在，系统有两个 CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。给定这颗任务树，请求出所有任务执行完毕的最小时间。
    public double minimalExecTime(TreeNode root) {
        Map<TreeNode, float[]> map = new HashMap<>(); // int[] 1代表总和 2代表局部最优值

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        List<Integer> res = new ArrayList<>();

        TreeNode preNode = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop(); // 这里node 不需要考虑 左节点
            if (node.right == null || node.right == preNode) {
                res.add(node.val);

                float sl = 0, sr = 0, fl = 0, fr = 0;
                if (node.left != null) {
                    float[] val = map.get(node.left);
                    sl = val[0];
                    fl = val[1];
                }
                if (node.right != null) {
                    float[] val = map.get(node.right);
                    sr = val[0];
                    fr = val[1];
                }
                float s = sl + sr + node.val;
                float f = Math.max((sl + sr) / 2f, Math.max(fl, fr)) + node.val;
                map.put(node, new float[]{s, f});

                preNode = node;
                node = null;
            } else {
                stack.push(node);
                node = node.right;
                preNode = null;
            }
        }
        return map.get(root)[1];
    }
}
