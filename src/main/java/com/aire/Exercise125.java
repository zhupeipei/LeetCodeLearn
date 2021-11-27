package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/27 14:37
 */
public class Exercise125 {
    public static void main(String[] args) {
//        System.out.println(new Exercise125().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new Exercise125().isPalindrome("0P"));

    }

    // 125. 验证回文串
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char ch = s.charAt(i);
            if (!isValidChar(ch)) {
                i++;
                continue;
            }
            char ch2 = s.charAt(j);
            if (!isValidChar(ch2)) {
                j--;
                continue;
            }
            if (covertToMinChar(ch) == covertToMinChar(ch2)) {
                i++;
                j--;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isValidChar(Character ch) {
        return (ch - 'a' >= 0 && ch - 'z' <= 0) || (ch - 'A' >= 0 && ch - 'Z' <= 0) || (ch - '0' >= 0 && ch - '9' <= 0);
    }

    private char covertToMinChar(char ch) {
        if (ch - 'A' >= 0 && ch - 'Z' <= 0) {
            return (char) (ch - 'A' + 'a');
        }
        return ch;
    }
}
