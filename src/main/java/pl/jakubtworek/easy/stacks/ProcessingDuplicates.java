package pl.jakubtworek.easy.stacks;

import java.util.Stack;

class ProcessingDuplicates {

    /**
       Algorytm: removeAdjacentDuplicates

       Zadanie:
       Usuwa wszystkie przylegające (sąsiadujące) duplikaty w łańcuchu znaków `s`,
       powtarzając proces aż nie będzie już więcej przylegających par do usunięcia.

       Przykład:
       Input: "abbaca" → "ca" (bo: "abbaca" → "aaca" → "ca")

       Działanie:
       - Używamy stosu (`Stack<Character>`), aby przetwarzać znaki po kolei.
       - Dla każdego znaku:
         • Jeśli stos jest niepusty i ostatni znak na stosie to ten sam znak — usuwamy go (pop),
         • W przeciwnym razie — dodajemy bieżący znak do stosu.
       - Na koniec budujemy wynik z zawartości stosu.

       Złożoność obliczeniowa:
       - Czasowa: O(n), gdzie n to długość łańcucha wejściowego.
         • Każdy znak jest przetwarzany najwyżej 2 razy: raz przy dodaniu i raz przy usunięciu.
       - Pamięciowa: O(n), w najgorszym przypadku stos może przechować wszystkie znaki.
     */
    static String removeAdjacentDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // usuwamy duplikat
            } else {
                stack.push(c); // dodajemy nowy znak
            }
        }

        // Budujemy wynikowy ciąg ze znaków pozostałych na stosie
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }
}
