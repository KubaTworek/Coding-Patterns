package pl.jakubtworek.hash_maps_and_sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HashMaps {
    /**
    *   Algorytm pairSumUnsorted:

    *   Zadanie:
    *   Dla danej nieposortowanej listy liczb całkowitych, znaleźć dwie liczby,
    *   których suma jest równa target, i zwrócić ich indeksy jako listę [i, j].

    *   Działanie:
    *   - Tworzona jest mapa (HashMap), która przechowuje odwzorowanie: liczba → jej indeks.
    *   - Iterując przez listę:
    *     - Sprawdzamy, czy `target - nums[i]` już występuje w mapie (czyli czy istnieje liczba,
    *       która razem z `nums[i]` daje `target`).
    *     - Jeśli tak — zwracamy parę indeksów: [indeks tej liczby, bieżący indeks].
    *     - Jeśli nie — dodajemy nums[i] do mapy jako potencjalnego kandydata na przyszłe pary.

    *   Złożoność obliczeniowa:
    *   - Czasowa: O(n)
    *     - Każdy element przetwarzany jest dokładnie raz.
    *     - Operacje na HashMap (containsKey, put) są średnio O(1).
    *   - Pamięciowa: O(n)
    *     - W najgorszym przypadku mapa przechowuje wszystkie elementy listy.

    *   Uwagi:
    *   - Jeśli para nie istnieje — metoda zwraca pustą listę.
    *   - Zwrócone indeksy są unikalne (i ≠ j).
    *   - Metoda zatrzymuje się przy znalezieniu pierwszej pasującej pary.
    **/
    static List<Integer> pairSumUnsorted(List<Integer> nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            if (numsMap.containsKey(target - nums.get(i))) {
                return List.of(numsMap.get(target - nums.get(i)), i);
            }
            numsMap.put(nums.get(i), i);
        }
        return List.of();
    }
}
