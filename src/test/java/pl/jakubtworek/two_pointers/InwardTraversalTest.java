package pl.jakubtworek.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.two_pointers.InwardTraversal.*;

class InwardTraversalTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testPairSumSorted(List<Integer> nums, int target, List<Integer> expected) {
        List<Integer> result = pairSumSorted(nums, target);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestCases() {
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
    @MethodSource("provideTripletTestCases")
    void testTripletSum(List<Integer> nums, int target, List<List<Integer>> expected) {
        List<List<Integer>> actual = tripletSum(nums, target);

        // sortujemy zarówno wynik jak i oczekiwaną listę, żeby porównywać niezależnie od kolejności
        Comparator<List<Integer>> tripletComparator = Comparator
                .<List<Integer>, Integer>comparing(t -> t.get(0))
                .thenComparing(t -> t.get(1))
                .thenComparing(t -> t.get(2));

        actual = new ArrayList<>(actual);
        actual.sort(tripletComparator);

        expected = new ArrayList<>(expected);
        expected.sort(tripletComparator);

        assertEquals(expected, actual);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideTripletTestCases() {
        return Stream.of(
                Arguments.of(
                        List.of(-1, 0, 1, 2, -1, -4), 0,
                        List.of(
                                List.of(-1, -1, 2),
                                List.of(-1, 0, 1)
                        )
                ),
                Arguments.of(
                        List.of(0, 0, 0, 0), 0,
                        List.of(
                                List.of(0, 0, 0)
                        )
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5), 9,
                        List.of(
                                List.of(1, 3, 5),
                                List.of(2, 3, 4)
                        )
                ),
                Arguments.of(
                        List.of(), 0,
                        List.of()
                ),
                Arguments.of(
                        List.of(-2, 0, 1, 1, 2), 0,
                        List.of(
                                List.of(-2, 0, 2),
                                List.of(-2, 1, 1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providePalindromeTestCases")
    void testIsPalindromeValid(String input, boolean expected) {
        boolean result = isPalindromeValid(input);
        assertEquals(expected, result);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> providePalindromeTestCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("A man, a plan, a canal: Panama", true),
                org.junit.jupiter.params.provider.Arguments.of("race a car", false),
                org.junit.jupiter.params.provider.Arguments.of("", true), // pusty ciąg to palindrom
                org.junit.jupiter.params.provider.Arguments.of(" ", true), // spacja = palindrom
                org.junit.jupiter.params.provider.Arguments.of("0P", false),
                org.junit.jupiter.params.provider.Arguments.of("madam", true),
                org.junit.jupiter.params.provider.Arguments.of("12321", true),
                org.junit.jupiter.params.provider.Arguments.of("No 'x' in Nixon", true),
                org.junit.jupiter.params.provider.Arguments.of("ab@a", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideContainerTestCases")
    void testLargestContainer(List<Integer> height, int expectedArea) {
        int result = largestContainer(height);
        assertEquals(expectedArea, result);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideContainerTestCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 8, 6, 2, 5, 4, 8, 3, 7), 49
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 1), 1
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(4, 3, 2, 1, 4), 16
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 1), 2
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(2, 3, 10, 5, 7, 8, 9), 36
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 3, 2, 5, 25, 24, 5), 24
                )
        );
    }
}
