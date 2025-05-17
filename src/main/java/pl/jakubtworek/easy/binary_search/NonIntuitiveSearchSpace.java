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
}
