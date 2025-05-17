package pl.jakubtworek.easy.fast_and_slow_pointers;

import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

class CycleDetection {

    /**
       Algorytm hasCycle (znany również jako Floyd's Tortoise and Hare):

       Zadanie:
       Sprawdza, czy jednokierunkowa lista połączona (Singly Linked List) zawiera cykl —
       czyli czy istnieje węzeł, do którego można dojść z samego siebie, podążając za `.next`.

       Działanie:
       - Wykorzystujemy dwa wskaźniki:
           • `slow` porusza się o jeden węzeł na krok,
           • `fast` porusza się o dwa węzły na krok.
       - Jeśli istnieje cykl, `fast` dogoni `slow` i oba wskaźniki się spotkają.
       - Jeśli nie ma cyklu, `fast` dotrze do końca listy (`null`), a pętla się zakończy.

       Złożoność:
       - Czasowa: O(n)
         • Każdy wskaźnik przechodzi maksymalnie przez n elementów.
         • W najgorszym przypadku (bez cyklu), fast osiąga koniec w O(n/2) krokach.
       - Pamięciowa: O(1)
         • Używamy stałej liczby wskaźników bez dodatkowych struktur danych.
     */
    static boolean hasCycle(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node<Integer> slow = linkedList.head;
        SinglyLinkedList.Node<Integer> fast = linkedList.head;

        // Dopóki fast i fast.next nie osiągną końca, poruszamy się
        while (fast != null && fast.next != null) {
            slow = slow.next;         // jeden krok
            fast = fast.next.next;    // dwa kroki

            // Jeśli wskaźniki się spotkają, mamy cykl
            if (slow == fast) {
                return true;
            }
        }

        // Brak cyklu — fast dotarł do końca listy
        return false;
    }
}
