package pl.jakubtworek.hard.bit_manipulation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.bit_manipulation.XOR.lonelyInteger;

class XORTest {
    @ParameterizedTest
    @MethodSource("xorProvider")
    void testLonelyInteger(List<Integer> nums, int expected) {
        assertEquals(expected, lonelyInteger(nums));
    }

    static Stream<Arguments> xorProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 2, 1), 3),
                Arguments.of(List.of(4, 4, 5, 6, 6), 5)
        );
    }
}
