package pl.jakubtworek.easy.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.hash_maps_and_sets.HashSets.*;

class HashSetsTest {

    @ParameterizedTest(name = "[{index}] Sudoku valid = {1}")
    @MethodSource("provideSudokuBoards")
    void testIsValidSudoku(List<List<Integer>> board, boolean expectedValid) {
        assertEquals(expectedValid, isValidSudoku(board));
    }

    static Stream<Arguments> provideSudokuBoards() {
        return Stream.of(
                Arguments.of( // ✅ poprawna plansza
                        List.of(
                                List.of(5, 3, 0, 0, 7, 0, 0, 0, 0),
                                List.of(6, 0, 0, 1, 9, 5, 0, 0, 0),
                                List.of(0, 9, 8, 0, 0, 0, 0, 6, 0),
                                List.of(8, 0, 0, 0, 6, 0, 0, 0, 3),
                                List.of(4, 0, 0, 8, 0, 3, 0, 0, 1),
                                List.of(7, 0, 0, 0, 2, 0, 0, 0, 6),
                                List.of(0, 6, 0, 0, 0, 0, 2, 8, 0),
                                List.of(0, 0, 0, 4, 1, 9, 0, 0, 5),
                                List.of(0, 0, 0, 0, 8, 0, 0, 7, 9)
                        ), true
                ),
                Arguments.of( // ❌ duplikat w wierszu
                        List.of(
                                List.of(5, 3, 3, 0, 7, 0, 0, 0, 0),
                                List.of(6, 0, 0, 1, 9, 5, 0, 0, 0),
                                List.of(0, 9, 8, 0, 0, 0, 0, 6, 0),
                                List.of(8, 0, 0, 0, 6, 0, 0, 0, 3),
                                List.of(4, 0, 0, 8, 0, 3, 0, 0, 1),
                                List.of(7, 0, 0, 0, 2, 0, 0, 0, 6),
                                List.of(0, 6, 0, 0, 0, 0, 2, 8, 0),
                                List.of(0, 0, 0, 4, 1, 9, 0, 0, 5),
                                List.of(0, 0, 0, 0, 8, 0, 0, 7, 9)
                        ), false
                ),
                Arguments.of( // ❌ duplikat w kolumnie
                        List.of(
                                List.of(5, 3, 0, 0, 7, 0, 0, 0, 0),
                                List.of(6, 0, 0, 1, 9, 5, 0, 0, 0),
                                List.of(5, 9, 8, 0, 0, 0, 0, 6, 0),
                                List.of(8, 0, 0, 0, 6, 0, 0, 0, 3),
                                List.of(4, 0, 0, 8, 0, 3, 0, 0, 1),
                                List.of(7, 0, 0, 0, 2, 0, 0, 0, 6),
                                List.of(0, 6, 0, 0, 0, 0, 2, 8, 0),
                                List.of(0, 0, 0, 4, 1, 9, 0, 0, 5),
                                List.of(0, 0, 0, 0, 8, 0, 0, 7, 9)
                        ), false
                ),
                Arguments.of( // ❌ duplikat w boxie
                        List.of(
                                List.of(5, 3, 0, 0, 7, 0, 0, 0, 0),
                                List.of(6, 0, 0, 1, 9, 5, 0, 0, 0),
                                List.of(0, 9, 5, 0, 0, 0, 0, 6, 0),
                                List.of(8, 0, 0, 0, 6, 0, 0, 0, 3),
                                List.of(4, 0, 0, 8, 0, 3, 0, 0, 1),
                                List.of(7, 0, 0, 0, 2, 0, 0, 0, 6),
                                List.of(0, 6, 0, 0, 0, 0, 2, 8, 0),
                                List.of(0, 0, 0, 4, 1, 9, 0, 0, 5),
                                List.of(0, 0, 0, 0, 8, 0, 0, 7, 9)
                        ), false
                )
        );
    }

    @ParameterizedTest(name = "[{index}] zero-strip input → output match")
    @MethodSource("provideBoardsForZeroStriping")
    void testApplyZeroStriping(List<List<Integer>> input, List<List<Integer>> expected) {
        List<List<Integer>> board = deepCopy(input);
        applyZeroStriping(board);
        assertEquals(expected, board);
    }

    static Stream<Arguments> provideBoardsForZeroStriping() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3),
                                List.of(4, 0, 6),
                                List.of(7, 8, 9)
                        ),
                        List.of(
                                List.of(1, 0, 3),
                                List.of(0, 0, 0),
                                List.of(7, 0, 9)
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3),
                                List.of(4, 5, 6),
                                List.of(7, 8, 9)
                        ),
                        List.of(
                                List.of(1, 2, 3),
                                List.of(4, 5, 6),
                                List.of(7, 8, 9)
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 0, 3),
                                List.of(4, 5, 6),
                                List.of(0, 8, 9)
                        ),
                        List.of(
                                List.of(0, 0, 0),
                                List.of(0, 0, 6),
                                List.of(0, 0, 0)
                        )
                )
        );
    }

    private static List<List<Integer>> deepCopy(List<List<Integer>> original) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> row : original) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLongestChainOfConsecutiveNumbers(List<Integer> input, int expected) {
        int result = longestConsecutiveSequenceLength(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(100, 4, 200, 1, 3, 2), 4), // 1,2,3,4),
                Arguments.of(List.of(0, -1, 1, 2), 4), // -1,0,1,2),
                Arguments.of(List.of(9, 1, 3, 10, 2, 4), 4), // 1,2,3,4),
                Arguments.of(List.of(5, 100, 101, 102, 103), 4), // 100-103),
                Arguments.of(List.of(5), 1), // tylko 1 liczba
                Arguments.of(List.of(), 0), // pusta lista
                Arguments.of(List.of(1, 2, 0, 1), 3) // 0,1,2 z duplikatem
        );
    }
}
