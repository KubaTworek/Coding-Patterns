package pl.jakubtworek.easy.linked_list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static pl.jakubtworek.easy.linked_list.Traversal.*;

class TraversalTest {

    @ParameterizedTest(name = "[{index}] powinno zwrócić wspólny węzeł: {2}")
    @MethodSource("provideIntersectingLists")
    void testFindIntersectionNode(
            SinglyLinkedList<Integer> listA,
            SinglyLinkedList<Integer> listB,
            SinglyLinkedList.Node<Integer> expectedNode
    ) {
        var result = findIntersectionNode(listA, listB);
        assertSame(expectedNode, result); // porównujemy referencje
    }

    private static Stream<Arguments> provideIntersectingLists() {
        // ✅ Przecięcie w środku
        var common = new SinglyLinkedList.Node<>(7);
        common.next = new SinglyLinkedList.Node<>(8);
        common.next.next = new SinglyLinkedList.Node<>(9);

        var listA1 = new SinglyLinkedList<Integer>();
        listA1.head = new SinglyLinkedList.Node<>(1);
        listA1.head.next = new SinglyLinkedList.Node<>(2);
        listA1.head.next.next = common;

        var listB1 = new SinglyLinkedList<Integer>();
        listB1.head = new SinglyLinkedList.Node<>(3);
        listB1.head.next = common;

        // ❌ Brak przecięcia
        var listA2 = new SinglyLinkedList<Integer>();
        listA2.head = new SinglyLinkedList.Node<>(1);
        listA2.head.next = new SinglyLinkedList.Node<>(2);

        var listB2 = new SinglyLinkedList<Integer>();
        listB2.head = new SinglyLinkedList.Node<>(3);
        listB2.head.next = new SinglyLinkedList.Node<>(4);

        // ✅ Całkowite przecięcie (ta sama lista)
        var shared = new SinglyLinkedList<Integer>();
        shared.head = new SinglyLinkedList.Node<>(10);
        shared.head.next = new SinglyLinkedList.Node<>(20);

        return Stream.of(
                Arguments.of(listA1, listB1, common),
                Arguments.of(listA2, listB2, null),
                Arguments.of(shared, shared, shared.head)
        );
    }

    @ParameterizedTest
    @MethodSource("provideListsForPalindromeCheck")
    void testPalindromicLinkedList(List<Integer> values, boolean expected) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        values.forEach(list::append);

        boolean result = isLinkedListPalindrome(list);
        assertEquals(expected, result);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> provideListsForPalindromeCheck() {
        return Stream.of(
                // ✅ Pusta lista — palindrom
                Arguments.of(List.of(), true),

                // ✅ Jeden element — palindrom
                Arguments.of(List.of(1), true),

                // ✅ Parzysta liczba elementów — palindrom
                Arguments.of(List.of(1, 2, 2, 1), true),

                // ✅ Nieparzysta liczba elementów — palindrom
                Arguments.of(List.of(1, 2, 3, 2, 1), true),

                // ❌ Parzysta — niepalindrom
                Arguments.of(List.of(1, 2, 3, 4), false),

                // ❌ Nieparzysta — niepalindrom
                Arguments.of(List.of(1, 2, 3, 4, 5), false)
        );
    }
}
