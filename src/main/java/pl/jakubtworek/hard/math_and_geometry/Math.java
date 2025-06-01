package pl.jakubtworek.hard.math_and_geometry;

class Math {
    /**
     * Odwraca cyfry 32-bitowej liczby całkowitej. Jeśli odwrócenie spowoduje przekroczenie zakresu typu int,
     * metoda zwraca 0.
     *
     * Przykład:
     *  - 123 → 321
     *  - -123 → -321
     *  - 1534236469 → 0 (przekroczenie zakresu int)
     *
     * Algorytm:
     * - Iteracyjnie wyciąga ostatnią cyfrę (x % 10) i dodaje ją do nowej liczby.
     * - Sprawdza, czy nowe przeliczenie nie przekroczy zakresu Integer.MIN/MAX.
     *
     * Złożoność czasowa: O(log₁₀ n)
     * Złożoność pamięciowa: O(1)
     */
    static int reverse32BitInteger(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7))
                return 0;
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8))
                return 0;

            reversed = reversed * 10 + digit;
        }

        return reversed;
    }
}
