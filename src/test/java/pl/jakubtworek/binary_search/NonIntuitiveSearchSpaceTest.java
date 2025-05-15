package pl.jakubtworek.binary_search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.binary_search.NonIntuitiveSearchSpace.cuttingWoods;

public class NonIntuitiveSearchSpaceTest {
    @ParameterizedTest
    @MethodSource("provideData")
    void testCuttingWoods(List<Integer> heights, int k, int expectedHeight) {
        int result = cuttingWoods(heights, k);
        assertEquals(expectedHeight, result);
    }

    private static Stream<Arguments> provideData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(List.of(20, 15, 10, 17), 7, 15),
                org.junit.jupiter.params.provider.Arguments.of(List.of(1, 2, 3), 10, 0),
                org.junit.jupiter.params.provider.Arguments.of(List.of(5, 5, 5), 3, 4),
                org.junit.jupiter.params.provider.Arguments.of(List.of(100), 99, 1),
                org.junit.jupiter.params.provider.Arguments.of(List.of(), 10, 0)
        );
    }
}
