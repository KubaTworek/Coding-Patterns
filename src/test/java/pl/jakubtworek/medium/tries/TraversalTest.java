package pl.jakubtworek.medium.tries;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.tries.Traversal.findAllWordsOnBoard;

class TraversalTest {

    @ParameterizedTest
    @MethodSource("provideBoardsAndWords")
    void testFindAllWords(List<List<Character>> board, List<String> words, Set<String> expected) {
        List<String> result = findAllWordsOnBoard(board, words);
        assertEquals(expected, Set.copyOf(result));
    }

    static Stream<Arguments> provideBoardsAndWords() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of('o', 'a', 'a', 'n'),
                                List.of('e', 't', 'a', 'e'),
                                List.of('i', 'h', 'k', 'r'),
                                List.of('i', 'f', 'l', 'v')
                        ),
                        List.of("oath", "pea", "eat", "rain"),
                        Set.of("oath", "eat")
                ),
                Arguments.of(
                        List.of(
                                List.of('a', 'b'),
                                List.of('c', 'd')
                        ),
                        List.of("ab", "abcd", "adcb", "acdb"),
                        Set.of("ab", "acdb")
                ),
                Arguments.of(
                        List.of(
                                List.of('a')
                        ),
                        List.of("a", "b", "aa"),
                        Set.of("a")
                )
        );
    }
}
