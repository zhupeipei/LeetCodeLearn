package com.aire;

import java.util.ArrayList;
import java.util.List;

public class Exercise007 {
    public static void main(String[] args) {
        System.out.println(new Exercise007().reverse(-123));
    }

    public int reverse(int x) {
        int mod;
        int res = 0;
        while (x != 0) {
            mod = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && mod > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (mod < Integer.MIN_VALUE % 10 && res == Integer.MIN_VALUE / 10)) {
                return 0;
            }
            res = res * 10 + mod;
        }
        return res;
    }
}
