package pl.jakubtworek.easy.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubtworek.easy.hash_maps_and_sets.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideSubstrings")
    void testFindLongestSubstringWithoutRepeatingCharacters(String input, int expected) {
        int result = findLongestSubstringWithoutRepeatingCharacters(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideSubstrings() {
        return Stream.of(
                Arguments.of("abcabcbb", 3),
                Arguments.of("bbbbb", 1),
                Arguments.of("pwwkew", 3),
                Arguments.of("", 0),
                Arguments.of("abcdef", 6)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSubarrays")
    void testCountSubarraysWithSum(List<Integer> nums, int target, int expected) {
        int result = countSubarraysWithSum(nums, target);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideSubarrays() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 1), 2, 2),
                Arguments.of(List.of(1, 2, 3), 3, 2),
                Arguments.of(List.of(0, 0, 0), 0, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTopK")
    void testTopKFrequentElements(List<Integer> nums, int k, List<Integer> expected) {
        List<Integer> result = topKFrequentElements(nums, k);
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    private static Stream<Arguments> provideTopK() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 1, 2, 2, 3), 2, List.of(1, 2)),
                Arguments.of(List.of(4, 4, 4, 5, 6, 5, 5), 1, List.of(5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsomorphicStrings")
    void testAreIsomorphicStrings(String s1, String s2, boolean expected) {
        boolean result = areIsomorphicStrings(s1, s2);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideIsomorphicStrings() {
        return Stream.of(
                Arguments.of("egg", "add", true),
                Arguments.of("foo", "bar", false),
                Arguments.of("paper", "title", true)
        );
    }

    @ParameterizedTest
    @MethodSource("providePatterns")
    void testWordPatternFollows(String pattern, String str, boolean expected) {
        boolean result = wordPatternFollows(pattern, str);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> providePatterns() {
        return Stream.of(
                Arguments.of("abba", "dog cat cat dog", true),
                Arguments.of("abba", "dog cat cat fish", false),
                Arguments.of("aaaa", "dog dog dog dog", true),
                Arguments.of("abba", "dog dog dog dog", false)
        );
    }
}
