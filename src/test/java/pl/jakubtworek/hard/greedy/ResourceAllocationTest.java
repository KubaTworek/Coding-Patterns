package pl.jakubtworek.hard.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.greedy.ResourceAllocation.candies;

class ResourceAllocationTest {

    @ParameterizedTest
    @MethodSource("candyProvider")
    void testCandies(List<Integer> ratings, int expected) {
        assertEquals(expected, candies(ratings));
    }

    static Stream<Arguments> candyProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 0, 2), 5),           // 2 1 2
                Arguments.of(List.of(1, 2, 2), 4),           // 1 2 1
                Arguments.of(List.of(1, 3, 4, 5, 2), 11),    // 1 2 3 4 1
                Arguments.of(List.of(1, 2, 3, 1, 0), 9),     // 1 2 3 2 1
                Arguments.of(List.of(5, 4, 3, 2, 1), 15)     // 5 4 3 2 1
        );
    }

}
