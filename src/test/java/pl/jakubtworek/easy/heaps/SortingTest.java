package pl.jakubtworek.easy.heaps;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.heaps.Sorting.mergeSortedLists;

class SortingTest {

    @ParameterizedTest(name = "[{index}] merged → {1}")
    @MethodSource("provideSortedLinkedLists")
    void testMergeSortedLists(List<SinglyLinkedList<Integer>> input, List<Integer> expected) {
        SinglyLinkedList<Integer> result = mergeSortedLists(input);
        assertEquals(expected, result.toList());
    }

    private static Stream<Arguments> provideSortedLinkedLists() {
        return Stream.of(
                // ✅ 3 listy posortowane, standardowy przypadek
                Arguments.of(
                        List.of(
                                buildList(1, 3, 5),
                                buildList(2, 4),
                                buildList(6)
                        ),
                        List.of(1, 2, 3, 4, 5, 6)
                ),
                // ✅ pusta lista jako wejście
                Arguments.of(
                        List.of(),
                        List.of()
                ),
                // ✅ pojedyncza lista
                Arguments.of(
                        List.of(buildList(10, 20, 30)),
                        List.of(10, 20, 30)
                ),
                // ✅ lista z pustymi i niepustymi listami
                Arguments.of(
                        List.of(
                                buildList(),
                                buildList(5),
                                buildList()
                        ),
                        List.of(5)
                ),
                // ✅ duplikaty
                Arguments.of(
                        List.of(
                                buildList(1, 2, 2),
                                buildList(2, 3)
                        ),
                        List.of(1, 2, 2, 2, 3)
                )
        );
    }

    private static SinglyLinkedList<Integer> buildList(Integer... values) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (Integer val : values) {
            list.append(val);
        }
        return list;
    }
}
