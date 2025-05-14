package pl.jakubtworek.linked_list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static pl.jakubtworek.linked_list.Traversal.intersection;

public class TraversalTest {

    @ParameterizedTest
    @MethodSource("provideLinkedLists")
    void testFindIntersection(
            SinglyLinkedList<Integer> listA,
            SinglyLinkedList<Integer> listB,
            SinglyLinkedList.Node<Integer> expectedIntersection) {

        var result = intersection(listA, listB);

        // Używamy assertSame — porównanie referencji (czy to ten sam obiekt)
        assertSame(expectedIntersection, result);
    }

    private static Stream<Arguments> provideLinkedLists() {
        return Stream.of(
                // ✅ Przecięcie w środku
                (Supplier<Arguments>) () -> {
                    var common = new SinglyLinkedList.Node<>(7);
                    common.next = new SinglyLinkedList.Node<>(8);
                    common.next.next = new SinglyLinkedList.Node<>(9);

                    var listA = new SinglyLinkedList<Integer>();
                    listA.head = new SinglyLinkedList.Node<>(1);
                    listA.head.next = new SinglyLinkedList.Node<>(2);
                    listA.head.next.next = common;

                    var listB = new SinglyLinkedList<Integer>();
                    listB.head = new SinglyLinkedList.Node<>(3);
                    listB.head.next = common;

                    return Arguments.of(listA, listB, common);
                },

                // ❌ Brak przecięcia
                (Supplier<Arguments>) () -> {
                    var listA = new SinglyLinkedList<Integer>();
                    listA.head = new SinglyLinkedList.Node<>(1);
                    listA.head.next = new SinglyLinkedList.Node<>(2);

                    var listB = new SinglyLinkedList<Integer>();
                    listB.head = new SinglyLinkedList.Node<>(3);
                    listB.head.next = new SinglyLinkedList.Node<>(4);

                    return Arguments.of(listA, listB, null);
                },

                // ✅ Ta sama lista (pełne przecięcie)
                (Supplier<Arguments>) () -> {
                    var shared = new SinglyLinkedList<Integer>();
                    shared.head = new SinglyLinkedList.Node<>(10);
                    shared.head.next = new SinglyLinkedList.Node<>(20);

                    return Arguments.of(shared, shared, shared.head);
                }
        ).map(Supplier::get);
    }
}
