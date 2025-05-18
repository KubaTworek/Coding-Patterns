package pl.jakubtworek.easy.two_pointers;

import java.util.List;

class Exercise {
    /**
     * Zadanie:
     * Usuń zduplikowane elementy z posortowanej listy liczb całkowitych.
     * Modyfikuj listę w miejscu i zwróć listę bez duplikatów.
     *
     * Przykład:
     * Input: [1, 1, 2, 2, 3] → Output: [1, 2, 3]
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1) — pomijając wynik
     */
    static List<Integer> removeDuplicates(List<Integer> sorted) {
        // implementacja
        return null;
    }

    /**
     * Zadanie:
     * Sprawdź, czy istnieją dwie liczby w posortowanej liście takie, że
     * jedna jest dokładnie podwójnością drugiej (i ≠ j).
     *
     * Przykład:
     * Input: [1, 2, 3, 6] → Output: true (bo 3 * 2 == 6)
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1)
     */
    static boolean hasDoubleValuePair(List<Integer> nums) {
        // implementacja
        return false;
    }

    /**
     * Zadanie:
     * Zlicz, ile istnieje unikalnych par liczb w liście `nums`, takich że
     * różnica między nimi wynosi dokładnie `k` (czyli |a - b| == k).
     *
     * Przykład:
     * Input: [1, 7, 5, 9, 2, 12, 3], k = 2 → Output: 4
     * (bo pary: (1,3), (3,5), (5,7), (7,9))
     *
     * Złożoność:
     * - Czasowa: O(n log n) (przez sortowanie i przesuwanie wskaźników)
     * - Pamięciowa: O(1)
     */
    static int countPairsWithDifference(List<Integer> nums, int k) {
        // implementacja
        return 0;
    }

    /**
     * Zadanie:
     * Sprawdź, czy z liter w ciągu `s` da się ułożyć palindrom (permutacja).
     *
     * Przykład:
     * Input: "civic" → true
     * Input: "ivicc" → true
     * Input: "hello" → false
     *
     * Warunek:
     * - Maksymalnie jeden znak może mieć nieparzystą liczbę wystąpień.
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1) dla alfabetu ASCII
     */
    static boolean canFormPalindrome(String s) {
        // implementacja
        return false;
    }
}
