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
        ListNode root = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
//        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
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
//        return mergeSort1(head);
        // 方法采用 归并排序（自下而上）
        return mergeSort2(head);
    }

    private ListNode mergeSort2(ListNode head) {
        ListNode root = new ListNode();
        root.next = head;

        ListNode node = head;
        int num = 0;
        while (node != null) {
            num++;
            node = node.next;
        }

        ListNode head1Par = root;
        ListNode head2Par = null;
        ListNode head1 = null;
        ListNode head2 = null;

        ListNode preNode = root;
        for (int step = 1; step < num; step *= 2) {
            head1Par = root;
            preNode = root;
            while (head1Par.next != null) {
                ListNode cur = head1Par;
                head1 = head1Par.next;
                for (int i = 0; i < step && cur != null; i++) {
                    cur = cur.next;
                }
                if (cur == null || cur.next == null) {
                    preNode.next = head1Par.next;
                    break;
                }
                head2Par = cur;
                head2 = head2Par.next;
                head2Par.next = null;
                cur = new ListNode(0, head2);
                for (int i = 0; i < step && cur != null; i++) {
                    cur = cur.next;
                }
                if (cur == null || cur.next == null) {
                    head1Par.next = mergeNodes2(head1, head2);
                    preNode.next = head1Par.next;
                    break;
                }
                ListNode tmp = cur.next;
                cur.next = null;
                head1Par.next = mergeNodes2(head1, head2);
                preNode.next = head1Par.next;
                preNode = head1Par;
                while (preNode.next != null) {
                    preNode = preNode.next;
                }
                head1Par = new ListNode(0, tmp);
            }
        }
        return root.next;
    }

    private ListNode mergeNodes2(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode root;
        if (left.val > right.val) {
            root = right;
            right = right.next;
            root.next = null;
        } else {
            root = left;
            left = left.next;
            root.next = null;
        }
        ListNode node = root;
        while (left != null && right != null) {
            if (left.val > right.val) {
                node.next = right;
                right = right.next;
                node = node.next;
            } else {
                node.next = left;
                left = left.next;
                node = node.next;
            }
        }
        if (left == null) {
            node.next = right;
        }
        if (right == null) {
            node.next = left;
        }
        return root;
    }

    private ListNode mergeSort1(ListNode head) {
        int len = 0;
        int middleIndex = 0;
        ListNode middleNode = head;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
            if (node != null) {
                len++;
                middleNode = middleNode.next;
                middleIndex++;
                node = node.next;
            }
        }
        if (len == 1) {
            return head;
        }
        if (len == 2) {
            ListNode right = head.next;
            head.next = null;
            return mergeNodes(head, right);
        }
        if (middleNode == null) {
            return head;
        }
        ListNode right = middleNode.next;
        middleNode.next = null;
        if (len <= 1) {
            return head;
        }
        return mergeNodes(mergeSort1(head), mergeSort1(right));
    }

    private ListNode mergeNodes(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode root;
        if (left.val < right.val) {
            root = left;
            left = left.next;
            root.next = null;
        } else {
            root = right;
            right = right.next;
            root.next = null;
        }
        ListNode node = root;
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            if (node.next != null) {
                node = node.next;
            }
            if (node.next != null) {
                node.next = null;
            }
        }
        if (left == null) {
            node.next = right;
        }
        if (right == null) {
            node.next = left;
        }
        return root;
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
