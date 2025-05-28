package pl.jakubtworek.medium.prefix_sums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.prefix_sums.ProductSums.productArrayWithoutCurrentElement;

class ProductSumsTest {
    @ParameterizedTest
    @MethodSource("provideArraysForProductTest")
    void testProductArrayWithoutCurrentElement(List<Integer> input, List<Integer> expected) {
        List<Integer> actual = productArrayWithoutCurrentElement(input);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> provideArraysForProductTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4), List.of(24, 12, 8, 6)),
                Arguments.of(List.of(2, 3, 4, 5), List.of(60, 40, 30, 24)),
                Arguments.of(List.of(1, 1, 1, 1), List.of(1, 1, 1, 1)),
                Arguments.of(List.of(5, 0, 2), List.of(0, 10, 0)),
                Arguments.of(List.of(0, 0, 2), List.of(0, 0, 0)),
                Arguments.of(List.of(10), List.of(1)), // edge case: 1 element
                Arguments.of(List.of(), List.of())     // edge case: empty list
        );
    }
}
