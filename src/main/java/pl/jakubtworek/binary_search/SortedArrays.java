package pl.jakubtworek.binary_search;

import java.util.ArrayList;
import java.util.List;

public class SortedArrays {
    /**
    Algorytm findTheInsertionIndex:

    Zadanie:
    Zwraca indeks, pod który można wstawić wartość `k` w posortowanej liście `list`,
    tak aby zachować porządek rosnący. Jeśli `k` już występuje w liście, zwracany jest indeks pierwszego jego wystąpienia (tzw. lower bound).

    Działanie:
    - Używa klasycznego wyszukiwania binarnego (binary search).
    - W każdej iteracji porównuje wartość środkową `mid` z wartością `k`.
    - Jeśli `list[mid] < k`, przesuwamy `left` na `mid + 1`.
    - W przeciwnym przypadku ograniczamy `right` do `mid`.
    - Pętla kończy się, gdy `left == right` — to miejsce, gdzie `k` należy wstawić.

    Złożoność:
    - Czasowa: O(log n) — binarne przeszukiwanie listy.
    - Pamięciowa: O(1) — stała ilość zmiennych pomocniczych.

    Uwagi:
    - Lista musi być posortowana rosnąco.
    - Przydaje się np. do implementacji bisekcji, dolnego ograniczenia (lower bound), czy stabilnego wstawiania.
    */
    static int findTheInsertionIndex(List<Integer> list, int k) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
    Algorytm findFirstAndLastOccurrences:

    Zadanie:
    Dla posortowanej listy liczb całkowitych `list` oraz liczby `k`,
    zwraca listę dwóch indeksów: pierwszy i ostatni indeks wystąpienia liczby `k`.
    Jeśli liczba `k` nie występuje — zwraca [-1, -1].

    Działanie:
    - Używa dwóch osobnych funkcji `findFirst` i `findLast`, które są wariantami wyszukiwania binarnego.
    - `findFirst` szuka pierwszego wystąpienia `k`:
      - po trafieniu w `k` szukamy dalej w lewo (czy jest wcześniejsze wystąpienie).
    - `findLast` szuka ostatniego wystąpienia `k`:
      - po trafieniu w `k` szukamy dalej w prawo.
    - Jeśli żadna funkcja nie znajdzie `k`, zwracają -1.

    Złożoność:
    - Czasowa: O(log n) — dwa przebiegi binarnego wyszukiwania.
    - Pamięciowa: O(1) — stała ilość zmiennych.

    Uwagi:
    - Lista wejściowa musi być posortowana rosnąco.
    - Działa niezależnie od tego, ile razy `k` występuje — także przy jednym lub wielu powtórzeniach.
    */
    static List<Integer> findFirstAndLastOccurrences(List<Integer> list, int k) {
        int first = findFirst(list, k);
        int last = findLast(list, k);
        return List.of(first, last);
    }

    private static int findFirst(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == k) {
                result = mid;
                right = mid - 1;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static int findLast(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == k) {
                result = mid;
                left = mid + 1;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
