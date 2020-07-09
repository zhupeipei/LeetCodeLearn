package com.aire;

import java.util.ArrayList;
import java.util.List;

public class Exercise006 {
    public static void main(String[] args) {
        System.out.println(new Exercise006().convert("leetcode", 3));
    }

    public String convert(String s, int numRows) {
        List<StringBuilder> res = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            res.add(new StringBuilder());
        }
        int len = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i++) {
            int rowi = i % len;
            if (rowi + 1 > numRows) {
                rowi = 2 * numRows - rowi - 2;
            }
            StringBuilder sb = res.get(rowi);
            sb.append(s.charAt(i));
        }
        StringBuilder sbRes = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sbRes.append(res.get(i));
        }
        return sbRes.toString();
    }
}
