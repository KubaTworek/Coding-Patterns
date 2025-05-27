package pl.jakubtworek.easy.fast_and_slow_pointers;

import java.util.List;

class Exercise {

    /**
     * Zadanie:
     * Dana jest tablica, która najpierw rośnie, a potem maleje (tzw. mountain array).
     * Znajdź indeks szczytu (największego elementu).
     * <p>
     * Przykład:
     * [1, 3, 5, 4, 2] → wynik: 2
     * <p>
     * Uwaga:
     * To wariant wyszukiwania lokalnego maksimum. Użyj wskaźników fast/slow jako binarnego poszukiwania.
     */
    static int findPeakInMountainArray(List<Integer> nums) {
        int left = 0;
        int right = 1;
        while (left < right) {
            left += 1;
            right += 1;
            if (nums.get(right) < nums.get(left)) {
                return left;
            }
        }
        return left;
    }

    /**
     * Zadanie:
     * Dla liczby `n` i podstawy `b`, generuj kolejne reszty z dzielenia `n` przez `b`, aż pojawi się cykl.
     * Zwróć true, jeśli cykl się powtarza (np. w ciągu dziesiętnym 1/3 = 0.333...).
     * <p>
     * Przykład:
     * hasModuloRepeatingCycle(1, 3) → true (bo 1 % 3 → 1 → 1 → cykl)
     * hasModuloRepeatingCycle(1, 2) → false
     * <p>
     * Wskazówka:
     * Możesz symulować reszty modulo i wykryć cykl za pomocą fast & slow.
     */
    static boolean hasModuloRepeatingCycle(int numerator, int base) {
        int left = 10 * (numerator % base);
        int right = 10 * ((10 * (numerator % base)) % base);

        while (left % base != 0 && right % base != 0) {
            left = 10 * (left % base);
            right = 10 * ((10 * (right % base)) % base);
            if (left == right) {
                return true;
            }
        }
        return false;
    }

    /**
     * Zadanie:
     * Sprawdź, czy dany ciąg znaków `s` można uznać za powtarzającą się sekwencję (cykl) podciągu.
     * <p>
     * Przykład:
     * "ababab" → true ("ab" powtarza się 3x)
     * "abcabcabc" → true ("abc" 3x)
     * "abac" → false
     * <p>
     * Podejście:
     * Fast/slow pointer do wykrycia "cyklicznego wzorca".
     */
    static boolean isRepeatedPatternCycle(String s) {
        return false;
    }

    /**
     * Zadanie:
     * W danej transformacji (np. n → suma cyfr ^2), znajdź pierwszą liczbę,
     * która nie wpada w cykl i nie prowadzi do 1.
     * <p>
     * Przykład:
     * Dla n = 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 (cykl)
     * wynik: brak (bo wszystkie wpadają w cykl)
     * <p>
     * Jeśli dla np. n = 7 dojdziemy do 1, przerywamy.
     * <p>
     * Wskazówka: Można śledzić ścieżkę fast i slow, by wykryć cykl i zbadać pierwszy unikalny krok.
     */
    static int findFirstNonCyclicNumberInTransform(int start) {
        int start1 = start;
        int start2 = start;
        int left = 0;
        int right = 0;
        boolean isFirst = true;
        while (start1 > 0 || start2 > 0) {
            if (start1 != 0) {
                left += (start1 % 10) * (start1 % 10);
                start1 /= 10;
                if (start1 == 0) {
                    start1 = left;
                    left = 0;
                    System.out.println("left " + start1);

                }

            }
            if (start2 != 0) {
                right += (start2 % 10) * (start2 % 10);
                start2 /= 10;
                System.out.println("r " + right);
                if (start2 == 0 && isFirst) {
                    start2 = right;
                    right = 0;
                    right += (start2 % 10) * (start2 % 10);
                    start2 /= 10;
                    isFirst = false;
                }
                if (start2 == 0) {
                    isFirst = true;
                    start2 = right;
                    right = 0;
                    System.out.println("right " + start2);

                    if (start1 == start2) {
                        return 1;
                    }
                }

            }
        }
        return -1;
    }

    /**
     * Zadanie:
     * Dla danej funkcji `f(x)` reprezentowanej przez tablicę `transforms`,
     * sprawdź, czy zaczynając od indeksu `start`, dojdziesz do punktu,
     * w którym `f(f(x)) == f(x)` (czyli wartość się stabilizuje).
     * <p>
     * transforms[i] = indeks kolejnego stanu (np. f(x) = transforms[x])
     * <p>
     * Przykład:
     * transforms = [1, 2, 3, 3], start = 0 → 0→1→2→3→3 (stabilizacja na 3) → true
     */
    static boolean doesFunctionEventuallyStabilize(List<Integer> transforms, int start) {
        return false;
    }


}
