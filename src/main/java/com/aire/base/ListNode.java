package com.aire.base;

/**
 * Created on 2021/10/26 10:33 下午.
 *
 * @Author ZhuPeipei
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
