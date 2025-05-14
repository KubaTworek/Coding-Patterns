package pl.jakubtworek.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.jakubtworek.hash_maps_and_sets.HashMaps.pairSumUnsorted;

class HashMapsTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testPairSumUnsorted(List<Integer> nums, int target, List<Integer> expectedIndices) {
        List<Integer> result = pairSumUnsorted(nums, target);

        if (expectedIndices.isEmpty()) {
            assertTrue(result.isEmpty());
        } else {
            int i = result.get(0);
            int j = result.get(1);
            assertEquals(target, nums.get(i) + nums.get(j));
            assertNotEquals(i, j);
        }
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(2, 7, 11, 15), 9, List.of(0, 1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(3, 2, 4), 6, List.of(1, 2)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(3, 3), 6, List.of(0, 1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3, 4), 8, List.of()
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(0, -1, 2, -3, 1), -2, List.of(1, 3)
                )
        );
    }
}
