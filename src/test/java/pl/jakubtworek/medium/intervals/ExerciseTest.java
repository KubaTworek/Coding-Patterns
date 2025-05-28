package pl.jakubtworek.medium.intervals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.intervals.Exercise.allIntervalsOverlapAtLeastOne;
import static pl.jakubtworek.medium.intervals.Exercise.eraseOverlappingIntervals;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideIntervalsForAllOverlapCheck")
    void testAllIntervalsOverlapAtLeastOne(List<Interval> input, boolean expected) {
        assertEquals(expected, allIntervalsOverlapAtLeastOne(input));
    }

    static Stream<Arguments> provideIntervalsForAllOverlapCheck() {
        return Stream.of(
                Arguments.of(List.of(new Interval(1, 5), new Interval(2, 6), new Interval(7, 8)), false),
                Arguments.of(List.of(new Interval(1, 3), new Interval(2, 4), new Interval(3, 5)), true),
                Arguments.of(List.of(new Interval(1, 7), new Interval(2, 3), new Interval(4, 5)), true),
                Arguments.of(List.of(new Interval(1, 2)), false),
                Arguments.of(List.of(new Interval(1, 5), new Interval(4, 6)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForEraseOverlapping")
    void testEraseOverlappingIntervals(List<Interval> input, int expected) {
        assertEquals(expected, eraseOverlappingIntervals(input));
    }

    static Stream<Arguments> provideForEraseOverlapping() {
        return Stream.of(
                Arguments.of(List.of(new Interval(1,3), new Interval(2,4), new Interval(3,5)), 1),
                Arguments.of(List.of(new Interval(1,2), new Interval(2,3), new Interval(3,4)), 0),
                Arguments.of(List.of(new Interval(1,5), new Interval(2,3), new Interval(3,4)), 1)
        );
    }
}
