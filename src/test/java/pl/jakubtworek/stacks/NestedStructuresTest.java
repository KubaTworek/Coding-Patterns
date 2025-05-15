package pl.jakubtworek.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.stacks.NestedStructures.evaluateExpression;
import static pl.jakubtworek.stacks.NestedStructures.isValidParenthesisExpression;

public class NestedStructuresTest {
    @ParameterizedTest
    @CsvSource({
            "'()', true",
            "'([])', true",
            "'({[]})', true",
            "'(((())))', true",
            "'[({})]', true",
            "'', true",

            "'(]', false",
            "'([)]', false",
            "'((()', false",
            "'({[})]', false",
            "'(()', false",
            "')(', false"
    })
    void testIsValidParenthesisExpression(String input, boolean expected) {
        boolean result = isValidParenthesisExpression(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            // Bez nawiasów
            "'1+2', 3",
            "'5-3+2', 4",
            "'10+20-30', 0",
            "'100', 100",

            // Proste nawiasy
            "'1+(2+3)', 6",
            "'5+(3-1)', 7",
            "'10-(2+3)', 5",
            "'7+(2-8)', 1",

            // Zagnieżdżone nawiasy
            "'1+(2+(3+4))', 10",
            "'10-(2+(3+5))', 0",
            "'((1+2)+3)', 6",
            "'(1+(4+5+2)-3)+(6+8)', 23",

            // Z minusem przed nawiasem
            "'10-(3+2)', 5",
            "'10-(2-(1+1))', 10",

            // Zera i pojedyncze liczby
            "'0', 0",
            "'(0)', 0",
            "'(1)', 1",

            // Liczby ujemne (przez odejmowanie)
            "'10-20', -10",
            "'50-(10+40)', 0"
    })
    void testEvaluateExpression(String expression, int expected) {
        int result = evaluateExpression(expression);
        assertEquals(expected, result);
    }
}
