package pl.jakubtworek.linked_list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.linked_list.Restructuring.linkedListReversal;
import static pl.jakubtworek.linked_list.Restructuring.removeKthNode;

public class RestructuringTest {

    @ParameterizedTest
    @MethodSource("provideListsForReversal")
    void testLinkedListReversal(List<Integer> input, List<Integer> expectedReversed) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);

        linkedListReversal(list);
        assertEquals(expectedReversed, list.toList());
    }

    private static Stream<Arguments> provideListsForReversal() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(), List.of()
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(42), List.of(42)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3), List.of(3, 2, 1)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(7, 7, 7), List.of(7, 7, 7)
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(5, 10, 15, 20), List.of(20, 15, 10, 5)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideListsAndK")
    void testRemoveKthNode(List<Integer> input, int k, List<Integer> expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);

        removeKthNode(list, k);
        assertEquals(expected, list.toList());
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideListsAndK() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3, 4, 5), 2, List.of(1, 2, 3, 5) // usuwa 4
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(10, 20, 30, 40), 1, List.of(10, 20, 30) // usuwa ostatni
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2), 2, List.of(2) // usuwa pierwszy
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(100), 1, List.of() // usuwa jedyny element
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3), 4, List.of(1, 2, 3) // k > długość — brak zmian
                )
        );
    }
}
