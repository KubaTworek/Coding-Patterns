package pl.jakubtworek.sliding_windw;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.sliding_windw.FixedSlidingWindow.substringAnagrams;

public class FixedSlidingWindowTest {
    @ParameterizedTest
    @CsvSource({
            "cbaebabacd, abc, 2",
            "abab, ab, 3",
            "af, fa, 1",
            "abcdefg, hgf, 0",
            "aaaaa, aa, 4",
            "a, a, 1",
            "a, b, 0",
            "'', a, 0",
            "abc, '', 0",
            "'', '', 0"
    })
    void testSubstringAnagrams(String s, String t, int expected) {
        int result = substringAnagrams(s, t);
        assertEquals(expected, result);
    }
}
