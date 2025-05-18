package pl.jakubtworek.easy.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.jakubtworek.easy.hash_maps_and_sets.HashMaps.countGeometricTriplets;
import static pl.jakubtworek.easy.hash_maps_and_sets.HashMaps.findTwoSumIndicesUnsorted;

class HashMapsTest {

    @ParameterizedTest(name = "[{index}] nums={0}, target={1} → expected={2}")
    @MethodSource("provideTwoSumCases")
    void testFindTwoSumIndicesUnsorted(List<Integer> nums, int target, List<Integer> expected) {
        List<Integer> result = findTwoSumIndicesUnsorted(nums, target);

        if (expected.isEmpty()) {
            assertTrue(result.isEmpty(), "Expected empty result, but got: " + result);
        } else {
            assertEquals(2, result.size(), "Result should contain exactly two indices.");
            int i = result.get(0);
            int j = result.get(1);

            assertNotEquals(i, j, "Indices should be different.");
            assertEquals(target, nums.get(i) + nums.get(j),
                    () -> String.format("nums[%d] + nums[%d] != %d", i, j, target));
        }
    }

    static Stream<Arguments> provideTwoSumCases() {
        return Stream.of(
                Arguments.of(List.of(2, 7, 11, 15), 9, List.of(0, 1)),
                Arguments.of(List.of(3, 2, 4), 6, List.of(1, 2)),
                Arguments.of(List.of(3, 3), 6, List.of(0, 1)),
                Arguments.of(List.of(1, 2, 3, 4), 8, List.of()),       // brak rozwiązania
                Arguments.of(List.of(0, -1, 2, -3, 1), -2, List.of(1, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("provideGeometricTripletsTestCases")
    void testCountGeometricTriplets(List<Long> nums, long ratio, long expected) {
        long result = countGeometricTriplets(nums, ratio);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideGeometricTripletsTestCases() {
        return Stream.of(
                Arguments.of(List.of(1L, 4L, 16L, 64L), 4, 2),
                Arguments.of(List.of(1L, 2L, 2L, 4L), 2, 2),
                Arguments.of(List.of(1L, 1L, 1L, 1L), 1, 4),
                Arguments.of(List.of(1L, 3L, 9L, 9L, 27L, 81L), 3, 6),
                Arguments.of(List.of(1L, 5L, 5L, 25L, 125L), 5, 4),
                Arguments.of(List.of(1L, 2L, 3L, 4L, 5L), 2, 0),
                Arguments.of(List.of(), 2, 0),
                Arguments.of(List.of(1L), 3, 0)
        );
    }
}
