package pl.jakubtworek.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.stacks.MonotonicStack.nextLargestNumberToTheRights;

public class MonotonicStackTest {
    @ParameterizedTest
    @MethodSource("provideData")
    void testNextLargestNumberToTheRights(List<Integer> input, List<Integer> expected) {
        List<Integer> result = nextLargestNumberToTheRights(input);
        assertEquals(expected, result);
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(2, 1, 2, 4, 3), List.of(4, 2, 4, -1, -1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3, 4), List.of(2, 3, 4, -1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(4, 3, 2, 1), List.of(-1, -1, -1, -1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 1, 1), List.of(-1, -1, -1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(10), List.of(-1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(), List.of()
                )
        );
    }
}
