package pl.jakubtworek.easy.heaps;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Sorting {

    /**
       Algorytm mergeSortedLists:

       Zadanie:
       Łączy wiele posortowanych list jednokierunkowych (`SinglyLinkedList<Integer>`) w jedną
       nową listę, która również jest posortowana rosnąco.

       Działanie:
       1. Tworzymy min-kopiec (PriorityQueue) do przechowywania wszystkich wartości z list.
       2. Iterujemy po każdej wejściowej liście i dodajemy wszystkie jej elementy do kopca.
       3. Tworzymy nową listę wynikową:
          - Używamy węzła "dummy", aby łatwo budować listę.
          - Wyciągamy elementy z kolejki (w kolejności rosnącej) i tworzymy nowe węzły.
       4. Zwracamy zbudowaną listę.

       Złożoność:
       - Czasowa: O(n log n)
         • n — łączna liczba węzłów we wszystkich listach
         • log n — dodawanie/usuwanie z kopca

       - Pamięciowa: O(n)
         • Kopiec przechowuje wszystkie liczby
         • Tworzymy nową listę z n węzłami
     */
    static SinglyLinkedList<Integer> mergeSortedLists(List<SinglyLinkedList<Integer>> sortedLists) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        // 1. Zbieramy wszystkie wartości z list do kopca
        for (SinglyLinkedList<Integer> list : sortedLists) {
            SinglyLinkedList.Node<Integer> node = list.head;
            while (node != null) {
                minHeap.offer(node.value);
                node = node.next;
            }
        }

        // 2. Tworzymy nową posortowaną listę
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        SinglyLinkedList.Node<Integer> dummy = new SinglyLinkedList.Node<>(0);
        SinglyLinkedList.Node<Integer> current = dummy;

        while (!minHeap.isEmpty()) {
            current.next = new SinglyLinkedList.Node<>(minHeap.poll());
            current = current.next;
        }

        result.head = dummy.next;
        return result;
    }


    /**
       Algorytm: sortNearlySortedArray

       Zadanie:
       Sortuje tablicę, w której każdy element znajduje się maksymalnie `k` pozycji
       od swojej docelowej pozycji w wersji posortowanej (tzw. "k-sorted array").

       Przykład:
       Input:  nums = [5, 1, 9, 4, 7, 10], k = 2
       Output: [1, 4, 5, 7, 9, 10]

       Działanie:
       - Wstawiamy pierwsze `k + 1` elementów do min-kopca (PriorityQueue).
       - Iterujemy po pozostałych elementach:
         • Usuwamy najmniejszy element z kopca i wstawiamy go na odpowiednie miejsce w tablicy.
         • Wrzucamy kolejny element z wejściowej tablicy do kopca.
       - Po przejściu wszystkich elementów, usuwamy pozostałe elementy z kopca i uzupełniamy tablicę.

       Złożoność:
       - Czasowa: O(n log k)
         • Budowa kopca: O(k)
         • Dla każdego z n elementów: dodanie do kopca + usunięcie minimum = O(log k)
       - Pamięciowa: O(k)
         • Min-kopiec przechowuje maksymalnie k+1 elementów
     */
    static List<Integer> sortNearlySortedArray(List<Integer> nums, int k) {
        if (nums == null || nums.size() <= 1 || k <= 0) return nums;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Integer> sorted = new ArrayList<>(nums.size());

        // Krok 1: Wypełnij kopiec pierwszymi k+1 elementami
        int i = 0;
        for (; i <= Math.min(k, nums.size() - 1); i++) {
            minHeap.add(nums.get(i));
        }

        // Krok 2: Przetwarzaj resztę elementów
        for (; i < nums.size(); i++) {
            sorted.add(minHeap.poll());
            minHeap.add(nums.get(i));
        }

        // Krok 3: Opróżnij kopiec
        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.poll());
        }

        return sorted;
    }
}
