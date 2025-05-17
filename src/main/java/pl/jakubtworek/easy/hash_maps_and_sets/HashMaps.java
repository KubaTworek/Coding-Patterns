package pl.jakubtworek.easy.hash_maps_and_sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HashMaps {

    /**
       Algorytm findTwoSumIndicesUnsorted (klasyczne "Two Sum"):

       Zadanie:
       Dla nieposortowanej listy liczb całkowitych, znajdź dwie liczby, których suma wynosi `target`,
       i zwróć ich indeksy jako listę [i, j].

       Przykład:
       Input: nums = [2, 7, 11, 15], target = 9 → Output: [0, 1] (bo 2 + 7 = 9)

       Działanie:
       - Iterujemy po liście, śledząc napotkane wartości w mapie `value → index`.
       - Dla każdego elementu `nums[i]`, obliczamy różnicę `target - nums[i]` i sprawdzamy,
         czy ta wartość istnieje w mapie — jeśli tak, mamy parę.
       - Jeśli nie, dodajemy `nums[i]` do mapy i kontynuujemy.
       - Gdy znajdziemy parę, zwracamy jej indeksy: [indeks z mapy, bieżący i].
       - Jeśli nie znajdziemy żadnej pary, zwracamy pustą listę.

       Złożoność:
       - Czasowa: O(n)
         • Przechodzimy przez listę raz.
         • Wyszukiwanie i dodawanie do HashMap to operacje O(1) średnio.
       - Pamięciowa: O(n)
         • W najgorszym przypadku musimy zapisać każdy element w mapie.

       Uzasadnienie:
       - Zamiast podwójnej pętli (O(n²)), używamy HashMap do szybkiego sprawdzania brakującej liczby.
       - To klasyczne i optymalne rozwiązanie problemu "two sum" dla listy nieposortowanej.

       Uwagi:
       - Gdy istnieje więcej niż jedna para, zwracana jest pierwsza napotkana.
       - Zwrócone indeksy są unikalne (i ≠ j).
     */
    static List<Integer> findTwoSumIndicesUnsorted(List<Integer> nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums.get(i);

            if (valueToIndex.containsKey(complement)) {
                return List.of(valueToIndex.get(complement), i);
            }

            // Dodaj bieżący element jako kandydat do przyszłej pary
            valueToIndex.put(nums.get(i), i);
        }

        return List.of(); // brak pasującej pary
    }
}
