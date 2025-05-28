package pl.jakubtworek.medium.intervals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.intervals.ManagingOverlaps.getOverlappingIntervals;
import static pl.jakubtworek.medium.intervals.ManagingOverlaps.mergeOverlappingIntervals;

class ManagingOverlapsTest {
    @ParameterizedTest
    @MethodSource("intervalProvider")
    void testMergeOverlappingIntervals(List<Interval> input, List<Interval> expected) {
        List<Interval> result = mergeOverlappingIntervals(input);
        assertEquals(expected, result);
    }

    static Stream<Arguments> intervalProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)),
                        List.of(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18))
                ),
                Arguments.of(
                        List.of(new Interval(1, 4), new Interval(4, 5)),
                        List.of(new Interval(1, 5))
                ),
                Arguments.of(
                        List.of(new Interval(1, 2), new Interval(3, 4)),
                        List.of(new Interval(1, 2), new Interval(3, 4))
                ),
                Arguments.of(
                        List.of(),
                        List.of()
                ),
                Arguments.of(
                        List.of(new Interval(1, 10)),
                        List.of(new Interval(1, 10))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideOverlappingIntervals")
    void testGetOverlappingIntervals(List<Interval> input, List<Interval> expected) {
        List<Interval> result = getOverlappingIntervals(input);
        assertEquals(expected, result);
    }

    static Stream<Arguments> provideOverlappingIntervals() {
        return Stream.of(
                // Przykład 1:  [1, 5] i [3, 7] -> nakładają się na [3,5]
                Arguments.of(
                        List.of(new Interval(1, 5), new Interval(3, 7)),
                        List.of(new Interval(3, 5))
                ),
                // Przykład 2: [1, 2], [3, 4] -> brak nakładania
                Arguments.of(
                        List.of(new Interval(1, 2), new Interval(3, 4)),
                        List.of()
                ),
                // Przykład 3: [1, 10], [5, 8] -> nakładają się na [5, 8]
                Arguments.of(
                        List.of(new Interval(1, 10), new Interval(5, 8)),
                        List.of(new Interval(5, 8))
                ),
                // Przykład 4: [2, 6], [4, 9], [5, 7] -> kilka warstw nakładania
                Arguments.of(
                        List.of(new Interval(2, 6), new Interval(4, 9), new Interval(5, 7)),
                        List.of(new Interval(4, 7))
                ),
                // Przykład 5: Pusta lista
                Arguments.of(
                        List.of(),
                        List.of()
                )
/*
                // Przykład 6: Jeden przedział
                Arguments.of(
                        List.of(new Interval(1, 5)),
                        List.of()
                )
*/
        );
    }
}
