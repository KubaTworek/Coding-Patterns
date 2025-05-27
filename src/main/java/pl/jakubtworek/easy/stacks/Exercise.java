package pl.jakubtworek.easy.stacks;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }

        return stack.pop();
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
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            }
            stack.push(s.charAt(i));
        }

        return stack.stream().map(String::valueOf).collect(Collectors.joining());
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
        String expression = s.replaceAll(" ", "");
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                if (i == 0) {
                    stack.push('+');
                } else {
                    if (expression.charAt(i - 1) == '-' && expression.charAt(i + 1) == '+') {
                        stack.push('-');
                    } else if (expression.charAt(i - 1) == '+' && expression.charAt(i + 1) == '+') {
                        stack.push('+');

                    } else if (expression.charAt(i - 1) == '+' && expression.charAt(i + 1) == '-') {
                        stack.push('+');

                    } else if (expression.charAt(i - 1) == '-' && expression.charAt(i + 1) == '-') {
                        stack.push('-');
                    }
                }
            }
            if (expression.charAt(i) != ')' && expression.charAt(i) != '(') {
                stack.push(expression.charAt(i));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            int number = Integer.parseInt(String.valueOf(stack.pop()));

            if (!stack.isEmpty()) {
                if (stack.pop() == '+') {
                    sum += number;
                } else {
                    sum -= number;
                }
            } else {
                sum += number;
            }

        }

        return sum;
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
