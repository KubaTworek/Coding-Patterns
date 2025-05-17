package pl.jakubtworek.easy.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.binary_search.SortedArrays.*;

class SortedArraysTest {

    @ParameterizedTest(name = "[{index}] list={0}, k={1} => index={2}")
    @MethodSource("insertionIndexData")
    void testBinarySearchInsertIndex(List<Integer> list, int k, int expectedIndex) {
        int actual = binarySearchInsertIndex(list, k);
        assertEquals(expectedIndex, actual);
    }

    static Stream<Arguments> insertionIndexData() {
        return Stream.of(
                Arguments.of(List.of(1, 3, 5, 7), 4, 2),
                Arguments.of(List.of(1, 3, 5, 7), 5, 2),
                Arguments.of(List.of(1, 3, 5, 7), 0, 0),
                Arguments.of(List.of(1, 3, 5, 7), 8, 4),
                Arguments.of(List.of(), 10, 0),
                Arguments.of(List.of(2, 4, 6, 8, 10), 9, 4),
                Arguments.of(List.of(2, 4, 6, 8, 10), 1, 0)
        );
    }

    @ParameterizedTest(name = "[{index}] list={0}, k={1} => range={2}")
    @MethodSource("occurrencesData")
    void testBinarySearchRangeForValue(List<Integer> list, int k, List<Integer> expectedRange) {
        List<Integer> actual = binarySearchRangeForValue(list, k);
        assertEquals(expectedRange, actual);
    }

    static Stream<Arguments> occurrencesData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 4, 4, 4, 5, 6), 4, List.of(2, 4)),
                Arguments.of(List.of(1, 2, 3, 4, 5), 3, List.of(2, 2)),
                Arguments.of(List.of(1, 2, 4, 5), 3, List.of(-1, -1)),
                Arguments.of(List.of(3, 3, 3, 4, 5), 3, List.of(0, 2)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 6), 6, List.of(5, 6)),
                Arguments.of(List.of(), 4, List.of(-1, -1))
        );
    }
}
