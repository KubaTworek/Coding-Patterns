package pl.jakubtworek.easy.heaps;

import java.util.*;
import java.util.stream.Collectors;

class FindingValuesInSortedOrder {

    /**
       Algorytm findStringsWithAtLeastKOccurrences:

       Zadanie:
       Dla danej listy stringów, zwraca te, które występują co najmniej `k` razy,
       posortowane malejąco według liczby wystąpień.

       Przykład:
       Input: ["a", "b", "a", "c", "b", "a"], k = 2
       Output: ["a", "b"] — ponieważ "a" występuje 3 razy, "b" 2 razy.

       Działanie:
       1. Zliczamy wystąpienia każdego stringa za pomocą mapy (`Map<String, Integer>`).
       2. Tworzymy kolejkę priorytetową (max-heap) posortowaną malejąco po liczbie wystąpień.
       3. Dodajemy do niej tylko te wpisy, które występują **co najmniej** `k` razy.
       4. Wyciągamy z kolejki klucze (stringi) w kolejności malejącej liczby wystąpień.

       Złożoność:
       - Czasowa: O(n log m)
         • n – liczba stringów w wejściowej liście
         • m – liczba unikalnych stringów, które wystąpiły ≥ k razy
         • log m – koszt operacji na kopcu
       - Pamięciowa: O(m)
         • mapa przechowuje n unikalnych elementów (w najgorszym przypadku),
           kolejka – tylko te ≥ k
     */
    static List<String> findStringsWithAtLeastKOccurrences(List<String> strings, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Liczenie wystąpień każdego stringa
        for (String str : strings) {
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }

        // Kolejka priorytetowa — max-heap według wartości (częstotliwości)
        Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );

        // Filtrujemy tylko te, które mają co najmniej k wystąpień
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= k) {
                maxHeap.add(entry);
            }
        }

        // Zwracamy posortowane klucze z kopca
        return maxHeap.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
