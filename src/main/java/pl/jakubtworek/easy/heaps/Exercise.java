package pl.jakubtworek.easy.heaps;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.*;

class Exercise {
    /**
     * Znajdź k największych elementów w tablicy liczb całkowitych i zwróć je posortowane malejąco.
     * Wykorzystaj min-heap o rozmiarze k.
     * <p>
     * Przykład:
     * Input: [4, 1, 7, 3, 8, 5], k = 3 → Output: [8, 7, 5]
     */
    static List<Integer> findTopKLargestElements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        List<Integer> result = new ArrayList<>();
        int size = pq.size();
        for (int i = 0; i < nums.length; i++) {
            if (size - k < i + 1) {
                result.add(pq.poll());
            } else {
                pq.poll();
            }
        }

        return result.reversed();
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
        int left = 0;
        int right = k - 1;
        List<Double> result = new ArrayList<>();


        while (right < nums.length) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = left; i <= right; i++) {
                pq.add(nums[i]);
            }

            for (int i = left; i <= right; i++) {
                if (i - left == k/2 && k % 2 == 1) {
                    result.add(Double.valueOf(pq.poll()));
                } else if (i - left == k/2 - 1 && k % 2 == 0) {
                    double tmp = (Double.valueOf(pq.poll()) + Double.valueOf(pq.poll())) / 2.0;
                    result.add(tmp);
                } else {
                    pq.poll();
                }
            }

            left++;
            right++;
        }

        return result;
    }

    /**
     * Dla listy k posortowanych list jednokierunkowych, połącz je w jedną posortowaną listę.
     * Użyj kopca do efektywnego wybierania najmniejszego elementu.
     *
     * Złożoność: O(n log k), gdzie n — łączna liczba elementów, k — liczba list
     */
    static SinglyLinkedList<Integer> mergeKSortedLinkedLists(List<SinglyLinkedList<Integer>> lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (SinglyLinkedList<Integer> list : lists) {
            SinglyLinkedList.Node<Integer> head = list.head;
            if (head != null) {
                pq.add(head.value);
                while (head.next != null) {
                    SinglyLinkedList.Node<Integer> current = head.next;
                    head.next = current.next;
                    pq.add(current.value);
                }
            }
        }

        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();

        while (!pq.isEmpty()) {
            result.append(pq.poll());
        }

        return result;
    }

    /*
     * Zaimplementuj metodę, która przyjmuje tablicę liczb całkowitych oraz liczbę k,
     * i zwraca k największych elementów w tablicy (niekoniecznie posortowanych).
     * Rozwiązanie powinno działać w czasie O(n log k) i wykorzystywać kopiec (heap).
     *
     * Przykład:
     * Input: nums = [3, 2, 1, 5, 6, 4], k = 2
     * Output: [5, 6] (kolejność nie ma znaczenia)
     *
     * Wskazówka: użyj kopca minimalnego rozmiaru k.
     */
    static List<Integer> findKLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            q.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(q.poll());
        }

        return result;
    }

    /*
     * Dany jest zbiór k posortowanych strumieni liczb całkowitych (każdy jako lista),
     * oraz liczba całkowita n.
     * Zaimplementuj metodę, która zwraca pierwsze n najmniejszych liczb,
     * jakie powstałyby po połączeniu tych strumieni (czyli wynik też ma być posortowany).
     *
     * Oczekiwana złożoność: O(n log k)
     *
     * Przykład:
     * Input:
     *   streams = [
     *     [1, 4, 7],
     *     [2, 5, 8],
     *     [0, 3, 6, 9]
     *   ]
     *   n = 5
     * Output: [0, 1, 2, 3, 4]
     *
     * Wskazówka:
     * Użyj kopca, który przechowuje aktualną najmniejszą wartość z każdego strumienia,
     * oraz indeksy pozwalające pobrać kolejny element z tego strumienia.
     */
    static List<Integer> mergeKStreams(List<List<Integer>> streams, int n) {
        Queue<Integer> q = new PriorityQueue<>();

        for (List<Integer> stream : streams) {
            q.addAll(stream);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(q.poll());
        }

        return result;
    }
}
