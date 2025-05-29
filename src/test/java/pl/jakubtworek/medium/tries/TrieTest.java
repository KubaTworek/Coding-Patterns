package pl.jakubtworek.medium.tries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrieTest {

    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apex");
        trie.insert("banana");
    }

    @ParameterizedTest(name = "search(\"{0}\") == {1}")
    @MethodSource("provideSearchWords")
    @DisplayName("Test search(word)")
    void testSearch(String word, boolean expected) {
        assertEquals(expected, trie.search(word));
    }

    static Stream<Arguments> provideSearchWords() {
        return Stream.of(
                Arguments.of("apple", true),
                Arguments.of("app", true),
                Arguments.of("apex", true),
                Arguments.of("banana", true),
                Arguments.of("ban", false),
                Arguments.of("ap", false),
                Arguments.of("orange", false)
        );
    }

    @ParameterizedTest(name = "startsWith(\"{0}\") == {1}")
    @MethodSource("providePrefixWords")
    @DisplayName("Test startsWith(prefix)")
    void testStartsWith(String prefix, boolean expected) {
        assertEquals(expected, trie.startsWith(prefix));
    }

    static Stream<Arguments> providePrefixWords() {
        return Stream.of(
                Arguments.of("app", true),
                Arguments.of("ap", true),
                Arguments.of("ban", true),
                Arguments.of("bana", true),
                Arguments.of("banana", true),
                Arguments.of("banz", false),
                Arguments.of("z", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWildcardSearchCases")
    void testSearchWithWildcard(String pattern, boolean expected) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apex");
        trie.insert("bat");
        trie.insert("bad");

        assertEquals(expected, trie.searchWithWildcard(pattern));
    }

    static Stream<Arguments> provideWildcardSearchCases() {
        return Stream.of(
                Arguments.of("apple", true),
                Arguments.of("a.ple", true),
                Arguments.of("ap.le", true),
                Arguments.of(".....", true),  // matches "apple"
                Arguments.of("a....", true),
                Arguments.of("a..x", true),   // matches "apex"
                Arguments.of("b.t", true),
                Arguments.of("b.d", true),
                Arguments.of("b..", true),
                Arguments.of("......", false), // no 6-letter word
                Arguments.of(".....s", false)
        );
    }
}
