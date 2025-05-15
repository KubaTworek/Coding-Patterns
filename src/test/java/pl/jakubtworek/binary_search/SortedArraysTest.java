package pl.jakubtworek.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.binary_search.SortedArrays.*;

public class SortedArraysTest {
    @ParameterizedTest
    @MethodSource("provideData1")
    void testFindTheInsertionIndex(List<Integer> list, int k, int expectedIndex) {
        int result = findTheInsertionIndex(list, k);
        assertEquals(expectedIndex, result);
    }

    private static Stream<Arguments> provideData1() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 3, 5, 7), 4, 2),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 3, 5, 7), 5, 2),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 3, 5, 7), 0, 0),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 3, 5, 7), 8, 4),
                org.junit.jupiter.params.provider.Arguments.of(List.of(), 10, 0),
                org.junit.jupiter.params.provider.Arguments.of(List.of(2, 4, 6, 8, 10), 9, 4),
                org.junit.jupiter.params.provider.Arguments.of(List.of(2, 4, 6, 8, 10), 1, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideData2")
    void testFindFirstAndLastOccurrences(List<Integer> list, int k, List<Integer> expected) {
        List<Integer> result = findFirstAndLastOccurrences(list, k);
        assertEquals(expected, result);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideData2() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 2, 4, 4, 4, 5, 6), 4, List.of(2, 4)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 2, 3, 4, 5), 3, List.of(2, 2)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 2, 4, 5), 3, List.of(-1, -1)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(3, 3, 3, 4, 5), 3, List.of(0, 2)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 2, 3, 4, 5, 6, 6), 6, List.of(5, 6)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(), 4, List.of(-1, -1))
        );
    }

}
