package com.aire.stack;

import java.util.Stack;

/**
 * Created on 2021/9/15 9:17 下午.
 *
 * @Author ZhuPeipei
 */
public class StackTest {
    public static void main(String[] args) {
        MinStack obj = new MinStack();
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}

class MinStack {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    public void push(int val) {
        if (stack2.isEmpty() || val < getMin()) {
            stack2.push(val);
        }
        stack1.push(val);
    }

    public void pop() {
        if (stack1.pop() == getMin()) {
            stack2.pop();
        }
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        if (stack1.size() == 0) {
            throw new IllegalStateException();
        }
        return stack2.peek();
    }
}