package pl.jakubtworek.hard.greedy;

import java.util.Arrays;
import java.util.List;

class ResourceAllocation {

    /**
     * Zadanie:
     * Dla każdego dziecka o ocenie `ratings[i]`, daj co najmniej 1 cukierek.
     * Dziecko z wyższą oceną niż sąsiad (i±1) musi dostać więcej cukierków.
     *
     * Cel: Minimalizuj łączną liczbę cukierków.
     *
     * Algorytm:
     * 1. Przejdź z lewej do prawej:
     *    - jeśli rating[i] > rating[i-1], daj więcej niż poprzedni
     * 2. Przejdź z prawej do lewej:
     *    - jeśli rating[i] > rating[i+1], upewnij się, że cukierków jest więcej niż po prawej
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n)
     */
    static int candies(List<Integer> ratings) {
        int n = ratings.size();
        if (n == 0) return 0;

        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        // lewa strona
        for (int i = 1; i < n; i++) {
            if (ratings.get(i) > ratings.get(i - 1)) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        // prawa strona
        for (int i = n - 2; i >= 0; i--) {
            if (ratings.get(i) > ratings.get(i + 1)) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        return Arrays.stream(candy).sum();
    }
}
