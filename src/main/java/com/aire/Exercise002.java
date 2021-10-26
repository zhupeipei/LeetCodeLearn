package com.aire;

import com.aire.base.ListNode;

public class Exercise002 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
//        listNode1.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(8);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);

        System.out.println(468 + 42);
        ListNode next = new Solution().addTwoNumbers(listNode1, listNode2);
        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return null;
            }
            ListNode result = new ListNode(0);
            int val;
            int add = 0;
            ListNode re = result;
            do {
                if (l1 == null) {
                    val = l2.val;
                } else {
                    val = l1.val + (l2 == null ? 0 : l2.val);
                }
                if (val + add >= 10) {
                    re.val = (val + add) % 10;
                    add = 1;
                } else {
                    re.val = val + add;
                    add = 0;
                }
                if (l1 == null || l1.next == null) {
                    l1 = null;
                } else {
                    l1 = l1.next;
                }

                if (l2 == null || l2.next == null) {
                    l2 = null;
                } else {
                    l2 = l2.next;
                }
                if (l1 == null && l2 == null) {
                    if (add != 0) {
                        re.next = new ListNode(add);
                    }
                    break;
                } else {
                    re.next = new ListNode(0);
                    re = re.next;
                }
            } while (true);
            return result;
        }
    }

}