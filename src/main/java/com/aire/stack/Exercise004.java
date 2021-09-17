package com.aire.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created on 2021/9/17 8:08 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise004 {
    public static void main(String[] args) {
        String s = "abc3[cd]xyz";
//        String s = "3[a]2[bc]";
//        String s = "100[leetcode]";
        String res = new Exercise004().decodeString(s);
        System.out.println(res);
    }

    // 字符串解码
    public String decodeString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char chi = s.charAt(i);
            if (stack.isEmpty() || chi != ']') {
                stack.push(chi);
            } else {
                Character chx = null;
                StringBuilder sb = new StringBuilder();
                while ((chx = stack.pop()) != '[') {
                    sb.append(chx); // 这里顺序是反的 后面再次取值 又会变回来
                }
                int num = calNum(stack);
                for (int j = 0; j < num; j++) {
                    for (int m = sb.length() - 1; m >= 0; m--) {
                        stack.push(sb.charAt(m));
                    }
                }
            }
        }
        char[] chars = new char[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            chars[i] = stack.pop();
        }
        return new String(chars);
    }

    private int calNum(Deque<Character> stack) {
        if (stack.isEmpty() || !(stack.peek() - '0' >= 0 && stack.peek() - '9' <= 0)) {
            return 0;
        }
        return (stack.pop() - '0') + 10 * calNum(stack);
    }
}
