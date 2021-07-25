package com.aire.queue;

import java.util.*;

// 打开转盘锁
public class Exercise001 {
    public static void main(String[] args) {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        new Exercise001().openLock(deadends, target);
    }

    public int openLock(String[] deadends, String target) {
        Set<Integer> deadendsSet = processDeadends(deadends);
        int targetVal = str2Val(target);

        Queue<Integer> queue = new LinkedList<>();
        int rootVal = arr2val(0, 0, 0, 0);
        queue.add(rootVal);
        Set<Integer> allVals = new HashSet<>();
        allVals.add(rootVal);

        TreeNode node = new TreeNode();
        node.rootVal = rootVal;

        int cengshu = 1;
        while (!queue.isEmpty()) {
            Integer val = queue.poll();

            TreeNode node1 = findNode(val, node);

            cengshu++;
            int result = bfs(queue, allVals, val2arr(val), deadendsSet, targetVal, node1);
            if (result != -1) {
                System.out.println("result: " + result + ", cengshu: " + cengshu);
                break;
            }
        }

        System.out.println("第" + cengshu + "层");

        return -1;
    }

    private TreeNode findNode(int val, TreeNode node) {
        if (node.rootVal == val) {
            return node;
        } else {
            if (node.childNode != null) {
                for (int i = 0; i < node.childNode.size(); i++) {
                    TreeNode childNode = node.childNode.get(i);
                    if (childNode != null) {
                        if (childNode.rootVal == val) {
                            return childNode;
                        } else {
                            TreeNode node1 = findNode(val, childNode);
                            if (node1 != null) {
                                return node1;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private Set<Integer> processDeadends(String[] deadends) {
        if (deadends == null || deadends.length <= 0) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < deadends.length; i++) {
            String str = deadends[i];
            set.add(str2Val(str));
        }
        return set;
    }

    private int str2Val(String str) {
        if (str == null || str.length() != 4) {
            throw new IllegalArgumentException("无效的输入参数");
        }
        return arr2val((str.charAt(0) - '0'), (str.charAt(1) - '0'), (str.charAt(2) - '0'), (str.charAt(3) - '0'));
    }

    // 整数转成4位数组
    private int[] val2arr(int val) {
        int val1 = val >> 12;
        int val2 = (val >> 8) - (val1 << 4);
        int val3 = (val >> 4) - (val2 << 4);
        int val4 = val - (val3 << 4);
        return new int[]{val1, val2, val3, val4};
    }

    private int arr2val(int arr0, int arr1, int arr2, int arr3) {
        return (arr0 << 12) + (arr1 << 8) + (arr2 << 4) + arr3;
    }

    private int arr2val(int[] arr) {
        return (arr[0] << 12) + (arr[1] << 8) + (arr[2] << 4) + arr[3];
    }

    private int bfs(Queue<Integer> queue, Set<Integer> allVals, int[] val, Set<Integer> deadends, int target, TreeNode node) {
        int val1 = val[0], val2 = val[1], val3 = val[2], val4 = val[3];
        int[] vals = new int[]{
                arr2val(addVal(val1), val2, val3, val4),
                arr2val(minusVal(val1), val2, val3, val4),
                arr2val(val1, addVal(val2), val3, val4),
                arr2val(val1, minusVal(val2), val3, val4),
                arr2val(val1, (val2), addVal(val3), val4),
                arr2val(val1, (val2), minusVal(val3), val4),
                arr2val(val1, (val2), (val3), addVal(val4)),
                arr2val(val1, (val2), (val3), minusVal(val4))
        };
        node.childNode = new ArrayList<>();
        for (int i = 0; i < vals.length; i++) {
            int keyVal = vals[i];
            if (allVals.contains(keyVal)) {
                continue;
            }
            allVals.add(keyVal);
            if (deadends.contains(val)) {
                continue;
            }
            if (target == keyVal) {
                return 1;
            }
            TreeNode childNode = new TreeNode();
            childNode.rootVal = keyVal;
            node.childNode.add(childNode);
            queue.add(keyVal);
        }
        return -1;
    }

    private int addVal(int val) {
        if (val == 9) {
            return 0;
        } else {
            return val + 1;
        }
    }

    private int minusVal(int val) {
        return val == 0 ? 9 : val - 1;
    }

    class TreeNode {
        TreeNode root;
        int rootVal;
        List<TreeNode> childNode;
    }
}
