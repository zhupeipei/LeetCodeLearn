package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/12/1 10:28
 */
public class Exercise344 {
    public static void main(String[] args) {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        new Exercise344().reverseString(s);
        System.out.println(s);
    }

    // 344. 反转字符串
    public void reverseString(char[] s) {
        int j, len = s.length;
        for (int i = 0; i < len - i - 1; i++) {
            j = len - i - 1;
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }
}
