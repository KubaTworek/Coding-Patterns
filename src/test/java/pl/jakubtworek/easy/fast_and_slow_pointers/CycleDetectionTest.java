package pl.jakubtworek.easy.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.fast_and_slow_pointers.CycleDetection.hasCycle;

class CycleDetectionTest {

    @ParameterizedTest(name = "[{index}] hasCycle={1} for list")
    @MethodSource("cycleTestData")
    void testHasCycle(SinglyLinkedList<Integer> list, boolean expected) {
        boolean result = hasCycle(list);
        assertEquals(expected, result);
    }

    static Stream<Arguments> cycleTestData() {
        return Stream.of(
                // Brak cyklu — zwykła lista
                Arguments.of(SinglyLinkedList.of(1, 2, 3), false),

                // Lista z cyklem: 3 → 4 → 5 → 3
                Arguments.of(createCycleList(), true),

                // Pusta lista
                Arguments.of(new SinglyLinkedList<>(), false),

                // Jeden element, bez cyklu
                Arguments.of(SinglyLinkedList.of(99), false),

                // Jeden element, z cyklem do siebie
                Arguments.of(createSelfLoopNode(42), true)
        );
    }

    // Pomocnicza metoda do stworzenia listy z cyklem: 3 → 4 → 5 → 3
    private static SinglyLinkedList<Integer> createCycleList() {
        var n1 = new SinglyLinkedList.Node<>(3);
        var n2 = new SinglyLinkedList.Node<>(4);
        var n3 = new SinglyLinkedList.Node<>(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        var list = new SinglyLinkedList<Integer>();
        list.head = n1;
        return list;
    }

    // Pomocnicza metoda: jeden węzeł wskazuje na siebie
    private static SinglyLinkedList<Integer> createSelfLoopNode(int val) {
        var node = new SinglyLinkedList.Node<>(val);
        node.next = node;

        var list = new SinglyLinkedList<Integer>();
        list.head = node;
        return list;
    }
}
