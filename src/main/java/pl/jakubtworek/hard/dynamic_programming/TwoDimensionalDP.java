package pl.jakubtworek.hard.dynamic_programming;

import java.util.List;

class TwoDimensionalDP {
    /**
     * Zwraca liczbę unikalnych ścieżek w siatce m x n,
     * zaczynając w lewym górnym rogu i kończąc w prawym dolnym,
     * poruszając się tylko w dół lub w prawo.
     *
     * Używa dynamicznego programowania (bottom-up).
     * dp[i][j] = liczba dróg do pola (i, j)
     *
     * Złożoność czasowa: O(m * n)
     * Złożoność pamięciowa: O(m * n)
     */
    static int matrixPathways(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Zwraca najdłuższy palindromiczny podciąg (substring) w danym ciągu.
     * Używa dynamicznego programowania (dp[i][j] = czy s[i..j] jest palindromem).
     *
     * Złożoność czasowa: O(n^2)
     * Złożoność pamięciowa: O(n^2)
     */
    static String longestPalindromeInString(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        for (int i = 0; i < n; i++) dp[i][i] = true;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            start = i;
                            maxLen = len;
                        }
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    /**
     * Zwraca długość najdłuższego wspólnego podciągu (LCS) dwóch ciągów.
     *
     * dp[i][j] = LCS dla s1[0..i-1], s2[0..j-1]
     *
     * Złożoność czasowa: O(m * n)
     * Złożoność pamięciowa: O(m * n)
     */
    static int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Rozwiązuje klasyczny problem 0/1 Knapsack.
     * Maksymalizuje sumę wartości przy ograniczeniu wagowym `cap`.
     *
     * dp[i][w] = maksymalna wartość przy użyciu pierwszych i przedmiotów
     * i mając do dyspozycji wagę w.
     *
     * Złożoność czasowa: O(n * cap)
     * Złożoność pamięciowa: O(n * cap)
     */
    static int ZeroOneKnapsack(int cap, List<Integer> weights, List<Integer> values) {
        int n = weights.size();
        int[][] dp = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; i++) {
            int wt = weights.get(i - 1);
            int val = values.get(i - 1);
            for (int w = 0; w <= cap; w++) {
                if (wt > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt] + val);
                }
            }
        }

        return dp[n][cap];
    }
}
