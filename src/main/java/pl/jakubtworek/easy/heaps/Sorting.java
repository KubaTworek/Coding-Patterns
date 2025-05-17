package pl.jakubtworek.easy.heaps;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

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
}
