package pl.jakubtworek.easy.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.binary_search.NonIntuitiveSearchSpace.maxCuttingHeight;

class NonIntuitiveSearchSpaceTest {

    @ParameterizedTest(name = "[{index}] heights={0}, k={1} => expected={2}")
    @MethodSource("cuttingHeightTestData")
    void testMaxCuttingHeight(List<Integer> heights, int k, int expected) {
        int result = maxCuttingHeight(heights, k);
        assertEquals(expected, result);
    }

    static Stream<Arguments> cuttingHeightTestData() {
        return Stream.of(
                Arguments.of(List.of(20, 15, 10, 17), 7, 15),     // klasyczny przykład
                Arguments.of(List.of(1, 2, 3), 10, 0),            // niemożliwe do uzyskania
                Arguments.of(List.of(5, 5, 5), 3, 4),             // równa wysokość
                Arguments.of(List.of(100), 99, 1),               // jedno drzewo
                Arguments.of(List.of(), 10, 0)                   // pusta lista
        );
    }
}
