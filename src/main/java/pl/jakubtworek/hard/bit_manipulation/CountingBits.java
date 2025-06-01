package pl.jakubtworek.hard.bit_manipulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CountingBits {
    /**
     * Zwraca listę liczby jedynek (bitów 1) w reprezentacji binarnej
     * wszystkich liczb od 0 do n (włącznie).
     *
     * Używa techniki DP + bit maskowania:
     * dp[i] = dp[i >> 1] + (i & 1)
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(n)
     */
    static List<Integer> hammingWeightsOfIntegers(int n) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(n + 1, 0));
        for (int i = 1; i <= n; i++) {
            result.set(i, result.get(i >> 1) + (i & 1));
        }
        return result;
    }
}
