package pl.jakubtworek.easy.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.two_pointers.StagedTraversal.nextLexicographicalSequence;

class StagedTraversalTest {

    @ParameterizedTest
    @CsvSource({
            "ab, ba",
            "ba, ab",
            "abcd, abdc",
            "abdc, acbd",
            "dcba, abcd",
            "aabb, abab",
            "a, a",
            "cba, abc",
            "hefg, hegf",
            "dkhc, hcdk"
    })
    void testNextLexicographicalSequence(String input, String expected) {
        String result = nextLexicographicalSequence(input);
        assertEquals(expected, result);
    }
}
