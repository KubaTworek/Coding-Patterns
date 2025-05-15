package pl.jakubtworek.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.linked_list.SinglyLinkedList;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.fast_and_slow_pointers.CycleDetection.isCycleDetected;

public class CycleDetectionTest {
    @ParameterizedTest
    @MethodSource("provideLists")
    void testIsCycleDetected(SinglyLinkedList<Integer> list, boolean expected) {
        boolean result = isCycleDetected(list);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideLists() {
        return Stream.of(
                // ✅ Brak cyklu — standardowa lista
                (Supplier<Arguments>) () -> {
                    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
                    list.append(1);
                    list.append(2);
                    list.append(3);
                    return org.junit.jupiter.params.provider.Arguments.of(list, false);
                },

                // ✅ Z cyklem: 3 → 4 → 5 → 3
                (Supplier<org.junit.jupiter.params.provider.Arguments>) () -> {
                    var list = new SinglyLinkedList<Integer>();
                    var node1 = new SinglyLinkedList.Node<>(3);
                    var node2 = new SinglyLinkedList.Node<>(4);
                    var node3 = new SinglyLinkedList.Node<>(5);

                    list.head = node1;
                    node1.next = node2;
                    node2.next = node3;
                    node3.next = node1; // cykl tutaj

                    return org.junit.jupiter.params.provider.Arguments.of(list, true);
                },

                // ✅ Pusta lista
                (Supplier<org.junit.jupiter.params.provider.Arguments>) () -> {
                    var list = new SinglyLinkedList<Integer>();
                    return org.junit.jupiter.params.provider.Arguments.of(list, false);
                },

                // ✅ Jeden element, bez cyklu
                (Supplier<org.junit.jupiter.params.provider.Arguments>) () -> {
                    var list = new SinglyLinkedList<Integer>();
                    list.head = new SinglyLinkedList.Node<>(99);
                    return org.junit.jupiter.params.provider.Arguments.of(list, false);
                },

                // ✅ Jeden element, z cyklem do siebie
                (Supplier<org.junit.jupiter.params.provider.Arguments>) () -> {
                    var node = new SinglyLinkedList.Node<>(42);
                    node.next = node; // cykl do siebie

                    var list = new SinglyLinkedList<Integer>();
                    list.head = node;

                    return org.junit.jupiter.params.provider.Arguments.of(list, true);
                }
        ).map(Supplier::get);
    }

}
