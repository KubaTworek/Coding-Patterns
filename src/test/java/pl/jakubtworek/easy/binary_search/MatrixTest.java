package pl.jakubtworek.easy.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.binary_search.Matrix.searchTargetInSortedMatrix;

class MatrixTest {

    @ParameterizedTest
    @MethodSource("provideMatricesAndTargets")
    void testSearchTargetInSortedMatrix(List<List<Integer>> matrix, int target, boolean expected) {
        boolean result = searchTargetInSortedMatrix(matrix, target);
        assertEquals(expected, result);
    }

    static Stream<Arguments> provideMatricesAndTargets() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of(1, 3, 5, 7),
                                List.of(10, 11, 16, 20),
                                List.of(23, 30, 34, 60)
                        ),
                        3,
                        true
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 3, 5, 7),
                                List.of(10, 11, 16, 20),
                                List.of(23, 30, 34, 60)
                        ),
                        13,
                        false
                ),
                Arguments.of(
                        List.of(
                                List.of(1)
                        ),
                        1,
                        true
                ),
                Arguments.of(
                        List.of(
                                List.of(1)
                        ),
                        2,
                        false
                ),
                Arguments.of(
                        List.of(),
                        10,
                        false
                ),
                Arguments.of(
                        List.of(
                                List.of(),
                                List.of()
                        ),
                        10,
                        false
                )
        );
    }
}
