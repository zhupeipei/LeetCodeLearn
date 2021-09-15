package com.aire.stack;

import java.util.Stack;

/**
 * Created on 2021/9/15 10:00 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise001 {
    // 有效的括号
    public static void main(String[] args) {
        String s = "([}}])";
        System.out.println(new Exercise001().isValid(s));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == character) {
                stack.pop();
            } else {
                if (character == '(' || character == '[' || character == '{') {
                    stack.push(getTwinChar(character));
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private Character getTwinChar(Character ch) {
        if (ch == '(') return ')';
//        if (ch == ')') return '(';
        if (ch == '[') return ']';
//        if (ch == ']') return '[';
        if (ch == '{') return '}';
//        if (ch == '}') return '{';
        // 走到这里已经应该报错了
        return ch;
    }
}
