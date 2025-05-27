package pl.jakubtworek.easy.stacks;

import java.util.Map;
import java.util.Stack;

class NestedStructures {

    /**
       Algorytm validateBracketNesting:

       Zadanie:
       Sprawdza poprawność zagnieżdżenia nawiasów w wyrażeniu zawierającym tylko: (), [], {}.
       Wyrażenie jest poprawne, jeśli każdy nawias otwierający ma swój domknięty odpowiednik w odpowiedniej kolejności.

       Złożoność:
       - Czasowa: O(n) — każdy znak analizowany jest raz.
       - Pamięciowa: O(n) — stos może zawierać wszystkie znaki w najgorszym przypadku (np. same nawiasy otwierające).
     */
    static boolean validateBracketNesting(String expression) {
        Map<Character, Character> bracketPairs = Map.of('(', ')', '[', ']', '{', '}');
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (bracketPairs.containsKey(c)) {
                // Otwierający nawias — zapisz na stosie
                stack.push(c);
            } else if (bracketPairs.containsValue(c)) {
                // Zamykający nawias — sprawdź zgodność ze szczytem stosu
                if (stack.isEmpty() || bracketPairs.get(stack.peek()) != c) {
                    return false;
                }
                stack.pop();
            }
        }

        // Jeśli stos pusty, wszystkie nawiasy domknięte poprawnie
        return stack.isEmpty();
    }

    /**
       Algorytm evaluateSimpleExpressionWithParentheses:

       Zadanie:
       Oblicza wartość wyrażenia arytmetycznego z operatorami + i -, obsługując zagnieżdżone nawiasy.
       Przykład: "(1 + (2 - 3)) + 4" → wynik: 4

       Złożoność:
       - Czasowa: O(n) — każdy znak analizowany raz.
       - Pamięciowa: O(n) — stos przechowuje zagnieżdżone wartości i znaki.
     */
    static int evaluateSimpleExpressionWithParentheses(String expression) {
        Stack<Integer> valueStack = new Stack<>();
        Stack<Integer> signStack = new Stack<>();

        int result = 0;
        int sign = 1;
        int number = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');

                // Jeśli to ostatnia cyfra liczby — dodaj ją do wyniku
                if (i == expression.length() - 1 || !Character.isDigit(expression.charAt(i + 1))) {
                    result += sign * number;
                    number = 0;
                }
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                // Zachowujemy bieżący kontekst (wynik i znak)
                valueStack.push(result);
                signStack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Zakończenie nawiasu: połącz bieżący wynik z tym sprzed nawiasu
                result = valueStack.pop() + signStack.pop() * result;
            }
        }

        return result;
    }
}
