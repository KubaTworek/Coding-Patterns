package pl.jakubtworek.easy.stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MonotonicStack {

    /**
       Algorytm findNextGreaterToRight:

       Zadanie:
       Dla każdej liczby w liście `nums`, zwraca listę, w której na pozycji `i` znajduje się
       najbliższa większa liczba po prawej stronie `nums[i]`, lub -1, jeśli taka nie istnieje.

       Przykład:
       Dla wejścia [2, 1, 2, 4, 3] → wynik to [4, 2, 4, -1, -1]

       Działanie:
       - Iterujemy po liście od końca do początku.
       - Używamy stosu `candidates` do przechowywania potencjalnych większych elementów.
       - Dla każdego elementu usuwamy ze stosu te, które są mniejsze lub równe (bo nie mogą być rozwiązaniem).
       - Jeśli stos nie jest pusty, jego szczyt to najbliższy większy element.
       - Jeśli stos jest pusty, dodajemy -1.
       - Każdy element dodajemy na stos jako potencjalny kandydat dla elementów po lewej.
       - Wyniki dodajemy na początek listy (add(0, ...)), ponieważ iterujemy od końca.

       Złożoność:
       Czasowa: O(n)
       - Każdy element jest dodany do stosu i zdjęty z niego maksymalnie raz.
       Pamięciowa: O(n)
       - W najgorszym przypadku stos przechowuje wszystkie elementy.
     */
    static List<Integer> findNextGreaterToRight(List<Integer> nums) {
        Stack<Integer> candidates = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!candidates.isEmpty() && candidates.peek() <= nums.get(i)) {
                candidates.pop();
            }

            if (!candidates.isEmpty()) {
                result.addFirst(candidates.peek());
            } else {
                result.addFirst(-1);
            }

            candidates.push(nums.get(i));
        }

        return result;
    }
}
