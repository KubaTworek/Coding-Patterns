package pl.jakubtworek.hard.bit_manipulation;

import java.util.List;

class XOR {
    /**
     * Zwraca liczbę, która pojawia się tylko raz w liście liczb całkowitych,
     * gdzie każda inna liczba pojawia się dokładnie dwa razy.
     *
     * Wykorzystuje właściwości XOR:
     * a ^ a = 0, a ^ 0 = a
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(1)
     */
    static int lonelyInteger(List<Integer> nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
