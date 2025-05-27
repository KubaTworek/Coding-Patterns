package pl.jakubtworek.easy.hash_maps_and_sets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubtworek.easy.hash_maps_and_sets.Exercise.SubarrayFinder.shortestSubarrayWithAllUniques;
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
        System.out.println(result);
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    private static Stream<Arguments> provideTopK() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 1, 2, 2, 3), 2, List.of(1, 2)),
                Arguments.of(List.of(4, 4, 4, 5, 6, 5, 5), 1, List.of(4))
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
                Arguments.of("abba", "dog dog dog dog", false),
                Arguments.of("abca", "dog cat fish dog", true),
                Arguments.of("aacbc", "dog dog fish fish cat", false)
        );
    }

    static Stream<Arguments> provideTestCases1() {
        return Stream.of(
                Arguments.of(new int[]{4, 5, 1, 2, 0, 4}, 5),
                Arguments.of(new int[]{1, 2, 2, 1}, Integer.MIN_VALUE),
                Arguments.of(new int[]{3, 3, 2, 2, 4}, 4),
                Arguments.of(new int[]{}, Integer.MIN_VALUE),
                Arguments.of(new int[]{7}, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases1")
    void testFindFirstUnique(int[] input, int expected) {
        int result = findFirstUnique(input);
        assertEquals(expected, result);
    }


    static Stream<Arguments> provideTestCases2() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 3, 1, 2, 4, 3}, 4),
                Arguments.of(new int[]{7, 5, 2, 7, 2, 7, 4, 7}, 6),
                Arguments.of(new int[]{1, 1, 1, 1}, 1),
                Arguments.of(new int[]{}, 0),
                Arguments.of(new int[]{4, 3, 2, 1}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases2")
    void testShortestSubarrayWithAllUniques(int[] input, int expected) {
        int result = shortestSubarrayWithAllUniques(input);
        assertEquals(expected, result);
    }


    static Stream<Arguments> provideTestCases3() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 1, 3, 4, 2, 3}, 4, 4),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 3, 3),
                Arguments.of(new int[]{1, 1, 1, 1, 1}, 2, 1),
                Arguments.of(new int[]{}, 3, 0),
                Arguments.of(new int[]{1, 2}, 3, 0),
                Arguments.of(new int[]{4, 5, 5, 4, 6}, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases3")
    void testMaxUniqueInWindow(int[] input, int k, int expected) {
        int result = maxUniqueInWindow(input, k);
        assertEquals(expected, result);
    }
}
