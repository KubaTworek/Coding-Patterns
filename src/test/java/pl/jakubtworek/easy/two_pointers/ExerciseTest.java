package pl.jakubtworek.easy.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.two_pointers.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideRemoveDuplicates")
    void testRemoveDuplicates(List<Integer> input, List<Integer> expected) {
        List<Integer> result = removeDuplicates(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideRemoveDuplicates() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 2, 2, 3), List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4)),
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(5, 5, 5, 5), List.of(5))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDoubleValuePairs")
    void testHasDoubleValuePair(List<Integer> input, boolean expected) {
        boolean result = hasDoubleValuePair(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideDoubleValuePairs() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 6), true),
                Arguments.of(List.of(0, 0), true),
                Arguments.of(List.of(1, 3, 5), false),
                Arguments.of(List.of(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDifferencePairs")
    void testCountPairsWithDifference(List<Integer> nums, int k, int expected) {
        int result = countPairsWithDifference(nums, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideDifferencePairs() {
        return Stream.of(
                Arguments.of(List.of(1, 7, 5, 9, 2, 12, 3), 2, 4),
                Arguments.of(List.of(1, 1, 1, 1), 0, 6),
                Arguments.of(List.of(), 1, 0)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'civic', true",
            "'ivicc', true",
            "'hello', false",
            "'', true",
            "'aabb', true"
    })
    void testCanFormPalindrome(String input, boolean expected) {
        boolean result = canFormPalindrome(input);
        assertEquals(expected, result);
    }

    static List<Object[]> dataProvider1() {
        return List.of(
                new Object[]{new int[]{1, 2, 4, 6, 10}, 8, new int[]{1, 3}},
                new Object[]{new int[]{1, 3, 5, 7, 9}, 10, new int[]{1, 3}},
                new Object[]{new int[]{2, 3, 4}, 6, new int[]{0, 2}},
                new Object[]{new int[]{-3, -1, 1, 3, 4, 5}, 1, new int[]{0, 4}}
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider1")
    void testTwoSum(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, twoSum(nums, target));
    }

    static List<Object[]> dataProvider2() {
        return List.of(
                new Object[]{
                        new int[]{-1, 0, 1, 2, -1, -4},
                        new ArrayList<>(List.of(
                                List.of(-1, -1, 2),
                                List.of(-1, 0, 1)
                        ))
                },
                new Object[]{
                        new int[]{0, 1, 1},
                        new ArrayList<>()
                },
                new Object[]{
                        new int[]{0, 0, 0},
                        new ArrayList<>(List.of(List.of(0, 0, 0)))
                }
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider2")
    void testThreeSum(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> actual = threeSum(nums);
        // sortujemy wyniki by porównanie było deterministyczne
        Comparator<List<Integer>> comp = Comparator.comparing((List<Integer> l) -> l.get(0))
                .thenComparing(l -> l.get(1))
                .thenComparing(l -> l.get(2));
        actual.sort(comp);
        expected.sort(comp);
        assertEquals(expected, actual);
    }

    static List<Object[]> dataProvider() {
        return List.of(
                new Object[]{7, new int[]{2, 3, 1, 2, 4, 3}, 2},
                new Object[]{4, new int[]{1, 4, 4}, 1},
                new Object[]{11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0},
                new Object[]{15, new int[]{1, 2, 3, 4, 5}, 5},
                new Object[]{11, new int[]{1, 2, 3, 4, 5}, 3}
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testMinSubArrayLen(int target, int[] nums, int expected) {
        assertEquals(expected, minSubArrayLen(target, nums));
    }
}
