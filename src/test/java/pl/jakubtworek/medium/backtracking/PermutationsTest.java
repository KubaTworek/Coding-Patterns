package pl.jakubtworek.medium.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.backtracking.Permutations.findAllPermutations;
import static pl.jakubtworek.medium.backtracking.Permutations.nQueens;

class PermutationsTest {

    @ParameterizedTest
    @MethodSource("permProvider")
    void testPermutations(List<Integer> input, int expectedSize) {
        List<List<Integer>> result = findAllPermutations(input);
        assertEquals(expectedSize, result.size());
    }

    static Stream<Arguments> permProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2), 2),
                Arguments.of(List.of(1, 2, 3), 6)
        );
    }

    @ParameterizedTest
    @MethodSource("nQueensProvider")
    void testNQueens(int n, int expected) {
        assertEquals(expected, nQueens(n));
    }

    static Stream<Arguments> nQueensProvider() {
        return Stream.of(
                Arguments.of(4, 2),
                Arguments.of(1, 1),
                Arguments.of(2, 0)
        );
    }
}
