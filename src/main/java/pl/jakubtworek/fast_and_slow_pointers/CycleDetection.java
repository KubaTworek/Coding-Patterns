package pl.jakubtworek.fast_and_slow_pointers;

import pl.jakubtworek.linked_list.SinglyLinkedList;

public class CycleDetection {
    /**
    Algorytm isCycleDetected:

    Zadanie:
    Sprawdza, czy w jednokierunkowej liście połączonej (singly linked list) istnieje cykl,
    czyli czy któryś z węzłów wskazuje z powrotem na wcześniejszy węzeł.

    Działanie:
    - Używamy dwóch wskaźników: slow i fast (algorytm "żółw i zając").
    - `slow` porusza się o 1 węzeł na krok, `fast` o 2 węzły.
    - Jeśli istnieje cykl, to `fast` i `slow` na pewno spotkają się wewnątrz cyklu.
    - Jeśli nie ma cyklu, `fast` dojdzie do końca listy (`null`) i pętla się zakończy.

    Złożoność:
    - Czasowa: O(n) — każdy wskaźnik przechodzi maksymalnie raz przez listę.
    - Pamięciowa: O(1) — nie używamy dodatkowych struktur danych.
    */
    static boolean isCycleDetected(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node<Integer> fast = linkedList.head;
        SinglyLinkedList.Node<Integer> slow = linkedList.head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
