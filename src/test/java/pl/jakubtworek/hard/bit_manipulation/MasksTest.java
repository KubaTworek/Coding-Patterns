package pl.jakubtworek.hard.bit_manipulation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MasksTest {
    @ParameterizedTest
    @MethodSource("bitSwapProvider")
    void testSwapBits(int input, int expected) {
        assertEquals(expected, Masks.swapOddAndEvenBits(input));
    }

    static Stream<Arguments> bitSwapProvider() {
        return Stream.of(
                Arguments.of(0b1010, 0b0101),        // 10 -> 5
                Arguments.of(0b111000, 0b110100),    // 56 -> 52
                Arguments.of(0b1, 0b10)              // 1 -> 2
        );
    }
}
