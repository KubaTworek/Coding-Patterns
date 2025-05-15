package pl.jakubtworek.heaps;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.heaps.FindingValuesInSortedOrder.kMostFrequentStrings;

public class FindingValuesInSortedOrderTest {

    @ParameterizedTest
    @MethodSource("provideData")
    void testKMostFrequentStrings(List<String> input, int k, List<String> expected) {
        List<String> result = kMostFrequentStrings(input, k);
        assertEquals(expected, result);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of("a", "b", "a", "c", "b", "a", "d"), 2, List.of("a", "b")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of("a", "b", "a", "b", "a"), 3, List.of("a")
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of("x", "y", "z"), 2, List.of()
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of("m", "m", "n", "n", "n", "o", "o", "o", "o"), 2, List.of("o", "m", "n")
                )
        );
    }

}

