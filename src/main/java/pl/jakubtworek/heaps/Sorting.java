package pl.jakubtworek.heaps;

import pl.jakubtworek.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sorting {

    /**
    Algorytm combineSortedLinkedList:

    Zadanie:
    Łączy wiele posortowanych list jednokierunkowych (`SinglyLinkedList<Integer>`) w jedną listę wynikową,
    która również jest posortowana rosnąco.

    Działanie:
    1. Tworzymy kolejkę priorytetową (`PriorityQueue`) typu min-heap, która sortuje liczby w porządku rosnącym.
    2. Iterujemy przez każdą z wejściowych list:
       - Dla każdej listy przechodzimy przez wszystkie jej węzły (`Node`).
       - Każdą wartość dodajemy do kolejki.
    3. Po zebraniu wszystkich wartości z list budujemy nową listę wynikową:
       - Tworzymy `dummy` węzeł pomocniczy do łatwego budowania nowej listy.
       - Wyjmujemy elementy z kolejki i tworzymy nowe węzły `Node`, które dołączamy do listy wynikowej.
    4. Ustawiamy `result.head` na początek nowej listy i ją zwracamy.

    Złożoność:
    - Czasowa: O(n log n)
      - n — łączna liczba wszystkich węzłów w wejściowych listach
      - log n — operacje na kolejce (PriorityQueue) przy dodawaniu i usuwaniu
    - Pamięciowa: O(n)
      - kolejka przechowuje wszystkie liczby (kopia danych)
      - nowa lista również zajmuje O(n)

    Uwagi:
    - Algorytm działa poprawnie niezależnie od liczby list i ich długości.
    - Zakłada, że wszystkie wejściowe listy zawierają dane typu Integer.
    - W odróżnieniu od klasycznego algorytmu "merge k sorted lists" (który używa kopca z węzłami), ta wersja najpierw pobiera **wszystkie wartości**, a dopiero potem je sortuje — przez co jest prostsza, ale mniej optymalna dla bardzo dużych strumieni danych.

    Możliwe ulepszenia:
    - Zamiast zbierać wszystkie wartości do `PriorityQueue`, można użyć wersji z kopcem węzłów i utrzymywać k-wskazaniowy merge (lepsze O(n log k)).
    */
    static SinglyLinkedList<Integer> combineSortedLinkedList(List<SinglyLinkedList<Integer>> list) {
        Queue<Integer> queue = new PriorityQueue<>();

        for (SinglyLinkedList<Integer> singlyLinkedList : list) {
            SinglyLinkedList.Node<Integer> node = singlyLinkedList.head;
            while (node != null) {
                queue.add(node.value);
                node = node.next;
            }
        }

        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        SinglyLinkedList.Node<Integer> dummy = new SinglyLinkedList.Node<>(0);
        SinglyLinkedList.Node<Integer> curr = dummy;

        while (!queue.isEmpty()) {
            curr.next = new SinglyLinkedList.Node<>(queue.poll());
            curr = curr.next;
        }

        result.head = dummy.next;
        return result;
    }
}
