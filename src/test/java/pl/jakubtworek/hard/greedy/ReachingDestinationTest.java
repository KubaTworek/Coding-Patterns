package pl.jakubtworek.hard.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.jakubtworek.hard.greedy.ReachingDestination.gasStations;
import static pl.jakubtworek.hard.greedy.ReachingDestination.jumpToTheEnd;

class ReachingDestinationTest {

    @ParameterizedTest
    @MethodSource("jumpProvider")
    void testJumpToTheEnd(List<Integer> nums, boolean expected) {
        assertEquals(expected, jumpToTheEnd(nums));
    }

    static Stream<Arguments> jumpProvider() {
        return Stream.of(
                Arguments.of(List.of(2, 3, 1, 1, 4), true),    // można skoczyć do końca
                Arguments.of(List.of(3, 2, 1, 0, 4), false),   // utkniesz na 3
                Arguments.of(List.of(0), true),               // już jesteś na końcu
                Arguments.of(List.of(1, 0, 1, 0), false)
        );
    }

    @ParameterizedTest
    @MethodSource("gasProvider")
    void testGasStations(List<Integer> gas, List<Integer> cost, int expected) {
        assertEquals(expected, gasStations(gas, cost));
    }

    static Stream<Arguments> gasProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), List.of(3, 4, 5, 1, 2), 3),
                Arguments.of(List.of(2, 3, 4), List.of(3, 4, 3), -1),
                Arguments.of(List.of(5, 1, 2, 3, 4), List.of(4, 4, 1, 5, 1), 4)
        );
    }
}
