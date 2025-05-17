package pl.jakubtworek.easy.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.two_pointers.InwardTraversal.*;

class InwardTraversalTest {

    @ParameterizedTest
    @MethodSource("pairSumTestCases")
    void testFindPairWithTargetSum(List<Integer> nums, int target, List<Integer> expected) {
        assertEquals(expected, findPairWithTargetSum(nums, target));
    }

    private static Stream<Arguments> pairSumTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 4, 6, 10), 8, List.of(2, 6)),
                Arguments.of(List.of(1, 3, 5, 7), 10, List.of(3, 7)),
                Arguments.of(List.of(1, 2, 3, 9), 8, List.of()),
                Arguments.of(List.of(2, 2, 2, 2), 4, List.of(2, 2)),
                Arguments.of(List.of(), 5, List.of()),
                Arguments.of(List.of(1), 1, List.of()),
                Arguments.of(List.of(-3, -1, 0, 2, 4, 6), 3, List.of(-3, 6))
        );
    }

    @ParameterizedTest
    @MethodSource("tripletSumTestCases")
    void testFindTripletsWithTargetSum(List<Integer> nums, int target, List<List<Integer>> expected) {
        List<List<Integer>> result = new ArrayList<>(findTripletsWithTargetSum(nums, target));

        Comparator<List<Integer>> tripletComparator = Comparator
                .<List<Integer>, Integer>comparing(t -> t.get(0))
                .thenComparing(t -> t.get(1))
                .thenComparing(t -> t.get(2));

        result.sort(tripletComparator);
        expected = new ArrayList<>(expected);
        expected.sort(tripletComparator);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> tripletSumTestCases() {
        return Stream.of(
                Arguments.of(List.of(-1, 0, 1, 2, -1, -4), 0, List.of(
                        List.of(-1, -1, 2),
                        List.of(-1, 0, 1)
                )),
                Arguments.of(List.of(0, 0, 0, 0), 0, List.of(
                        List.of(0, 0, 0)
                )),
                Arguments.of(List.of(1, 2, 3, 4, 5), 9, List.of(
                        List.of(1, 3, 5),
                        List.of(2, 3, 4)
                )),
                Arguments.of(List.of(), 0, List.of()),
                Arguments.of(List.of(-2, 0, 1, 1, 2), 0, List.of(
                        List.of(-2, 0, 2),
                        List.of(-2, 1, 1)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("palindromeTestCases")
    void testIsPalindromeIgnoringNonAlphanumeric(String input, boolean expected) {
        assertEquals(expected, isPalindromeIgnoringNonAlphanumeric(input));
    }

    private static Stream<Arguments> palindromeTestCases() {
        return Stream.of(
                Arguments.of("A man, a plan, a canal: Panama", true),
                Arguments.of("race a car", false),
                Arguments.of("", true),
                Arguments.of(" ", true),
                Arguments.of("0P", false),
                Arguments.of("madam", true),
                Arguments.of("12321", true),
                Arguments.of("No 'x' in Nixon", true),
                Arguments.of("ab@a", true)
        );
    }

    @ParameterizedTest
    @MethodSource("containerTestCases")
    void testMaxWaterContainer(List<Integer> heights, int expectedArea) {
        assertEquals(expectedArea, maxWaterContainer(heights));
    }

    private static Stream<Arguments> containerTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 8, 6, 2, 5, 4, 8, 3, 7), 49),
                Arguments.of(List.of(1, 1), 1),
                Arguments.of(List.of(4, 3, 2, 1, 4), 16),
                Arguments.of(List.of(1, 2, 1), 2),
                Arguments.of(List.of(2, 3, 10, 5, 7, 8, 9), 36),
                Arguments.of(List.of(1, 3, 2, 5, 25, 24, 5), 24)
        );
    }
}
