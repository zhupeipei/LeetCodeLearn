package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/27 17:12
 */
public class Exercise005_1 {
    public static void main(String[] args) {
//        String s = "babad";
        String s = "cbbd";
        System.out.println(new Exercise005_1().longestPalindrome(s));
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        // fij = f(i+1)(j-1) & s(i)==s(j)
        boolean[][] f = new boolean[s.length()][s.length()];
        int maxLen = 0;
        int m = 0, n = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            f[i][i] = true;
            if (maxLen < 1) {
                maxLen = 1;
                m = n = i;
            }
            for (int j = i + 1; j < s.length(); j++) {
                f[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 >= j - 1 || f[i + 1][j - 1]);
                if (f[i][j]) {
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        m = i;
                        n = j;
                    }
                }
            }
        }
        return s.substring(m, n + 1);
    }
}
