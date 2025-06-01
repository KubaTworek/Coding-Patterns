package pl.jakubtworek.hard.math_and_geometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.math_and_geometry.Math.reverse32BitInteger;

class MathTest {

    @ParameterizedTest
    @MethodSource("reverseProvider")
    void testReverse32BitInteger(int input, int expected) {
        assertEquals(expected, reverse32BitInteger(input));
    }

    static Stream<Arguments> reverseProvider() {
        return Stream.of(
                Arguments.of(123, 321),
                Arguments.of(-123, -321),
                Arguments.of(120, 21),
                Arguments.of(0, 0),
                Arguments.of(1534236469, 0),          // overflow
                Arguments.of(-2147483648, 0),         // INT_MIN
                Arguments.of(1463847412, 2147483641)  // edge safe case
        );
    }
}
