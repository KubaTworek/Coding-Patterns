package pl.jakubtworek.medium.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubsetsTest {

    @ParameterizedTest
    @MethodSource("subsetProvider")
    void testSubsets(List<Integer> input, int expectedSize) {
        List<List<Integer>> result = Subsets.findAllSubsets(input);
        assertEquals(expectedSize, result.size());
    }

    static Stream<Arguments> subsetProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2), 4),
                Arguments.of(List.of(1, 2, 3), 8)
        );
    }
}
