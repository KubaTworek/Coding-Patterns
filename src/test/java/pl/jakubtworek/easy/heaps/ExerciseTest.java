package pl.jakubtworek.easy.heaps;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.heaps.Exercise.*;

class ExerciseTest {
    @ParameterizedTest
    @MethodSource("topKData")
    void testFindTopKLargestElements(int[] nums, int k, List<Integer> expected) {
        List<Integer> result = findTopKLargestElements(nums, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> topKData() {
        return Stream.of(
                Arguments.of(new int[]{4, 1, 7, 3, 8, 5}, 3, List.of(8, 7, 5)),
                Arguments.of(new int[]{10, 20, 11, 70}, 2, List.of(70, 20)),
                Arguments.of(new int[]{1, 2}, 1, List.of(2)),
                Arguments.of(new int[]{3, 2, 1}, 3, List.of(3, 2, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("medianData")
    void testMedianSlidingWindow(int[] nums, int k, List<Double> expected) {
        List<Double> result = medianSlidingWindow(nums, k);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> medianData() {
        return Stream.of(
                Arguments.of(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, List.of(1.0, -1.0, -1.0, 3.0, 5.0, 6.0)),
                Arguments.of(new int[]{1, 2, 3, 4}, 2, List.of(1.5, 2.5, 3.5)),
                Arguments.of(new int[]{5, 2, 1, 4, 6}, 1, List.of(5.0, 2.0, 1.0, 4.0, 6.0))
        );
    }

    @ParameterizedTest
    @MethodSource("mergeKListsData")
    void testMergeKSortedLinkedLists(List<List<Integer>> rawLists, List<Integer> expected) {
        List<SinglyLinkedList<Integer>> input = rawLists.stream().map(list -> {
            SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
            list.forEach(sll::append);
            return sll;
        }).toList();

        SinglyLinkedList<Integer> result = mergeKSortedLinkedLists(input);
        assertEquals(expected, result.toList());
    }

    private static Stream<Arguments> mergeKListsData() {
        return Stream.of(
                Arguments.of(List.of(List.of(1, 4, 5), List.of(1, 3, 4), List.of(2, 6)), List.of(1, 1, 2, 3, 4, 4, 5, 6)),
                Arguments.of(List.of(List.of(2), List.of(), List.of(1, 3)), List.of(1, 2, 3)),
                Arguments.of(List.of(), List.of())
        );
    }
}
