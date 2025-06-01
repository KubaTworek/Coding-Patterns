package pl.jakubtworek.hard.dynamic_programming;

import java.util.Arrays;
import java.util.List;

class OneDimensionalDP {

    /**
     * Wersja rekurencyjna z memoizacją (top-down).
     * Dla każdego `n` oblicza wynik rekurencyjnie i zapamiętuje rezultaty
     * w tablicy memo, by uniknąć ponownych obliczeń tych samych wartości.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n) — na tablicę memo i stos wywołań rekurencyjnych.
     */
    static int climbingStairsTopDown(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return climb(n, memo);
    }

    private static int climb(int n, int[] memo) {
        if (n <= 2) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);
        return memo[n];
    }

    /**
     * Wersja iteracyjna (bottom-up).
     * Bazuje na idei ciągu Fibonacciego — liczy od dołu do góry, używając tylko dwóch zmiennych.
     * Zamiast tablicy, zapamiętuje tylko dwie ostatnie wartości — O(1) pamięci.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(1)
     */
    static int climbingStairsBottomUp(int n) {
        if (n <= 2) return n;

        int twoStepsBefore = 1;
        int oneStepBefore = 2;

        for (int i = 3; i <= n; i++) {
            int current = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = current;
        }

        return oneStepBefore;
    }

    /**
     * Rekurencyjne rozwiązanie problemu z memoizacją (Top-Down).
     *
     * Dla każdej kwoty `amount` próbujemy użyć każdej monety i rekurencyjnie
     * obliczamy minimalną liczbę monet potrzebnych do pokrycia pozostałej kwoty.
     *
     * Złożoność czasowa: O(n * m) — n: target, m: liczba monet
     * Złożoność pamięciowa: O(n) — tablica memo
     */
    static int minimumCoinCombinationTopDown(List<Integer> coins, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -2); // -2: nieobliczone jeszcze
        int result = minCoins(coins, target, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int minCoins(List<Integer> coins, int target, int[] memo) {
        if (target == 0) return 0;
        if (target < 0) return Integer.MAX_VALUE;
        if (memo[target] != -2) return memo[target];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = minCoins(coins, target - coin, memo);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, res + 1);
            }
        }

        memo[target] = min;
        return memo[target];
    }

    /**
     * Iteracyjne rozwiązanie (Bottom-Up) z dynamicznym programowaniem.
     *
     * dp[i] oznacza minimalną liczbę monet potrzebnych do uzyskania kwoty i.
     * Wypełniamy tablicę dp od 0 do target, używając każdego nominału monet.
     *
     * Złożoność czasowa: O(n * m)
     * Złożoność pamięciowa: O(n)
     */
    static int minimumCoinCombinationBottomUp(List<Integer> coins, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, target + 1); // wartość większa niż jakikolwiek możliwy wynik
        dp[0] = 0;

        for (int i = 1; i <= target; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[target] > target ? -1 : dp[target];
    }

    /**
     * Zwraca maksymalną sumę pieniędzy, jaką można ukraść z listy domów,
     * nie okradając dwóch sąsiadujących.
     *
     * Wersja z memoizacją:
     * - W każdym kroku decydujemy: okraść bieżący dom i pominąć kolejny,
     *   lub nie kraść i przejść do następnego.
     * - Zapamiętujemy wynik dla każdego indeksu, by uniknąć powtórnych obliczeń.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n)
     */
    static int neighborhoodBurglary(List<Integer> houses) {
        int[] memo = new int[houses.size()];
        Arrays.fill(memo, -1);
        return robFrom(0, houses, memo);
    }

    private static int robFrom(int i, List<Integer> houses, int[] memo) {
        if (i >= houses.size()) return 0;
        if (memo[i] != -1) return memo[i];

        int rob = houses.get(i) + robFrom(i + 2, houses, memo);
        int skip = robFrom(i + 1, houses, memo);
        memo[i] = Math.max(rob, skip);
        return memo[i];
    }

    /**
     * Zwraca największą sumę ciągłej podtablicy (maximum subarray sum).
     * Klasyczne dynamiczne programowanie z memoizacją:
     * - W każdym indeksie decydujemy:
     *   - rozpocząć nową sumę od nums[i],
     *   - kontynuować sumowanie z poprzednim wynikiem.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n) na memo (lub O(1) bez memoizacji)
     */
    static int maximumSubarraySum(List<Integer> nums) {
        int[] memo = new int[nums.size()];
        memo[0] = nums.get(0);
        int max = memo[0];

        for (int i = 1; i < nums.size(); i++) {
            memo[i] = Math.max(nums.get(i), nums.get(i) + memo[i - 1]);
            max = Math.max(max, memo[i]);
        }

        return max;
    }
}
