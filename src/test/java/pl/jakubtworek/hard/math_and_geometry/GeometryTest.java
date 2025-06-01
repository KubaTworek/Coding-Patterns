package pl.jakubtworek.hard.math_and_geometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hard.math_and_geometry.Geometry.maximumCollinearPoints;
import static pl.jakubtworek.hard.math_and_geometry.Geometry.spiralTraversal;

class GeometryTest {

    @ParameterizedTest
    @MethodSource("spiralProvider")
    void testSpiralTraversal(List<List<Integer>> matrix, List<Integer> expected) {
        assertEquals(expected, spiralTraversal(matrix));
    }

    static Stream<Arguments> spiralProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(1, 2, 3),
                        List.of(4, 5, 6),
                        List.of(7, 8, 9)
                ), List.of(1, 2, 3, 6, 9, 8, 7, 4, 5)),

                Arguments.of(List.of(
                        List.of(1, 2),
                        List.of(3, 4)
                ), List.of(1, 2, 4, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("collinearProvider")
    void testMaximumCollinearPoints(List<List<Integer>> points, int expected) {
        assertEquals(expected, maximumCollinearPoints(points));
    }

    static Stream<Arguments> collinearProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(1, 1),
                        List.of(2, 2),
                        List.of(3, 3)
                ), 3),

                Arguments.of(List.of(
                        List.of(1, 1),
                        List.of(3, 2),
                        List.of(5, 3),
                        List.of(4, 1),
                        List.of(2, 3),
                        List.of(1, 4)
                ), 4)
        );
    }
}
