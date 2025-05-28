package pl.jakubtworek.medium.prefix_sums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.prefix_sums.SubarraySums.kSumSubArrays;
import static pl.jakubtworek.medium.prefix_sums.SubarraySums.sumBetweenRange;

class SubarraySumsTest {

    @ParameterizedTest
    @MethodSource("provideArraysForSum")
    void testSumBetweenRange(int[] arr, int start, int end, int expectedSum) {
        int actual = sumBetweenRange(arr, start, end);
        assertEquals(expectedSum, actual);
    }

    static Stream<Arguments> provideArraysForSum() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 0, 2, 6),    // 1 + 2 + 3 = 6
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 1, 3, 9),    // 2 + 3 + 4 = 9
                Arguments.of(new int[]{5, 5, 5, 5, 5}, 0, 4, 25),   // cały zakres
                Arguments.of(new int[]{10, 20, 30}, 1, 2, 50),      // 20 + 30
                Arguments.of(new int[]{-5, 10, -15, 20}, 0, 3, 10), // suma z liczbami ujemnymi
                Arguments.of(new int[]{7}, 0, 0, 7),                // pojedynczy element
                Arguments.of(new int[]{}, 0, 0, 0)                  // pusty przypadek – opcjonalnie (powinien rzucić wyjątek)
        );
    }


    @ParameterizedTest
    @MethodSource("provideArraysForKSum")
    void testKSumSubArrays(int[] arr, int k, int expected) {
        int actual = kSumSubArrays(arr, k);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> provideArraysForKSum() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1}, 2, 2),            // [1,1] (twice)
                Arguments.of(new int[]{1, 2, 3}, 3, 2),            // [1,2], [3]
                Arguments.of(new int[]{1, 2, -1, 1, 2}, 3, 3),     // [1,2], [1,2,-1,1], [1,2] (from index 3)
                Arguments.of(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7, 4),
                Arguments.of(new int[]{1, -1, 1, -1, 1}, 0, 6),    // multiple zero-sum subarrays
                Arguments.of(new int[]{5}, 5, 1),                  // one-element match
                Arguments.of(new int[]{5}, 0, 0),                  // no match
                Arguments.of(new int[]{}, 0, 0)                    // empty array
        );
    }
}
