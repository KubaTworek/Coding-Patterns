package pl.jakubtworek.medium.prefix_sums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.prefix_sums.Exercise.longestSubarraySumEqualsK;

class ExerciseTest {
    @ParameterizedTest
    @MethodSource("provideDataForLongestSubarray")
    void testLongestSubarraySumEqualsK(int[] arr, int k, int expected) {
        assertEquals(expected, longestSubarraySumEqualsK(arr, k));
    }

    static Stream<Arguments> provideDataForLongestSubarray() {
        return Stream.of(
                Arguments.of(new int[]{1, -1, 5, -2, 3}, 3, 4),
                Arguments.of(new int[]{-2, -1, 2, 1}, 1, 2),
                Arguments.of(new int[]{1, 2, 3}, 3, 2),
                Arguments.of(new int[]{1}, 1, 1),
                Arguments.of(new int[]{1}, 2, 0)
        );
    }
}
