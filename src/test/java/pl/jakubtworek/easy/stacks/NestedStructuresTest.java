package pl.jakubtworek.easy.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.stacks.NestedStructures.evaluateSimpleExpressionWithParentheses;
import static pl.jakubtworek.easy.stacks.NestedStructures.validateBracketNesting;

class NestedStructuresTest {

    @ParameterizedTest
    @MethodSource("provideParenthesisValidationCases")
    void testIsValidParenthesisExpression(String input, boolean expected) {
        boolean result = validateBracketNesting(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideParenthesisValidationCases() {
        return Stream.of(
                Arguments.of("()", true),
                Arguments.of("([])", true),
                Arguments.of("({[]})", true),
                Arguments.of("(((())))", true),
                Arguments.of("[({})]", true),
                Arguments.of("", true),
                Arguments.of("(]", false),
                Arguments.of("([)]", false),
                Arguments.of("((()", false),
                Arguments.of("({[})]", false),
                Arguments.of("(()", false),
                Arguments.of(")(", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideExpressionEvaluationCases")
    void testEvaluateExpression(String expression, int expected) {
        int result = evaluateSimpleExpressionWithParentheses(expression);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideExpressionEvaluationCases() {
        return Stream.of(
                // Bez nawiasów
                Arguments.of("1+2", 3),
                Arguments.of("5-3+2", 4),
                Arguments.of("10+20-30", 0),
                Arguments.of("100", 100),

                // Proste nawiasy
                Arguments.of("1+(2+3)", 6),
                Arguments.of("5+(3-1)", 7),
                Arguments.of("10-(2+3)", 5),
                Arguments.of("7+(2-8)", 1),

                // Zagnieżdżone nawiasy
                Arguments.of("1+(2+(3+4))", 10),
                Arguments.of("10-(2+(3+5))", 0),
                Arguments.of("((1+2)+3)", 6),
                Arguments.of("(1+(4+5+2)-3)+(6+8)", 23),

                // Z minusem przed nawiasem
                Arguments.of("10-(3+2)", 5),
                Arguments.of("10-(2-(1+1))", 10),

                // Zera i pojedyncze liczby
                Arguments.of("0", 0),
                Arguments.of("(0)", 0),
                Arguments.of("(1)", 1),

                // Liczby ujemne (przez odejmowanie)
                Arguments.of("10-20", -10),
                Arguments.of("50-(10+40)", 0)
        );
    }
}
