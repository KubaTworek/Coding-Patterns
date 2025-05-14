package pl.jakubtworek.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.hash_maps_and_sets.HashSets.verifySudokuBoard;
import static pl.jakubtworek.hash_maps_and_sets.HashSets.zeroStriping;

public class HashSetsTest {
    @ParameterizedTest
    @MethodSource("provideSudokuBoards")
    void testVerifySudokuBoard(List<List<Integer>> board, boolean expectedValid) {
        boolean result = verifySudokuBoard(board);
        assertEquals(expectedValid, result);
    }

    private static Stream<Arguments> provideSudokuBoards() {
        return Stream.of(
                // ✅ Poprawna plansza
                org.junit.jupiter.params.provider.Arguments.of(
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
                // ❌ Duplikat w wierszu
                org.junit.jupiter.params.provider.Arguments.of(
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
                // ❌ Duplikat w kolumnie
                org.junit.jupiter.params.provider.Arguments.of(
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
                // ❌ Duplikat w boxie
                org.junit.jupiter.params.provider.Arguments.of(
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

    @ParameterizedTest
    @MethodSource("provideBoards")
    void testZeroStriping(List<List<Integer>> input, List<List<Integer>> expected) {
        List<List<Integer>> board = deepCopy(input);

        zeroStriping(board);

        assertEquals(expected, board);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideBoards() {
        return Stream.of(
                // ✅ Jeden zero w środku – powinien wyczyścić wiersz i kolumnę
                org.junit.jupiter.params.provider.Arguments.of(
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
                // ✅ Brak zer – plansza nie powinna się zmienić
                org.junit.jupiter.params.provider.Arguments.of(
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
                // ✅ Zera w wielu miejscach – wszystkie odpowiednie rzędy i kolumny na zero
                org.junit.jupiter.params.provider.Arguments.of(
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
}
