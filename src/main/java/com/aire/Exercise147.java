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
        t.insertionSortList(root);
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
        ListNode rootNode = new ListNode();
        rootNode.next = head;

        ListNode selectParentNode = rootNode;
        while (selectParentNode.next != null) {
            ListNode compareParentNode = rootNode;
            boolean insertSuccess = false;
            while (compareParentNode.next != null && compareParentNode.next != selectParentNode.next) {
                if (compareParentNode.next.val > selectParentNode.next.val) {
                    ListNode temp = compareParentNode.next;
                    compareParentNode.next = selectParentNode.next;
                    selectParentNode.next = temp;
                    insertSuccess = true;
                }
                compareParentNode = compareParentNode.next;
            }
            if (insertSuccess) {
                selectParentNode.next = selectParentNode.next.next;
            }
            selectParentNode = selectParentNode.next;
            printListNode(rootNode);
        }
        return rootNode.next;
    }
}
