package pl.jakubtworek.medium.graphs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.graphs.BFS.matrixInfection;
import static pl.jakubtworek.medium.graphs.BFS.shortestTransformationSequence;

class BFSTest {

    @ParameterizedTest
    @MethodSource("provideMatrices")
    void testMatrixInfection(List<List<Integer>> input, int expected) {
        List<List<Integer>> copy = input.stream()
                .map(row -> new ArrayList<>(row).reversed())
                .toList();
        assertEquals(expected, matrixInfection(copy));
    }

    private static Stream<Arguments> provideMatrices() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(2, 1, 1),
                        List.of(1, 1, 0),
                        List.of(0, 1, 1)
                ), 4),

                Arguments.of(List.of(
                        List.of(2, 1, 1),
                        List.of(0, 1, 1),
                        List.of(1, 0, 1)
                ), -1), // nie da się zainfekować wszystkich

                Arguments.of(List.of(
                        List.of(2, 2),
                        List.of(2, 2)
                ), 0), // już wszystko zainfekowane

                Arguments.of(List.of(
                        List.of(0, 2),
                        List.of(1, 1)
                ), 2) // infekcja schodzi na dół
        );
    }

    @ParameterizedTest
    @MethodSource("wordProvider")
    void testShortestTransformation(String start, String end, List<String> dict, int expected) {
        assertEquals(expected, shortestTransformationSequence(start, end, dict));
    }

    static Stream<Arguments> wordProvider() {
        return Stream.of(
                Arguments.of("hit", "cog",
                        List.of("hot", "dot", "dog", "lot", "log", "cog"), 5),
                Arguments.of("hit", "cog",
                        List.of("hot", "dot", "dog", "lot", "log"), 0), // brak "cog" w słowniku
                Arguments.of("game", "thee",
                        List.of("gane", "gaze", "gave", "have", "hive", "hike", "like", "bike", "bake", "take", "tale", "tele", "thee"), 0)
        );
    }
}
