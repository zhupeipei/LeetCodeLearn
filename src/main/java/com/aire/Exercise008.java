package com.aire;

public class
Exercise008 {
    public static void main(String[] args) {
        System.out.println(new Exercise008().myAtoi("-2147483649"));
    }

    /**
     * 1. 可以使用有限状态机来完成
     * 2. 其次，可以将int改成long会更好些
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        boolean positive = true;
        int num = 0;
        Character chi = str.charAt(0);
        if (chi == '+' || chi == '-' || (chi >= '0' && chi <= '9')) {
            if (chi == '-') {
                positive = false;
            } else if (chi == '+') {
            } else {
                num = chi - '0';
            }
        } else {
            return 0;
        }
        for (int i = 1; i < str.length(); i++) {
            chi = str.charAt(i);
            if (chi >= '0' && chi <= '9') {
                if (positive) {
                    if (num < Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && (chi - '0') <= Integer.MAX_VALUE % 10)) {
                        num = num * 10 + chi - '0';
                    } else {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    if (num > Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && ('0' - chi) >= Integer.MIN_VALUE % 10)) {
                        num = num * 10 + -1 * (chi - '0');
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
            } else {
                return num;
            }
        }
        return num;
    }
}
