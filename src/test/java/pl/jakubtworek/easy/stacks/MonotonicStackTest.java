package pl.jakubtworek.easy.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.stacks.MonotonicStack.findNextGreaterToRight;

class MonotonicStackTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testNextLargestNumberToTheRights(List<Integer> input, List<Integer> expected) {
        assertEquals(expected, findNextGreaterToRight(input));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(2, 1, 2, 4, 3), List.of(4, 2, 4, -1, -1)),
                Arguments.of(List.of(1, 2, 3, 4), List.of(2, 3, 4, -1)),
                Arguments.of(List.of(4, 3, 2, 1), List.of(-1, -1, -1, -1)),
                Arguments.of(List.of(1, 1, 1), List.of(-1, -1, -1)),
                Arguments.of(List.of(10), List.of(-1)),
                Arguments.of(List.of(), List.of())
        );
    }

}
