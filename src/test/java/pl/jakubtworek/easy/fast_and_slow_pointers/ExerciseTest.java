package pl.jakubtworek.easy.fast_and_slow_pointers;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.fast_and_slow_pointers.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideFindPeakInMountainArray")
    void testFindPeakInMountainArray(List<Integer> input, int expected) {
        assertEquals(expected, findPeakInMountainArray(input));
    }

    static Stream<Arguments> provideFindPeakInMountainArray() {
        return Stream.of(
                Arguments.of(List.of(1, 3, 5, 4, 2), 2),
                Arguments.of(List.of(0, 2, 1), 1),
                Arguments.of(List.of(1, 2, 3, 2), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideHasModuloRepeatingCycle")
    void testHasModuloRepeatingCycle(int numerator, int base, boolean expected) {
        assertEquals(expected, hasModuloRepeatingCycle(numerator, base));
    }

    static Stream<Arguments> provideHasModuloRepeatingCycle() {
        return Stream.of(
                Arguments.of(1, 3, true),
                Arguments.of(1, 2, false),
                Arguments.of(7, 6, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsRepeatedPatternCycle")
    void testIsRepeatedPatternCycle(String input, boolean expected) {
        assertEquals(expected, isRepeatedPatternCycle(input));
    }

    static Stream<Arguments> provideIsRepeatedPatternCycle() {
        return Stream.of(
                Arguments.of("abab", true),
                Arguments.of("abcabcabc", true),
                Arguments.of("abcd", false),
                Arguments.of("aaaa", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindFirstNonCyclicNumberInTransform")
    void testFindFirstNonCyclicNumberInTransform(int input, int expected) {
        assertEquals(expected, findFirstNonCyclicNumberInTransform(input));
    }

    static Stream<Arguments> provideFindFirstNonCyclicNumberInTransform() {
        return Stream.of(
                Arguments.of(7, -1),
                Arguments.of(19, 1),
                Arguments.of(7, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDoesFunctionEventuallyStabilize")
    void testDoesFunctionEventuallyStabilize(List<Integer> mapping, int start, boolean expected) {
        assertEquals(expected, doesFunctionEventuallyStabilize(mapping, start));
    }

    static Stream<Arguments> provideDoesFunctionEventuallyStabilize() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 3), 0, true),
                Arguments.of(List.of(1, 0), 0, false),
                Arguments.of(List.of(2, 2, 2), 0, true)
        );
    }
}
