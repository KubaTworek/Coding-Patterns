package pl.jakubtworek.easy.two_pointers;

import java.util.List;

class UnidirectionalTraversal {
    /**
       Algorytm moveZerosToEnd:

       Zadanie:
       Przesuwa wszystkie zera w liście `nums` na koniec, zachowując kolejność pozostałych (niezerowych) elementów.
       Operacja odbywa się "w miejscu" (bez tworzenia nowej listy).

       Przykład:
       [0, 1, 0, 3, 12] → [1, 3, 12, 0, 0]

       Działanie:
       - Używamy dwóch wskaźników:
         - `left` — miejsce, gdzie powinien trafić kolejny niezerowy element.
         - `right` — obecny element, który rozpatrujemy.
       - Iterujemy po liście:
         - Jeśli `nums[right]` nie jest zerem:
           - zamieniamy `nums[left]` i `nums[right]` (jeśli to różne pozycje),
           - zwiększamy `left` (bo niezerowy element został już przesunięty na swoje miejsce).
         - `right` zwiększamy zawsze.

       Złożoność:
       - Czasowa: O(n) — każdy element listy odwiedzany jest dokładnie raz.
       - Pamięciowa: O(1) — nie tworzymy dodatkowych struktur danych, działamy na oryginalnej liście.
     */
    static void moveZerosToEnd(List<Integer> nums) {
        int left = 0;

        for (int right = 0; right < nums.size(); right++) {
            if (nums.get(right) != 0) {
                if (left != right) {
                    // Zamiana miejscami zero z liczbą niezerową
                    int temp = nums.get(left);
                    nums.set(left, nums.get(right));
                    nums.set(right, temp);
                }
                left++;
            }
        }
    }
}
