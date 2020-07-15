package com.aire;

public class Exercise065 {
    public static void main(String[] args) {
        System.out.println(new Exercise065().isNumber("3."));
    }

    public int make(char c) {
        switch(c) {
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': return 4;
            default:
                if(c >= 48 && c <= 57) return 2;
        }
        return -1;
    }

    public boolean isNumber(String s) {
        int state = 0;
        int finals = 0b101101000;
        int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                {-1,-1, 6, 2,-1},
                {-1,-1, 3,-1,-1},
                { 8,-1, 3,-1, 4},
                {-1, 7, 5,-1,-1},
                { 8,-1, 5,-1,-1},
                { 8,-1, 6, 3, 4},
                {-1,-1, 5,-1,-1},
                { 8,-1,-1,-1,-1}};
        char[] ss = s.toCharArray();
        for(int i=0; i < ss.length; ++i) {
            int id = make(ss[i]);
            if (id < 0) return false;
            state = transfer[state][id];
            if (state < 0) return false;
        }
        return (finals & (1 << state)) > 0;
    }


//    public boolean isNumber(String s) {
//        if (s == null || s.length() == 0) {
//            return false;
//        }
//        int state = 0;
//        int[][] transfer = new int[][]{
//                {0, 1, 2, -1, 9},
//                {-1, -1, 2, -1, 9},
//                {8, -1, 2, 5, 3},
//                {8, -1, 4, 5, -1},
//                {8, -1, 4, 5, -1},
//                {-1, 6, 7, -1, -1},
//                {-1, -1, 7, -1, -1},
//                {8, -1, 7, -1, -1},
//                {8, -1, -1, -1, -1},
//                {-1, -1, 4, -1, -1}
//        };
//        for (int i = 0; i < s.length(); i++) {
//            int index = make(s.charAt(i));
//            if (index == -1) {
//                return false;
//            }
//            state = transfer[state][index];
//            if (state == -1) {
//                return false;
//            }
//        }
//        return state == 2 || state == 3 || state == 4 || state == 7 || state == 8;
//    }
//
//    private int make(Character ch) {
//        if (ch == ' ') {
//            return 0;
//        }
//        if (ch == '+' || ch == '-') {
//            return 1;
//        }
//        if (ch - '0' >= 0 && ch - '0' <= 9) {
//            return 2;
//        }
//        if (ch == 'e') {
//            return 3;
//        }
//        if (ch == '.') {
//            return 4;
//        }
//        return -1;
//    }
}
