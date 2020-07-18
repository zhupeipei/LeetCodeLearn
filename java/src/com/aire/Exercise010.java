package com.aire;

public class Exercise010 {
    public static void main(String[] args) {
        System.out.println(new Exercise010().isMatch("", "c*c*"));
//        System.out.println(new Exercise010().isMatch("aab", "b.*"));
    }

    /**
     * state 1 a* 2 . 3 .* 4 a-z
     */
    public boolean isMatch(String s, String p) {
        if (p == null || s == null) {
            return false;
        }
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        dp[0][0] = 1; // 1 true -1 false
        for (int j = 0; j < p.length(); j++) {
            Character chp = p.charAt(j);
            Character chp1 = j + 1 >= p.length() ? null : p.charAt(j + 1);
            int state;
            if (chp == '.' && (chp1 != null && chp1 == '*')) {
                state = 3;
                j++;
            } else if ((chp != '.' && chp != '*') && (chp1 == null || chp1 != '*')) {
                state = 4;
            } else if ((chp != '.' && chp != '*') && chp1 != null && chp1 == '*') {
                state = 1;
                j++;
            } else {
                state = 2;
            }
            // 边界条件赋值
            if (state == 1) {
                dp[0][j + 1] = dp[0][j - 1];
            }
            if (state == 3) {
                if (j == 1) {
                    dp[0][2] = 1;
                }
                dp[0][j] = dp[0][j - 1];
            }
            if (state == 4) {
                dp[0][j + 1] = -1;
            }
            for (int i = 0; i < s.length(); i++) {
                Character chs = s.charAt(i);
                if (state == 4) {
                    if (chp == chs) {
                        dp[i + 1][j + 1] = dp[i][j];
                    } else {
                        dp[i + 1][j + 1] = -1;
                    }
                } else if (state == 1) {
                    if (chs == chp) {
                        if (i == 0 && j == 0) {
                            dp[i][j + 1] = 1;
                        } else if (i == 0) {
                            dp[i][j + 1] = dp[i][j - 1];
                        }
                        if (j == 1) {
                            if (i == 0) {
                                dp[i + 1][j - 1] = -1;
                            } else {
                                dp[i + 1][j - 1] = -1;
                            }
                        }
                        dp[i + 1][j + 1] = dp[i][j + 1] == 1 || dp[i + 1][j - 1] == 1 ? 1 : -1;
                    } else {
                        if (j - 2 < 0) {
                            dp[i + 1][j + 1] = -1;
                        } else {
                            dp[i + 1][j + 1] = dp[i + 1][j - 1];
                        }
                    }
                } else if (state == 3) {
                    if (i == 0) {
                        dp[i][j + 1] = dp[i][j - 1];
                    }
                    if (j == 1) {
                        dp[i + 1][j - 1] = -1;
                    }
                    dp[i + 1][j + 1] = dp[i][j + 1] == 1 || dp[i + 1][j - 1] == 1 ? 1 : -1;
                } else if (state == 2) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
            }
        }
        return dp[s.length()][p.length()] == 1;
    }
}
