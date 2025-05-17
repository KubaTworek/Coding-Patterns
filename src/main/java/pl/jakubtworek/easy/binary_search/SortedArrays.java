package pl.jakubtworek.easy.binary_search;

import java.util.List;

class SortedArrays {

    /**
       Algorytm binarySearchInsertIndex:

       Zadanie:
       Znajduje indeks, pod który można wstawić wartość `k` w posortowanej rosnąco liście `list`,
       tak aby zachować porządek (tzw. lower bound). Jeśli `k` istnieje, zwraca jego pierwsze wystąpienie.

       Złożoność:
       - Czasowa: O(log n)
         • W każdej iteracji przeszukiwana połowa listy zostaje odrzucona.
         • Jeśli lista ma n elementów, maksymalna liczba iteracji to log₂(n).
       - Pamięciowa: O(1)
         • Używamy stałej liczby zmiennych pomocniczych (left, right, mid).
     */
    static int binarySearchInsertIndex(List<Integer> list, int k) {
        int left = 0;
        int right = list.size(); // ekskluzywny koniec

        // Wariant wyszukiwania dolnego ograniczenia (lower bound)
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Jeśli wartość środkowa < szukana, to przesuń lewą granicę
            if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                // Inaczej zawęź prawą stronę
                right = mid;
            }
        }

        return left;
    }

    /**
       Algorytm binarySearchRangeForValue:

       Zadanie:
       Zwraca listę dwóch indeksów: pierwszego i ostatniego wystąpienia liczby `k` w posortowanej rosnąco liście.
       Jeśli liczba nie występuje — zwraca [-1, -1].

       Złożoność:
       - Czasowa: O(log n)
         • Dwa niezależne przebiegi binarnego wyszukiwania: jeden dla początku, jeden dla końca.
         • Każdy przebieg wykonuje maksymalnie log₂(n) iteracji (dzieląc przeszukiwany obszar na pół).
       - Pamięciowa: O(1)
         • Wszystkie operacje odbywają się przy użyciu zmiennych lokalnych.
     */
    static List<Integer> binarySearchRangeForValue(List<Integer> list, int k) {
        int first = findFirstOccurrence(list, k);
        int last = findLastOccurrence(list, k);
        return List.of(first, last);
    }

    // Znajduje indeks pierwszego wystąpienia wartości k
    private static int findFirstOccurrence(List<Integer> list, int k) {
        int left = 0;
        int right = list.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) == k) {
                result = mid;         // Zapisz wynik...
                right = mid - 1;      // ...i szukaj wcześniej
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Znajduje indeks ostatniego wystąpienia wartości k
    private static int findLastOccurrence(List<Integer> list, int k) {
        int left = 0;
        int right = list.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) == k) {
                result = mid;        // Zapisz wynik...
                left = mid + 1;      // ...i szukaj dalej w prawo
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
