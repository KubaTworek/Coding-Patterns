package pl.jakubtworek.medium.prefix_sums;

/**
 * Klasa do szybkiego obliczania sum prefiksowych.
 * Zakłada indeksowanie 0-based.
 *
 * Przykład:
 *   PrefixSum ps = new PrefixSum(new int[] {1, 2, 3, 4});
 *   ps.rangeSum(1, 3); // 2 + 3 + 4 = 9
 */
class PrefixSum {
    private final int[] prefix;

    /**
     * Tworzy tablicę sum prefiksowych z wejściowej tablicy.
     * prefix[i] = suma z arr[0] do arr[i - 1], prefix[0] = 0
     */
    PrefixSum(int[] arr) {
        this.prefix = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
    }

    /**
     * Zwraca sumę przedziału [left, right], włącznie.
     */
    int rangeSum(int left, int right) {
        if (left < 0 || right >= prefix.length - 1 || left > right) {
            throw new IllegalArgumentException("Nieprawidłowy zakres: [" + left + ", " + right + "]");
        }
        return prefix[right + 1] - prefix[left];
    }

    /**
     * Zwraca sumę prefiksową do indeksu `right` (czyli z zakresu [0, right]).
     */
    int prefixSum(int right) {
        return rangeSum(0, right);
    }
}
