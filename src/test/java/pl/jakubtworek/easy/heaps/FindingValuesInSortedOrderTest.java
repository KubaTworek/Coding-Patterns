package pl.jakubtworek.easy.heaps;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.heaps.FindingValuesInSortedOrder.findStringsWithAtLeastKOccurrences;

class FindingValuesInSortedOrderTest {


    @ParameterizedTest(name = "[{index}] input={0}, k={1} → expected={2}")
    @MethodSource("provideTestCases")
    void testFindStringsWithAtLeastKOccurrences(List<String> input, int k, List<String> expected) {
        List<String> result = findStringsWithAtLeastKOccurrences(input, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(
                        List.of("a", "b", "a", "c", "b", "a", "d"),
                        2,
                        List.of("a", "b")
                ),
                Arguments.of(
                        List.of("a", "b", "a", "b", "a"),
                        3,
                        List.of("a")
                ),
                Arguments.of(
                        List.of("x", "y", "z"),
                        2,
                        List.of()
                ),
                Arguments.of(
                        List.of("m", "m", "n", "n", "n", "o", "o", "o", "o"),
                        2,
                        List.of("o", "m", "n")
                )
        );
    }
}

