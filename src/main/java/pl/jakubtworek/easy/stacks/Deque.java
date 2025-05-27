package pl.jakubtworek.easy.stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class Deque {
    /**
       Algorytm: findSlidingWindowMaximums

       Zadanie:
       Dla zadanej listy liczb całkowitych `nums` i rozmiaru okna `k`,
       znajdź największy element w każdym oknie o długości `k`,
       przesuwanym od lewej do prawej.

       Przykład:
       Input:  nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
       Output: [3, 3, 5, 5, 6, 7]

       Działanie:
       - Wykorzystujemy dwukierunkową kolejkę (Deque), która przechowuje indeksy kandydatów na maksimum.
       - Dla każdego elementu w `nums`:
         1. Usuwamy z początku kolejki indeksy, które są poza bieżącym oknem (i - k).
         2. Usuwamy z końca kolejki wszystkie indeksy elementów mniejszych niż bieżący — nie będą już maksymalne.
         3. Dodajemy bieżący indeks do końca kolejki.
         4. Jeśli jesteśmy od pozycji `k - 1` lub dalej, pierwszy element w kolejce to maksimum okna — dodajemy go do wyniku.

       Złożoność:
       - Czasowa: O(n)
         • Każdy indeks dodajemy do kolejki dokładnie raz i usuwamy co najwyżej raz.
       - Pamięciowa: O(k)
         • Deque przechowuje maksymalnie `k` indeksów.
     */
    static List<Integer> findSlidingWindowMaximums(List<Integer> nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            // 1. Usuń indeksy spoza bieżącego okna
            if (!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.removeFirst();
            }

            // 2. Usuń z końca mniejsze wartości — nie będą już użyte jako maksimum
            while (!deque.isEmpty() && nums.get(deque.getLast()) < nums.get(i)) {
                deque.removeLast();
            }

            // 3. Dodaj bieżący indeks
            deque.addLast(i);

            // 4. Dodaj maksimum do wyniku (gdy okno osiągnie rozmiar k)
            if (i >= k - 1) {
                result.add(nums.get(deque.getFirst()));
            }
        }

        return result;
    }
}
