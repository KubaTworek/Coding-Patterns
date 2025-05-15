package pl.jakubtworek.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NestedStructures {
    /**
    Algorytm isValidParenthesisExpression:

    Zadanie:
    Sprawdza, czy dane wyrażenie składające się z nawiasów: (), [], {} jest poprawnie zagnieżdżone
    i domknięte (czyli każdy otwarty nawias ma swój pasujący zamykający we właściwej kolejności).

    Działanie:
    1. Tworzymy mapę odpowiadającą nawiasom otwierającym i ich domknięciom: ( → ), [ → ], { → }.
    2. Iterujemy przez każdy znak w wyrażeniu:
       - Jeśli znak to nawias otwierający (np. `(`), odkładamy go na stos.
       - Jeśli znak to nawias zamykający (np. `)`):
         • sprawdzamy, czy na szczycie stosu znajduje się odpowiadający mu otwierający nawias;
         • jeśli tak, usuwamy go (pop);
         • jeśli nie — wyrażenie jest niepoprawne.
    3. Na końcu stos powinien być pusty — jeśli nie, wyrażenie jest niedomknięte.

    Złożoność:
    - Czasowa: O(n), gdzie n to długość wyrażenia.
    - Pamięciowa: O(n) — stos może w najgorszym przypadku zawierać wszystkie znaki.

    Uwagi:
    - Ignoruje inne znaki poza nawiasami.
    - Obsługuje tylko trzy typy nawiasów — łatwo rozszerzyć mapę o inne.
    */
    static boolean isValidParenthesisExpression(String expression) {
        Map<Character, Character> parenthesis = Map.of('(', ')', '[', ']', '{', '}');
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (parenthesis.containsKey(c)) {
                stack.push(c);
            } else if (parenthesis.containsValue(c)) {
                if (!stack.isEmpty() && parenthesis.get(stack.peek()).equals(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
    Algorytm evaluateExpression:

    Zadanie:
    Oblicza wartość arytmetycznego wyrażenia zawierającego liczby całkowite oraz operatory + i -,
    z możliwością zagnieżdżania za pomocą nawiasów (tylko okrągłych: ( )).

    Działanie:
    1. Przechodzimy znak po znaku po wyrażeniu:
       - Jeśli znak to cyfra — budujemy aktualną liczbę (`num`).
       - Jeśli pojawia się operator `+` lub `-`, zapisujemy znak (`sign`) do użycia przy najbliższej liczbie.
       - Jeśli trafiamy na `(`:
         • zapisujemy aktualny `result` i `sign` na stosy pomocnicze,
         • zerujemy `result` i `sign`, bo zaczynamy nowe podwyrażenie.
       - Jeśli trafiamy na `)`:
         • kończymy aktualne wyrażenie wewnętrzne,
         • wynik mnożymy przez znak sprzed nawiasu i dodajemy do wartości sprzed nawiasu.
    2. Po wyjściu z pętli dodajemy jeszcze ostatnią liczbę, jeśli istniała.

    Złożoność:
    - Czasowa: O(n), gdzie n to długość wyrażenia.
    - Pamięciowa: O(n) — stosy mogą zawierać tyle elementów, ile nawiasów (w przypadku maksymalnego zagnieżdżenia).

    Uwagi:
    - Obsługuje tylko +, - i nawiasy ( ).
    - Nie obsługuje ujemnych liczb jako liczby początkowej (np. `-4+2`) — można dodać łatwo.
    - Nie uwzględnia spacji ani błędnych znaków — zakłada poprawne wejście.
    */
    static int evaluateExpression(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Integer> signs = new Stack<>();

        int result = 0;
        int sign = 1;
        int num = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');

                if (i == expression.length() - 1 || !Character.isDigit(expression.charAt(i + 1))) {
                    result += sign * num;
                    num = 0;
                }
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                values.push(result);
                signs.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result = values.pop() + signs.pop() * result;
            }
        }

        return result;
    }
}
