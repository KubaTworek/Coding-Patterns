package pl.jakubtworek.easy.sliding_windw;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.sliding_windw.FixedSlidingWindow.countAnagramSubstrings;

class FixedSlidingWindowTest {

    @ParameterizedTest(name = "[{index}] s=\"{0}\", t=\"{1}\" => {2}")
    @CsvSource({
            "cbaebabacd, abc, 2",   // 'abc' found as 'cba', 'bac'
            "abab, ab, 3",          // 'ab', 'ba', 'ab'
            "af, fa, 1",            // 'fa' is an anagram of 'fa'
            "abcdefg, hgf, 0",      // no match
            "aaaaa, aa, 4",         // 'aa' appears 4 times (overlapping)
            "a, a, 1",              // exact match
            "a, b, 0",              // no match
            "'', a, 0",             // empty s
            "abc, '', 0",           // empty t
            "'', '', 0"            // both empty
    })
    void testCountAnagramSubstrings(String s, String t, int expected) {
        int result = countAnagramSubstrings(s, t);
        assertEquals(expected, result);
    }
}
