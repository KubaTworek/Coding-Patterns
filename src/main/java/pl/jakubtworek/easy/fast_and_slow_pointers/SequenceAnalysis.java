package pl.jakubtworek.easy.fast_and_slow_pointers;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

class SequenceAnalysis {

    /**
       Algorytm findMiddleNode (tzw. "fast & slow pointer"):

       Zadanie:
       Znajduje środkowy węzeł w jednokierunkowej liście połączonej (singly linked list).
       Dla listy o parzystej liczbie elementów zwraca drugi ze środkowych węzłów.

       Przykład:
       Dla listy 1 → 2 → 3 → 4 → 5 → wynik to 3.
       Dla listy 1 → 2 → 3 → 4 → wynik to 3.

       Działanie:
       - Używamy dwóch wskaźników:
           • `slow` przesuwa się o 1 element na iterację,
           • `fast` przesuwa się o 2 elementy na iterację.
       - Gdy `fast` dotrze na koniec listy, `slow` znajduje się w środku.

       Złożoność:
       - Czasowa: O(n)
         • Każdy węzeł jest odwiedzany najwyżej raz przez `slow` i `fast`.
         • Pełna długość listy jest przechodzona jedną pętlą.
       - Pamięciowa: O(1)
         • Używane są tylko dwa wskaźniki niezależnie od długości listy.
     */
    static SinglyLinkedList.Node<Integer> findMiddleNode(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node<Integer> slow = linkedList.head;
        SinglyLinkedList.Node<Integer> fast = linkedList.head;

        // Fast przesuwa się dwa razy szybciej niż slow
        while (fast != null && fast.next != null) {
            slow = slow.next;         // 1 krok
            fast = fast.next.next;    // 2 kroki
        }

        // Gdy fast dojdzie do końca, slow jest na środku
        return slow;
    }
}
