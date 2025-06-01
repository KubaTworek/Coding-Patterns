package pl.jakubtworek.hard.dynamic_programming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.dynamic_programming.OneDimensionalDP.*;

class OneDimensionalDPTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 3",
            "4, 5",
            "5, 8",
            "10, 89"
    })
    void testClimbingStairs(int n, int expected) {
        assertEquals(expected, climbingStairsTopDown(n));
        assertEquals(expected, climbingStairsBottomUp(n));
    }

    @ParameterizedTest
    @MethodSource("coinProvider")
    void testMinimumCoinCombination(List<Integer> coins, int target, int expected) {
        assertEquals(expected, minimumCoinCombinationTopDown(coins, target));
        assertEquals(expected, minimumCoinCombinationBottomUp(coins, target));
    }

    static Stream<Arguments> coinProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 5), 11, 3),     // 5 + 5 + 1
                Arguments.of(List.of(2), 3, -1),           // nie da się
                Arguments.of(List.of(1), 0, 0),
                Arguments.of(List.of(1), 2, 2),
                Arguments.of(List.of(1, 3, 4), 6, 2)       // 3 + 3
        );
    }

    @ParameterizedTest
    @MethodSource("houseProvider")
    void testRobbery(List<Integer> houses, int expected) {
        assertEquals(expected, neighborhoodBurglary(houses));
    }

    static Stream<Arguments> houseProvider() {
        return Stream.of(
                Arguments.of(List.of(2, 7, 9, 3, 1), 12),  // 2 + 9 + 1
                Arguments.of(List.of(1, 2, 3, 1), 4),      // 1 + 3
                Arguments.of(List.of(2, 1, 1, 2), 4),      // 2 + 2
                Arguments.of(List.of(1), 1),
                Arguments.of(List.of(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("subarrayProvider")
    void testMaxSubarray(List<Integer> nums, int expected) {
        assertEquals(expected, maximumSubarraySum(nums));
    }

    static Stream<Arguments> subarrayProvider() {
        return Stream.of(
                Arguments.of(List.of(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6), // 4 + -1 + 2 + 1
                Arguments.of(List.of(1), 1),
                Arguments.of(List.of(5, 4, -1, 7, 8), 23),
                Arguments.of(List.of(-1, -2, -3), -1)
        );
    }
}
