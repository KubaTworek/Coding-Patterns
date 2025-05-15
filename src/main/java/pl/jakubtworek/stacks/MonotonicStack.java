package pl.jakubtworek.stacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonicStack {
    /**
    Algorytm nextLargestNumberToTheRights:

    Zadanie:
    Dla każdej liczby w liście `nums`, zwraca listę, w której na pozycji `i` znajduje się
    najbliższa większa liczba po prawej stronie `nums[i]`, lub -1, jeśli taka nie istnieje.

    Przykład:
    Dla wejścia [2, 1, 2, 4, 3] → wynik to [4, 2, 4, -1, -1]

    Działanie:
    1. Iterujemy po liście od końca do początku (prawo → lewo).
    2. Używamy stosu `candidates` do przechowywania potencjalnych "większych" kandydatów.
       - Jeśli element ze szczytu stosu jest mniejszy lub równy bieżącemu `nums[i]`, usuwamy go (bo nie może być rozwiązaniem).
       - Gdy stos nie jest pusty, jego szczyt to najbliższy większy element.
       - Jeśli stos jest pusty — oznacza to brak większego elementu po prawej → dodajemy -1.
    3. Bieżący element dodajemy na stos, bo może być "następnym większym" dla elementów po lewej.
    4. Wyniki dodajemy w odwrotnej kolejności (bo iterujemy od końca) — tu zakładamy użycie metody `addFirst`, która wymaga np. `LinkedList`.

    Złożoność:
    - Czasowa: O(n)
      - Każdy element jest dodany do stosu i usunięty z niego maksymalnie raz.
    - Pamięciowa: O(n)
      - W najgorszym przypadku stos przechowuje wszystkie elementy (np. lista rosnąca).

    Uwagi:
    - Użycie `addFirst(...)` sugeruje, że lista `res` musi być typu `LinkedList<Integer>`, ponieważ `ArrayList` nie ma tej metody.
    - Alternatywnie można użyć `add(...)` i na końcu wykonać `Collections.reverse(res)` — bardziej wydajne dla `ArrayList`.
    */
    static List<Integer> nextLargestNumberToTheRights(List<Integer> nums) {
        Stack<Integer> candidates = new Stack<>();
        List<Integer> res = new ArrayList<>();

        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!candidates.isEmpty() && candidates.peek() <= nums.get(i)) {
                candidates.pop();
            }
            if (!candidates.isEmpty()) {
                res.addFirst(candidates.peek());
            } else {
                res.add(-1);
            }
            candidates.push(nums.get(i));
        }
        return res;
    }
}
