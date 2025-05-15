package pl.jakubtworek.sliding_windw;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.sliding_windw.DynamicSlidingWindow.longestSubstringWithUniqueCharacters;
import static pl.jakubtworek.sliding_windw.DynamicSlidingWindow.longestUniformSubstringAfterReplacement;

public class DynamicSlidingWindowTest {

    @ParameterizedTest
    @CsvSource({
            "'abcabcbb', 3",   // "abc"
            "'bbbbb', 1",      // "b"
            "'pwwkew', 3",     // "wke"
            "'', 0",           // pusty string
            "'a', 1",          // pojedynczy znak
            "'abcdef', 6",     // wszystkie znaki unikalne
            "'abba', 2",       // "ab" lub "ba"
            "'dvdf', 3",       // "vdf"
            "'tmmzuxt', 5"     // "mzuxt"
    })
    void testLongestSubstringWithUniqueCharacters(String input, int expected) {
        int result = longestSubstringWithUniqueCharacters(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "AABABBA, 1, 4",
            "ABAB, 2, 4",
            "ABAA, 0, 2",
            "AAAA, 2, 4",
            "ABCABC, 2, 4",
            "A, 0, 1",
            "'', 3, 0",
            "ABCD, 1, 2",
            "ABBB, 2, 4",
            "AAABBC, 1, 4"
    })
    void testLongestUniformSubstringAfterReplacement(String input, int k, int expected) {
        int result = longestUniformSubstringAfterReplacement(input, k);
        assertEquals(expected, result);
    }
}
