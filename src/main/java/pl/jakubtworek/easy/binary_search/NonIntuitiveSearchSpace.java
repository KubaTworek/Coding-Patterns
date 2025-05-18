package pl.jakubtworek.easy.binary_search;

import java.util.List;

class NonIntuitiveSearchSpace {

    /**
       Algorytm maxCuttingHeight:

       Zadanie:
       Dana jest lista wysokości drzew (`heights`) oraz wartość `k` — ilość drewna,
       jaką trzeba łącznie uzyskać. Należy znaleźć maksymalną możliwą wysokość ostrza piły (cutHeight),
       przy której nadal uda się zebrać co najmniej `k` jednostek drewna,
       zakładając, że każdy kawałek drzewa powyżej tej wysokości zostaje ucięty i zebrany.

       Przykład:
       heights = [20, 15, 10, 17], k = 7 → wynik: 15

       Złożoność:
       - Czasowa: O(n log h)
         • `log h` – binarne przeszukiwanie przestrzeni wysokości od 0 do maksymalnej wysokości drzewa.
         • `n` – dla każdej iteracji obliczamy sumę drewna w czasie O(n).
       - Pamięciowa: O(1), pomijając wejściową listę.
     */
    static int maxCuttingHeight(List<Integer> heights, int k) {
        int left = 0;
        int right = heights.stream().max(Integer::compareTo).orElse(0); // max wysokość drzewa

        // Szukamy największej możliwej wysokości, która pozwala zebrać ≥ k jednostek drewna
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;

            if (canObtainAtLeastKWood(mid, k, heights)) {
                left = mid; // Możemy spróbować jeszcze wyżej
            } else {
                right = mid - 1; // Za wysoko – zetniemy za mało
            }
        }

        return left; // lewy == prawy → najlepsza możliwa wysokość cięcia
    }

    /**
     * Sprawdza, czy przy danej wysokości cięcia `cutHeight` uda się zebrać co najmniej `k` jednostek drewna.
     */
    private static boolean canObtainAtLeastKWood(int cutHeight, int requiredWood, List<Integer> heights) {
        int totalWood = 0;

        for (int height : heights) {
            if (height > cutHeight) {
                totalWood += height - cutHeight;
            }
        }

        return totalWood >= requiredWood;
    }


    /**
     * Algorytm: findLocalMaximumIndex
     *
     * Zadanie:
     * Znajduje indeks lokalnego maksimum w liście liczb całkowitych `nums`.
     * Lokalny maksymalny element to taki, który jest większy od swojego sąsiada z prawej strony.
     * Gwarantuje się, że lokalne maksimum istnieje (dzięki warunkowi brzegowemu).
     *
     * Przykład:
     * Dla nums = [1, 3, 5, 4, 2] → wynik: 2 (bo 5 > 4)
     *
     * Działanie:
     * - Używamy wyszukiwania binarnego do znalezienia lokalnego maksimum.
     * - Jeśli środek `mid` jest większy niż `mid + 1`, to lokalny maksimum jest po lewej lub w `mid`.
     * - W przeciwnym razie, szukamy po prawej stronie.
     * - Proces kontynuujemy aż `left == right`, wtedy to jest lokalny maksimum.
     *
     * Złożoność:
     * - Czasowa: O(log n)
     *   • Ponieważ dzielimy zakres wyszukiwania na pół w każdej iteracji.
     * - Pamięciowa: O(1)
     *   • Stała liczba zmiennych – brak dodatkowych struktur danych.
     */
    public static int findLocalMaximumIndex(List<Integer> nums) {
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums.get(mid) > nums.get(mid + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left; // lub right, bo left == right
    }
}
