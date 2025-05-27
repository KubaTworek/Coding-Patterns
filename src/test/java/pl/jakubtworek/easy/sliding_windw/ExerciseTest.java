package pl.jakubtworek.easy.sliding_windw;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.sliding_windw.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "'ADOBECODEBANC', 'ABC', 'BANC'",
            "'a', 'a', 'a'",
            "'a', 'aa', ''",
            "'abc', 'ac', 'abc'",
            "'aabbcc', 'abc', 'abbc'"
    })
    void testMinWindowContainingAllChars(String s, String pattern, String expected) {
        assertEquals(expected, minWindowContainingAllChars(s, pattern));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3', 3, 2",
            "'1,1,1', 2, 2",
            "'3,1,2,1', 4, 2",
            "'1,2,1,3,1', 3, 3"
    })
    void testCountSubarraysWithSumK(String input, int k, int expected) {
        int[] nums = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        assertEquals(expected, countSubarraysWithSumK(nums, k));
    }

    @ParameterizedTest
    @MethodSource("provideWindows")
    void testCountDistinctCharsInEveryWindow(String s, int k, List<Integer> expected) {
        assertEquals(expected, countDistinctCharsInEveryWindow(s, k));
    }

    private static Stream<Arguments> provideWindows() {
        return Stream.of(
                Arguments.of("abcba", 3, List.of(3, 2, 3)),
                Arguments.of("aaaaa", 2, List.of(1, 1, 1, 1)),
                Arguments.of("abcdef", 4, List.of(4, 4, 4)),
                Arguments.of("aababcabcd", 3, List.of(2, 2, 2, 3, 3, 3, 3, 3))
        );
    }
}
