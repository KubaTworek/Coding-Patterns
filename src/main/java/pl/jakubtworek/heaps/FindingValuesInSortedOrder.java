package pl.jakubtworek.heaps;

import pl.jakubtworek.linked_list.SinglyLinkedList;

import java.util.*;
import java.util.stream.Collectors;

public class FindingValuesInSortedOrder {
    /**
    Algorytm kMostFrequentStrings:

    Zadanie:
    Zwraca wszystkie stringi, które wystąpiły w wejściowej liście co najmniej (lub więcej niż) `k` razy,
    posortowane malejąco według liczby ich wystąpień.

    Działanie:
    1. Budujemy mapę `map`, która zlicza liczbę wystąpień każdego stringa z listy wejściowej.
    2. Tworzymy kolejkę priorytetową (`PriorityQueue`) posortowaną malejąco po liczbie wystąpień (max-heap).
    3. Iterujemy po mapie:
       - jeśli dany string wystąpił więcej niż `k` razy (`entry.getValue() > k`), dodajemy go do kolejki.
    4. Na końcu zbieramy z kolejki same klucze (`.getKey()`), które odpowiadają stringom, i zwracamy listę wynikową.

    Złożoność:
    - Czasowa: O(n log m)
      - n — liczba wszystkich elementów na wejściu
      - m — liczba unikalnych stringów spełniających warunek (>k)
      - log m — koszt dodawania elementów do kopca
    - Pamięciowa: O(m)
      - mapa i kolejka przechowują unikalne elementy

    Uwagi:
    - Funkcja `map.getOrDefault(s, 1) + 1` zawyża pierwsze wystąpienie stringa – poprawne powinno być `getOrDefault(s, 0) + 1`.
    - Warunek `> k` oznacza, że string musi wystąpić **więcej niż** `k` razy. Jeśli chcesz włączyć `k`, użyj `>= k`.
    - Kolejność wyników zależy od liczby wystąpień – nie jest alfabetyczna przy remisie.

    Możliwe rozszerzenia:
    - Dodanie sortowania alfabetycznego w przypadku remisu: `Comparator.comparing(...).thenComparing(...)`
    - Zmiana logiki z `> k` na `>= k` zgodnie z oczekiwanym zachowaniem
    - Zwracanie top-`k` najczęstszych elementów (zmiana semantyki `k`)
    */
    static List<String> kMostFrequentStrings(List<String> list, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, map.getOrDefault(s, 1) + 1);
        }

        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > k) {
                queue.add(entry);
            }
        }

        return queue.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
