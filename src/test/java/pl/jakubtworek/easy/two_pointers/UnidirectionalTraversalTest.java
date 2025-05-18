package pl.jakubtworek.easy.two_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.two_pointers.UnidirectionalTraversal.moveZerosToEnd;

class UnidirectionalTraversalTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testShiftZerosToEnd(List<Integer> input, List<Integer> expected) {
        List<Integer> nums; // kopiujemy, by nie zmieniać oryginału
        nums = new ArrayList<>(input);
        moveZerosToEnd(nums);
        assertEquals(expected, nums);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 0, 3, 12), List.of(1, 3, 12, 0, 0)),
                Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4)),
                Arguments.of(List.of(0, 0, 0), List.of(0, 0, 0)),
                Arguments.of(List.of(4, 0, 5, 0, 6), List.of(4, 5, 6, 0, 0)),
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(0, 1), List.of(1, 0)),
                Arguments.of(List.of(1, 0), List.of(1, 0)),
                Arguments.of(List.of(0, 0, 1), List.of(1, 0, 0)),
                Arguments.of(List.of(1, 0, 2, 0, 3), List.of(1, 2, 3, 0, 0))
        );
    }
}
