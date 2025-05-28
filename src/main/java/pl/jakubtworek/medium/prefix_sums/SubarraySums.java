package pl.jakubtworek.medium.prefix_sums;

import java.util.HashMap;
import java.util.Map;

class SubarraySums {
    static int sumBetweenRange(int[] arr, int start, int end) {
        PrefixSum prefixSum = new PrefixSum(arr);
        return prefixSum.rangeSum(start, end);
    }

    /**
     * Zwraca liczbę podtablic (subarray) w tablicy arr, których suma elementów jest równa k.
     * Działa w czasie O(n) z użyciem mapy do śledzenia częstości wystąpień sum prefiksowych.
     *
     * Algorytm:
     * 1. Dla każdego elementu oblicza bieżącą sumę prefiksową.
     * 2. Sprawdza, czy wcześniej wystąpiła suma prefiksowa = currentSum - k.
     *    Jeśli tak, to znaczy, że jakaś podtablica o sumie k kończy się w bieżącym miejscu.
     * 3. Aktualizuje mapę z częstością wystąpień sum prefiksowych.
     *
     * Przykład:
     * [1, 2, -1, 1, 2], k = 3 → wynik: 3 (bo [1,2], [1,2,-1,1], [1,2])
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n)
     */
    static int kSumSubArrays(int[] arr, int k) {
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        prefixSumFreq.put(0, 1); // suma 0 występuje raz (na starcie)

        int currentSum = 0;
        int count = 0;

        for (int num : arr) {
            currentSum += num;

            // Sprawdź, czy istniała wcześniej suma (currentSum - k)
            count += prefixSumFreq.getOrDefault(currentSum - k, 0);

            // Zaktualizuj mapę sum prefiksowych
            prefixSumFreq.put(currentSum, prefixSumFreq.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
