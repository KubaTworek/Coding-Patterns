package pl.jakubtworek.easy.heaps;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.heaps.Sorting.mergeSortedLists;
import static pl.jakubtworek.easy.heaps.Sorting.sortNearlySortedArray;

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

    @ParameterizedTest
    @MethodSource("provideKSortedArrays")
    void testSortNearlySortedArray(List<Integer> input, int k, List<Integer> expected) {
        List<Integer> result = sortNearlySortedArray(input, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideKSortedArrays() {
        return Stream.of(
                Arguments.of(List.of(5, 1, 9, 4, 7, 10), 2, List.of(1, 4, 5, 7, 9, 10)),
                Arguments.of(List.of(3, 2, 1), 2, List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4), 1, List.of(1, 2, 3, 4)),
                Arguments.of(List.of(10, 9, 8, 7, 4), 4, List.of(4, 7, 8, 9, 10)),
                Arguments.of(List.of(4, 3, 2, 1), 3, List.of(1, 2, 3, 4)),
                Arguments.of(List.of(), 2, List.of()),
                Arguments.of(List.of(1), 1, List.of(1))
        );
    }
}
