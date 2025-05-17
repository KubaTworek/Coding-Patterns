package pl.jakubtworek.easy.linked_list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.linked_list.Restructuring.*;

class RestructuringTest {

    @ParameterizedTest(name = "Reversal of {0} should be {1}")
    @MethodSource("reversalCases")
    void testReverse(List<Integer> input, List<Integer> expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);

        reverse(list);

        assertEquals(expected, list.toList());
    }

    private static Stream<Arguments> reversalCases() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(42), List.of(42)),
                Arguments.of(List.of(1, 2, 3), List.of(3, 2, 1)),
                Arguments.of(List.of(7, 7, 7), List.of(7, 7, 7)),
                Arguments.of(List.of(5, 10, 15, 20), List.of(20, 15, 10, 5))
        );
    }

    @ParameterizedTest(name = "After removing {1}th node from end of {0}, result should be {2}")
    @MethodSource("removalCases")
    void testRemoveKthFromEnd(List<Integer> input, int k, List<Integer> expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);

        removeKthFromEnd(list, k);

        assertEquals(expected, list.toList());
    }

    private static Stream<Arguments> removalCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), 2, List.of(1, 2, 3, 5)),
                Arguments.of(List.of(10, 20, 30, 40), 1, List.of(10, 20, 30)),
                Arguments.of(List.of(1, 2), 2, List.of(2)),
                Arguments.of(List.of(100), 1, List.of()),
                Arguments.of(List.of(1, 2, 3), 4, List.of(1, 2, 3)) // k > length
        );
    }}
