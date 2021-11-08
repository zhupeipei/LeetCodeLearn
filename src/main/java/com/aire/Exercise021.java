package com.aire;

import com.aire.base.ListNode;

/**
 * Created on 2021/11/9 上午12:46.
 *
 * @Author ZhuPeipei
 */
public class Exercise021 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        new Exercise021().mergeTwoLists(l1, l2);
    }

    // 21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null, node = null, node1 = l1, node2 = l2;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                if (head == null) {
                    head = node1;
                    node = node1;
                } else {
                    node.next = node1;
                    node = node.next;
                }
                node1 = node1.next;
            } else {
                if (head == null) {
                    head = node2;
                    node = node2;
                } else {
                    node.next = node2;
                    node = node.next;
                }
                node2 = node2.next;
            }
        }
        if (node1 != null) {
            node.next = node1;
        }
        if (node2 != null) {
            node.next = node2;
        }
        return head;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head, node, node1 = l1, node2 = l2;
        if (l1.val < l2.val) {
            head = l1;
            node1 = l1.next;
        } else {
            head = l2;
            node2 = l2.next;
        }
        node = head;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                node.next = node1;
                node1 = node1.next;
            } else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if (node1 != null) {
            node.next = node1;
        }
        if (node2 != null) {
            node.next = node2;
        }
        return head;
    }
}
