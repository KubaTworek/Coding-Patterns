package pl.jakubtworek.easy.stacks;

import java.util.List;

class Exercise {

    /**
     * Zadanie:
     * Oblicz wartość wyrażenia zapisanego w odwrotnej notacji polskiej (postfix).
     * Operatory to +, -, *, /. Operandy to liczby całkowite jako napisy.
     *
     * Przykład:
     * ["2", "1", "+", "3", "*"] → (2 + 1) * 3 = 9
     *
     * Złożoność:
     * - Czasowa: O(n), każdy token przetwarzany raz
     * - Pamięciowa: O(n), stos pomocniczy
     */
    static int evaluateReversePolishNotation(List<String> tokens) {
        return 0;
    }

    /**
     * Zadanie:
     * Dla poprawnego ciągu nawiasów usuń wszystkie zewnętrzne nawiasy każdego fragmentu.
     *
     * Przykład:
     * "(()())(())" → "()()"
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(n)
     */
    static String removeOuterParentheses(String s) {
        return "";
    }

    /**
     * Zadanie:
     * Oblicz wartość prostego wyrażenia arytmetycznego zawierającego +, - oraz nawiasy.
     * Zakładamy brak znaków mnożenia/dzielenia.
     *
     * Przykład:
     * "(1+(4+5+2)-3)+(6+8)" → 23
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(n), stos znaków i wartości
     */
    static int calculateBasicExpression(String s) {
        return 0;
    }

    /**
     * Zadanie:
     * Usuń wszystkie k przylegających (identycznych) znaków z ciągu znaków.
     * Proces powtarzaj aż nie będzie zmian.
     *
     * Przykład:
     * "deeedbbcccbdaa", k=3 → "aa"
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(n)
     */
    static String removeKAdjacentDuplicates(String s, int k) {
        return null;
    }

    /**
     * Zadanie:
     * Zwróć maksymalną głębokość zagnieżdżenia poprawnego wyrażenia nawiasowego.
     *
     * Przykład:
     * "(1+(2*3)+((8)/4))+1" → 3
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1)
     */
    static int maxNestingDepthOfParentheses(String s) {
        return 0;
    }
}
