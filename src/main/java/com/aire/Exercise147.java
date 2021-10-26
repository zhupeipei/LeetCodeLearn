package com.aire;

import com.aire.base.ListNode;

/**
 * Created on 2021/10/26 10:32 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise147 {
    public static void main(String[] args) {
//        4->2->1->3
//        1->2->3->4
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        Exercise147 t = new Exercise147();//
        root = t.insertionSortList(root);
        t.printListNode(root);
    }

    private void printListNode(ListNode root) {
        ListNode node = root;
        while (node != null) {
            System.out.print(node.val + "-->");
            node = node.next;
        }
        System.out.println();
    }

    // 147. 对链表进行插入排序
    // 对链表进行插入排序。
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }
        ListNode rootNode = new ListNode();
        rootNode.next = head;

        ListNode preNode = head; // 这个表示比较到这个节点结束
        ListNode selectNode = head.next; // 比较的节点
        while (selectNode != null) {
            if (preNode.val <= selectNode.val) {
                preNode = selectNode;
                selectNode = preNode.next;
            } else {
                preNode.next = selectNode.next;
                selectNode.next = null;
                ListNode compareParentNode = rootNode; // 循环比较节点的父节点
                while (compareParentNode != preNode) {
                    if (compareParentNode.next.val > selectNode.val) {
                        ListNode tmp = compareParentNode.next;
                        compareParentNode.next = selectNode;
                        selectNode.next = tmp;
                        break;
                    }
                    compareParentNode = compareParentNode.next;
                }
                selectNode = preNode.next;
            }
        }
        return rootNode.next;
    }
}
