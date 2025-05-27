package pl.jakubtworek.easy.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.stacks.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideRpnExpressions")
    void testEvaluateReversePolishNotation(List<String> input, int expected) {
        assertEquals(expected, evaluateReversePolishNotation(input));
    }

    private static Stream<Arguments> provideRpnExpressions() {
        return Stream.of(
                Arguments.of(List.of("2", "1", "+", "3", "*"), 9),
                Arguments.of(List.of("4", "13", "5", "/", "+"), 6),
                Arguments.of(List.of("10", "6", "9", "3", "/", "-", "*"), 30)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'(()())(())', '()()()'",
            "'(()())(())(()(()))', '()()()()()'",
            "'()()', '()()'"
    })

    void testRemoveOuterParentheses(String input, String expected) {
        assertEquals(expected, removeOuterParentheses(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'1 + 1', 2",
            "' 2-1 + 2 ', 3",
            "'(1+(4+5+2)-3)+(6+8)', 23"
    })
    void testCalculateBasicExpression(String expression, int expected) {
        assertEquals(expected, calculateBasicExpression(expression));
    }

    @ParameterizedTest
    @MethodSource("provideRemoveKDuplicateCases")
    void testRemoveKAdjacentDuplicates(String s, int k, String expected) {
        assertEquals(expected, removeKAdjacentDuplicates(s, k));
    }

    private static Stream<Arguments> provideRemoveKDuplicateCases() {
        return Stream.of(
                Arguments.of("deeedbbcccbdaa", 3, "aa"),
                Arguments.of("pbbcggttciiippooaais", 2, "ps"),
                Arguments.of("abcd", 2, "abcd")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'(1+(2*3)+((8)/4))+1', 3",
            "'(1)+((2))+(((3)))', 3",
            "'1+(2*3)/(2-1)', 1",
            "'1', 0"
    })
    void testMaxNestingDepthOfParentheses(String s, int expected) {
        assertEquals(expected, maxNestingDepthOfParentheses(s));
    }
}
