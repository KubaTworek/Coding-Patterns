package pl.jakubtworek.hard.bit_manipulation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.bit_manipulation.CountingBits.hammingWeightsOfIntegers;

class CountingBitsTest {
    @ParameterizedTest
    @MethodSource("hammingProvider")
    void testHammingWeights(int n, List<Integer> expected) {
        assertEquals(expected, hammingWeightsOfIntegers(n));
    }

    static Stream<Arguments> hammingProvider() {
        return Stream.of(
                Arguments.of(2, List.of(0, 1, 1)),
                Arguments.of(5, List.of(0, 1, 1, 2, 1, 2))
        );
    }
}
