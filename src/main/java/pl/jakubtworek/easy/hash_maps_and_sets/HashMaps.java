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

    /**
       Algorytm: countGeometricTriplets

       Zadanie:
       Zlicza liczbę trójek geometrycznych (a, b, c) w liście liczb całkowitych,
       dla których b = a * r i c = b * r, czyli: a, a*r, a*r^2

       Działanie:
       - Używamy dwóch map:
         • leftMap — częstotliwości liczb po lewej stronie analizowanej liczby
         • rightMap — częstotliwości liczb po prawej stronie analizowanej liczby
       - Najpierw uzupełniamy rightMap — ile razy każda liczba występuje.
       - Iterujemy po elementach jako potencjalnym "środku" trójki (czyli `b`).
         • Odejmujemy bieżący element z rightMap.
         • Jeśli `x % r == 0`, to:
           - liczba trójek to leftMap[x / r] * rightMap[x * r]
         • Dodajemy x do leftMap — będzie dostępny przy kolejnych iteracjach.

       Złożoność:
       - Czasowa: O(n), bo każde wystąpienie liczby analizowane jest tylko raz
       - Pamięciowa: O(n), ze względu na dwie mapy
     */
    public static long countGeometricTriplets(List<Long> nums, long r) {
        Map<Long, Long> leftMap = new HashMap<>();
        Map<Long, Long> rightMap = new HashMap<>();
        long count = 0;

        // Zlicz wszystkie wystąpienia w rightMap
        for (long x : nums) {
            rightMap.put(x, rightMap.getOrDefault(x, 0L) + 1);
        }

        // Iterujemy po elementach jako potencjalnych środkach trójek
        for (long x : nums) {
            // Usuwamy aktualny element z rightMap, bo właśnie go przetwarzamy
            rightMap.put(x, rightMap.get(x) - 1);

            if (x % r == 0) {
                long left = leftMap.getOrDefault(x / r, 0L);
                long right = rightMap.getOrDefault(x * r, 0L);
                count += left * right;
            }

            // Zwiększamy licznik aktualnej liczby w leftMap
            leftMap.put(x, leftMap.getOrDefault(x, 0L) + 1);
        }

        return count;
    }
}
