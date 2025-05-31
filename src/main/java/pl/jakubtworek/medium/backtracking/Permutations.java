package pl.jakubtworek.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

class Permutations {

    /**
     * Zwraca wszystkie możliwe permutacje listy liczb całkowitych.
     *
     * Algorytm:
     * - Wykorzystuje backtracking: wybieramy kolejne liczby, pomijając już użyte.
     * - Na każdym etapie eksplorujemy wszystkie dostępne możliwości.
     *
     * Złożoność czasowa: O(n!), gdzie n = liczba elementów.
     * Złożoność pamięciowa: O(n!) dla przechowywania wyniku.
     */
    static List<List<Integer>> findAllPermutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new boolean[nums.size()], result);
        return result;
    }

    private static void backtrack(List<Integer> nums, List<Integer> temp, boolean[] used, List<List<Integer>> result) {
        if (temp.size() == nums.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (used[i]) continue;
            used[i] = true;
            temp.add(nums.get(i));
            backtrack(nums, temp, used, result);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    /**
     * Zwraca liczbę rozwiązań problemu N-hetmanów (N-Queens).
     *
     * Cel: Umieścić n hetmanów na szachownicy n x n tak, by żadne się nie atakowały.
     *
     * Algorytm:
     * - Używa rekurencji i backtrackingu.
     * - Na każdym poziomie próbuje postawić hetmana w kolejnych kolumnach.
     * - Sprawdza, czy pozycja nie jest zagrożona przez wcześniej postawione hetmany.
     *
     * Złożoność czasowa: O(n!), z optymalizacjami można zejść do O(n^n).
     * Złożoność pamięciowa: O(n)
     */
    static int nQueens(int n) {
        return placeQueens(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }

    private static int placeQueens(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) return 1;

        int count = 0;
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;
            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            cols[col] = diag1[d1] = diag2[d2] = true;
            count += placeQueens(row + 1, n, cols, diag1, diag2);
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
        return count;
    }
}
