package pl.jakubtworek.fast_and_slow_pointers;

import pl.jakubtworek.linked_list.SinglyLinkedList;

public class SequenceAnalysis {
    /**
    Algorytm getMidpoint:

    Zadanie:
    Znajduje środkowy węzeł w jednokierunkowej liście połączonej (singly linked list).
    Dla listy o parzystej liczbie elementów zwraca drugi z dwóch środkowych.

    Działanie:
    - Używamy dwóch wskaźników: `slow` i `fast`.
    - `slow` porusza się o 1 krok, `fast` o 2 kroki.
    - Gdy `fast` dotrze do końca listy (lub go przekroczy), `slow` będzie dokładnie na środku.

    Złożoność:
    - Czasowa: O(n) — jedno przejście przez listę.
    - Pamięciowa: O(1) — stała liczba wskaźników.
    */
    static SinglyLinkedList.Node<Integer> getMidpoint(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node<Integer> fast = linkedList.head;
        SinglyLinkedList.Node<Integer> slow = linkedList.head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

}
