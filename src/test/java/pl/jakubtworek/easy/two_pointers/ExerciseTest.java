package pl.jakubtworek.easy.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.two_pointers.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideRemoveDuplicates")
    void testRemoveDuplicates(List<Integer> input, List<Integer> expected) {
        List<Integer> result = removeDuplicates(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideRemoveDuplicates() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 2, 2, 3), List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4)),
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(5, 5, 5, 5), List.of(5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDoubleValuePairs")
    void testHasDoubleValuePair(List<Integer> input, boolean expected) {
        boolean result = hasDoubleValuePair(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideDoubleValuePairs() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 6), true),
                Arguments.of(List.of(0, 0), true),
                Arguments.of(List.of(1, 3, 5), false),
                Arguments.of(List.of(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDifferencePairs")
    void testCountPairsWithDifference(List<Integer> nums, int k, int expected) {
        int result = countPairsWithDifference(nums, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideDifferencePairs() {
        return Stream.of(
                Arguments.of(List.of(1, 7, 5, 9, 2, 12, 3), 2, 4),
                Arguments.of(List.of(1, 1, 1, 1), 0, 6),
                Arguments.of(List.of(), 1, 0)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'civic', true",
            "'ivicc', true",
            "'hello', false",
            "'', true",
            "'aabb', true"
    })
    void testCanFormPalindrome(String input, boolean expected) {
        boolean result = canFormPalindrome(input);
        assertEquals(expected, result);
    }
}
