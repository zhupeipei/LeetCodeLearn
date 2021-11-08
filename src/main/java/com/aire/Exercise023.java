package com.aire;

import com.aire.base.ListNode;

/**
 * Created on 2021/11/9 上午1:10.
 *
 * @Author ZhuPeipei
 */
public class Exercise023 {
    public static void main(String[] args) {

    }

    // 23. 合并K个升序链表
    // 我这种方案 不是最优解
    // 最优解 可以是每次只合并两个数组，通过分而治之的方法来实现
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode preHead = new ListNode(-1);
        ListNode curNode = preHead;
        ListNode node;
        while (true) {
            // 找到最小的node
            node = null;
            int minIndex = 0;
            for (int i = 0; i < lists.length; i++) {
                if (node == null || (lists[i] != null && node.val > lists[i].val)) {
                    node = lists[i];
                    minIndex = i;
                }
            }
            if (node == null) {
                return preHead.next;
            }
            curNode.next = node;
            lists[minIndex] = node.next;
        }
    }
}
