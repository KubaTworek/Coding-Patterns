package pl.jakubtworek.easy.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.stacks.Deque.findSlidingWindowMaximums;

class DequeTest {

    @ParameterizedTest
    @MethodSource("provideSlidingWindowData")
    void testMaximumsOfSlidingWindow(List<Integer> nums, int k, List<Integer> expected) {
        List<Integer> result = findSlidingWindowMaximums(nums, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideSlidingWindowData() {
        return Stream.of(
                Arguments.of(List.of(1, 3, -1, -3, 5, 3, 6, 7), 3, List.of(3, 3, 5, 5, 6, 7)),
                Arguments.of(List.of(9, 11), 2, List.of(11)),
                Arguments.of(List.of(4, 2, 12, 3, 8), 2, List.of(4, 12, 12, 8)),
                Arguments.of(List.of(1, 1, 1, 1), 2, List.of(1, 1, 1)),
                Arguments.of(List.of(10), 1, List.of(10))
        );
    }
}
