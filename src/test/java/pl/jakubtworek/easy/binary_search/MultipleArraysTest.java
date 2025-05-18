package pl.jakubtworek.easy.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.binary_search.MultipleArrays.findMedianOfTwoSortedArrays;

class MultipleArraysTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindMedianOfTwoSortedArrays(List<Integer> nums1, List<Integer> nums2, double expectedMedian) {
        double result = findMedianOfTwoSortedArrays(nums1, nums2);
        assertEquals(expectedMedian, result, 0.00001); // dopuszczalny błąd przy porównywaniu double
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 3), List.of(2), 2.0),
                Arguments.of(List.of(1, 2), List.of(3, 4), 2.5),
                Arguments.of(List.of(0, 0), List.of(0, 0), 0.0),
                Arguments.of(List.of(), List.of(1), 1.0),
                Arguments.of(List.of(2), List.of(), 2.0),
                Arguments.of(List.of(1, 3, 8, 9, 15), List.of(7, 11, 18, 19, 21, 25), 11.0),
                Arguments.of(List.of(1, 2, 3), List.of(4, 5, 6, 7), 4.0)
        );
    }
}
