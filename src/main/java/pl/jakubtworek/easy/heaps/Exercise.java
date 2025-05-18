package pl.jakubtworek.easy.heaps;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;

class Exercise {
    /**
     * Znajdź k największych elementów w tablicy liczb całkowitych i zwróć je posortowane malejąco.
     * Wykorzystaj min-heap o rozmiarze k.
     * <p>
     * Przykład:
     * Input: [4, 1, 7, 3, 8, 5], k = 3 → Output: [8, 7, 5]
     */
    static List<Integer> findTopKLargestElements(int[] nums, int k) {
        return null;
    }

    /**
     * Dla tablicy nums i liczby k, zwróć mediany dla każdego przesuwającego się okna długości k.
     * <p>
     * Przykład:
     * Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
     * Output: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
     * <p>
     * Wymagane: użyj dwóch kopców: maxHeap dla lewej połowy i minHeap dla prawej.
     */
    static List<Double> medianSlidingWindow(int[] nums, int k) {
        return null;
    }

    /**
     * Dla listy k posortowanych list jednokierunkowych, połącz je w jedną posortowaną listę.
     * Użyj kopca do efektywnego wybierania najmniejszego elementu.
     *
     * Złożoność: O(n log k), gdzie n — łączna liczba elementów, k — liczba list
     */
    static SinglyLinkedList<Integer> mergeKSortedLinkedLists(List<SinglyLinkedList<Integer>> lists) {
        return null;
    }

}
