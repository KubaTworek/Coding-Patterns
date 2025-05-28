package pl.jakubtworek.medium.prefix_sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ProductSums {

    /**
     * Zwraca nową listę, w której każdy element to iloczyn wszystkich pozostałych elementów wejściowej listy.
     * Nie używa dzielenia. Działa w czasie O(n) i zużywa dodatkową pamięć O(n).
     *
     * Algorytm:
     * 1. Tworzy dwie pomocnicze tablice:
     *    - prefix[i] = iloczyn wszystkich elementów przed indeksem i
     *    - suffix[i] = iloczyn wszystkich elementów po indeksie i
     * 2. Wynik dla pozycji i to: prefix[i] * suffix[i]
     *
     * Przykład:
     * [1, 2, 3, 4] → [24, 12, 8, 6]
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n)
     */
    static List<Integer> productArrayWithoutCurrentElement(List<Integer> nums) {
        int n = nums.size();
        if (n == 0) return Collections.emptyList();
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 1));
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        // Oblicz prefix produkty
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums.get(i - 1);
        }

        // Oblicz suffix produkty
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums.get(i + 1);
        }

        System.out.println(Arrays.toString(prefix));
        System.out.println(Arrays.toString(suffix));
        // Oblicz wynik: prefix[i] * suffix[i]
        for (int i = 0; i < n; i++) {
            result.set(i, prefix[i] * suffix[i]);
        }

        return result;
    }
}
