package pl.jakubtworek.medium.intervals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.intervals.FindingValuesInSortedOrder.largestOverlapOfIntervals;

class FindingValuesInSortedOrderTest {


    @ParameterizedTest
    @MethodSource("intervalProvider")
    void testLargestOverlapOfIntervals(List<Interval> input, int expected) {
        int actual = largestOverlapOfIntervals(input);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> intervalProvider() {
        return Stream.of(
                // 3 przedziały nakładają się między 4 a 5
                Arguments.of(
                        List.of(new Interval(1, 5), new Interval(2, 6), new Interval(4, 7)),
                        3
                ),
                // 2 przedziały nakładają się między 2 a 3
                Arguments.of(
                        List.of(new Interval(1, 3), new Interval(2, 4)),
                        2
                ),
                // Brak nakładania
                Arguments.of(
                        List.of(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6)),
                        1
                ),
                // Wszystkie nachodzą na siebie przez cały czas
                Arguments.of(
                        List.of(new Interval(0, 10), new Interval(0, 10), new Interval(0, 10)),
                        3
                ),
                // Tylko jeden przedział
                Arguments.of(
                        List.of(new Interval(5, 10)),
                        1
                ),
                // Pusta lista
                Arguments.of(
                        List.of(),
                        0
                )
        );
    }
}
