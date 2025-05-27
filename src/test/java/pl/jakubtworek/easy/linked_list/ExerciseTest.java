package pl.jakubtworek.easy.linked_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubtworek.easy.linked_list.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideRemoveDuplicatesCases")
    void testRemoveDuplicatesFromSortedList(List<Integer> input, List<Integer> expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);
        var result = removeDuplicatesFromSortedList(list);
        assertEquals(expected, result.toList());
    }

    private static Stream<Arguments> provideRemoveDuplicatesCases() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 2, 3, 3), List.of(1, 2, 3)),
                Arguments.of(List.of(1, 1, 1), List.of(1)),
                Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3)),
                Arguments.of(List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideRotationCases")
    void testRotateRight(List<Integer> input, int k, List<Integer> expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        input.forEach(list::append);
        var result = rotateRight(list, k);
        assertEquals(expected, result.toList());
    }

    private static Stream<Arguments> provideRotationCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), 2, List.of(4, 5, 1, 2, 3)),
                Arguments.of(List.of(0, 1, 2), 4, List.of(2, 0, 1)),
                Arguments.of(List.of(1), 1, List.of(1))
        );
    }

    @Test
    void testHasCycle() {
        var list = new SinglyLinkedList<Integer>();
        var node1 = new SinglyLinkedList.Node<>(1);
        var node2 = new SinglyLinkedList.Node<>(2);
        var node3 = new SinglyLinkedList.Node<>(3);
        var node4 = new SinglyLinkedList.Node<>(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // cykl do node2

        list.head = node1;
        assertTrue(hasCycle(list));
    }

    @ParameterizedTest
    @MethodSource("provideEqualityCases")
    void testAreIdentical(List<Integer> list1, List<Integer> list2, boolean expected) {
        SinglyLinkedList<Integer> a = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> b = new SinglyLinkedList<>();
        list1.forEach(a::append);
        list2.forEach(b::append);
        assertEquals(expected, areIdentical(a, b));
    }

    private static Stream<Arguments> provideEqualityCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3), true),
                Arguments.of(List.of(1, 2), List.of(2, 1), false),
                Arguments.of(List.of(), List.of(), true),
                Arguments.of(List.of(1, 2), List.of(1, 2, 3), false)
        );
    }
}
