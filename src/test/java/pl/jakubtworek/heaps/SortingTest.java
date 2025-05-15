package pl.jakubtworek.heaps;

import org.junit.jupiter.api.Test;
import pl.jakubtworek.linked_list.SinglyLinkedList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.heaps.Sorting.combineSortedLinkedList;

public class SortingTest {

    @Test
    void testCombineSortedLinkedList() {
        // Lista 1: 1 → 3 → 5
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.append(1);
        list1.append(3);
        list1.append(5);

        // Lista 2: 2 → 4
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.append(2);
        list2.append(4);

        // Lista 3: 6
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
        list3.append(6);

        List<SinglyLinkedList<Integer>> input = List.of(list1, list2, list3);

        // Wywołanie
        SinglyLinkedList<Integer> result = combineSortedLinkedList(input);

        // Sprawdź
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        assertEquals(expected, result.toList());
    }

}
