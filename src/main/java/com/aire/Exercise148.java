package com.aire;

import com.aire.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/10/27 9:40 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise148 {
    public static void main(String[] args) {
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        root = new Exercise148().sortList(root);
        while (root != null) {
            System.out.print(root.val + "-->");
            root = root.next;
        }
        System.out.println();
    }

    // 148. 排序链表
    // 给你链表的头结点head，请将其按 升序 排列并返回 排序后的链表 。
    // 进阶：
    // 你可以在O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
    public ListNode sortList(ListNode head) {
        // 插入、希尔排序
        // 归并排序
        // 冒泡、快速排序
        // 选择、堆排序
        // 方法采用 插入排序
//        return insertSort(head);
        // 方法采用 归并排序 (比较low的归并排序)
//        return mergeSort(head);
        // 方法采用 归并排序（自上而下）
        // 方法采用 归并排序（自下而上）
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        // 这里就转换成了list 然后对list做归并排序
        int middle = list.size() / 2;
        List<ListNode> nodeList = merge(mergeInner(list.subList(0, middle)), mergeInner(list.subList(middle, list.size())));
        for (int i = 0; i < nodeList.size(); i++) {
            ListNode next = null;
            if (i + 1 < nodeList.size()) {
                next = nodeList.get(i + 1);
            }
            nodeList.get(i).next = next;
        }
        return nodeList.get(0);
    }

    private List<ListNode> mergeInner(List<ListNode> root) {
        if (root.size() <= 1) {
            return root;
        }
        int middle = root.size() / 2;
        return merge(mergeInner(root.subList(0, middle)), mergeInner(root.subList(middle, root.size())));
    }

    private List<ListNode> merge(List<ListNode> left, List<ListNode> right) {
        if (left == null || left.size() <= 0) {
            return right;
        }
        if (right == null || right.size() <= 0) {
            return left;
        }
        List<ListNode> list = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        while (true) {
            if (leftIndex >= left.size() && rightIndex >= right.size()) {
                break;
            }
            if (leftIndex >= left.size()) {
                list.add(right.get(rightIndex));
                rightIndex++;
            } else if (rightIndex >= right.size()) {
                list.add(left.get(leftIndex));
                leftIndex++;
            } else if (left.get(leftIndex).val > right.get(rightIndex).val) {
                list.add(right.get(rightIndex));
                rightIndex++;
            } else {
                list.add(left.get(leftIndex));
                leftIndex++;
            }
        }
        return list;
    }

    private ListNode insertSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode root = new ListNode(0, head);
        ListNode lastEndNode = head;
        ListNode selectNode = head.next;

        while (selectNode != null) {
            if (selectNode.val >= lastEndNode.val) {
                selectNode = lastEndNode;
                lastEndNode = selectNode.next;
            } else {
                lastEndNode.next = selectNode.next;
                selectNode.next = null;
                ListNode pre = root;
                while (pre.next.val < selectNode.val) {
                    pre = pre.next;
                }
                ListNode tmp = pre.next;
                pre.next = selectNode;
                selectNode.next = tmp;

                selectNode = lastEndNode.next;
            }
        }
        return root.next;
    }

}
