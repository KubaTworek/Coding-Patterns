package pl.jakubtworek.hard.dynamic_programming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubtworek.hard.dynamic_programming.TwoDimensionalDP.*;

class TwoDimensionalDPTest {

    @ParameterizedTest
    @MethodSource("matrixPathwaysProvider")
    void testMatrixPathways(int m, int n, int expected) {
        assertEquals(expected, matrixPathways(m, n));
    }

    static Stream<Arguments> matrixPathwaysProvider() {
        return Stream.of(
                Arguments.of(2, 2, 2),
                Arguments.of(3, 2, 3),
                Arguments.of(3, 3, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("palindromeProvider")
    void testLongestPalindrome(String s, String expectedOneOf) {
        String result = longestPalindromeInString(s);
        assertTrue(expectedOneOf.contains(result)); // np. "bab" lub "aba"
    }

    static Stream<Arguments> palindromeProvider() {
        return Stream.of(
                Arguments.of("babad", "bab|aba"),
                Arguments.of("cbbd", "bb"),
                Arguments.of("a", "a"),
                Arguments.of("ac", "a|c")
        );
    }

    @ParameterizedTest
    @MethodSource("lcsProvider")
    void testLongestCommonSubsequence(String s1, String s2, int expected) {
        assertEquals(expected, longestCommonSubsequence(s1, s2));
    }

    static Stream<Arguments> lcsProvider() {
        return Stream.of(
                Arguments.of("abcde", "ace", 3),
                Arguments.of("abc", "abc", 3),
                Arguments.of("abc", "def", 0),
                Arguments.of("abc", "", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("knapsackProvider")
    void testZeroOneKnapsack(int cap, List<Integer> weights, List<Integer> values, int expected) {
        assertEquals(expected, ZeroOneKnapsack(cap, weights, values));
    }

    static Stream<Arguments> knapsackProvider() {
        return Stream.of(
                Arguments.of(5, List.of(1, 2, 3), List.of(6, 10, 12), 22),
                Arguments.of(50, List.of(10, 20, 30), List.of(60, 100, 120), 220)
        );
    }
}
