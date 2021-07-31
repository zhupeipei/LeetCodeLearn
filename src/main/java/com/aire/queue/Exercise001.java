package com.aire.queue;

import java.util.*;

// 打开转盘锁
public class Exercise001 {
    public static void main(String[] args) {
        String[] deadends = new String[]{"5557", "5553", "5575", "5535", "5755", "5355", "7555", "3555", "6655", "6455", "4655", "4455", "5665", "5445", "5645", "5465", "5566", "5544", "5564", "5546", "6565", "4545", "6545", "4565", "5656", "5454", "5654", "5456", "6556", "4554", "4556", "6554"};
        String target = "5555";
        long time = System.currentTimeMillis();
        System.out.println(new Exercise001().openLock(deadends, target));
        System.out.println(System.currentTimeMillis() - time);
    }

    public int openLock(String[] deadends, String target) {
        Set<Integer> deadendsSet = processDeadends(deadends);
        int targetVal = str2Val(target);

        if (targetVal == 0) {
            return 0;
        }
        if (deadendsSet.contains(targetVal) || deadendsSet.contains(0)) {
            return -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        int rootVal = arr2val(0, 0, 0, 0);
        queue.add(rootVal);

        Set<Integer> allVals = new HashSet<>();
        allVals.add(rootVal);

        int cengshu = 0;
        while (!queue.isEmpty()) {
            cengshu++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer val = queue.poll();
                boolean found = bfs(queue, allVals, val2arr(val), deadendsSet, targetVal);
                if (found) {
                    return cengshu;
                }
            }
        }
        return -1;
    }

    private boolean bfs(Queue<Integer> queue, Set<Integer> allVals, int[] val, Set<Integer> deadends, int target) {
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
        for (int i = 0; i < vals.length; i++) {
            int keyVal = vals[i];
            if (allVals.contains(keyVal)) {
                continue;
            }
            allVals.add(keyVal);
            if (deadends.contains(keyVal)) {
                continue;
            }
            if (target == keyVal) {
                return true;
            }
            queue.add(keyVal);
        }
        return false;
    }

    // 老的方法 可以获取 但是耗时较长
    public int openLock1(String[] deadends, String target) {
        Set<Integer> deadendsSet = processDeadends(deadends);
        int targetVal = str2Val(target);

        if (targetVal == 0) {
            return 0;
        }
        if (deadendsSet.contains(targetVal) || deadendsSet.contains(0)) {
            return -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        int rootVal = arr2val(0, 0, 0, 0);
        queue.add(rootVal);
        Set<Integer> allVals = new HashSet<>();
        allVals.add(rootVal);

        TreeNode node = new TreeNode();
        node.rootVal = rootVal;
        node.root = null;

        List<TreeNode> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer val = queue.poll();
            TreeNode node1 = findNode(val, node);
            List<TreeNode> result = bfs(queue, allVals, val2arr(val), deadendsSet, targetVal, node1);
            res.addAll(result);
        }

        if (res.size() == 0) {
            return -1;
        }
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < res.size(); i++) {
            TreeNode node2 = res.get(i);
            minVal = Math.min(minVal, printResult(node2));
        }

        System.out.println(minVal);

        return minVal;
    }

    private int printResult(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (node != null) {
            i++;
            sb.append(val2Str(node.rootVal) + " -> ");
            node = node.root;
        }
        System.out.println(sb.toString() + ", " + i);
        return i - 1;
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
        int val3 = (val >> 4) - ((val >> 8) << 4);
        int val4 = val - ((val >> 4) << 4);
        return new int[]{val1, val2, val3, val4};
    }

    private String val2Str(int val) {
        int val1 = val >> 12;
        int val2 = (val >> 8) - (val1 << 4);
        int val3 = (val >> 4) - ((val >> 8) << 4);
        int val4 = val - ((val >> 4) << 4);
        return val1 + "-" + val2 + "-" + val3 + "-" + val4;
    }

    private int arr2val(int arr0, int arr1, int arr2, int arr3) {
        return (arr0 << 12) + (arr1 << 8) + (arr2 << 4) + arr3;
    }

    private int arr2val(int[] arr) {
        return (arr[0] << 12) + (arr[1] << 8) + (arr[2] << 4) + arr[3];
    }

    private List<TreeNode> bfs(Queue<Integer> queue, Set<Integer> allVals, int[] val, Set<Integer> deadends, int target, TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
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
            if (deadends.contains(keyVal)) {
                continue;
            }
            TreeNode childNode = new TreeNode();
            childNode.rootVal = keyVal;
            childNode.root = node;
            node.childNode.add(childNode);
            if (target == keyVal) {
                nodes.add(childNode);
            }
            queue.add(keyVal);
        }
        return nodes;
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
