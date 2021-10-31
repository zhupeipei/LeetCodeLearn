package com.aire.base;

/**
 * Created on 2021/11/1 上午1:01.
 *
 * @Author ZhuPeipei
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
