package pl.jakubtworek.easy.linked_list;

import java.util.Objects;

class Exercise {

    /**
     * removeDuplicatesFromSortedList

     * Zadanie:
     * Dla posortowanej jednokierunkowej listy połączonej usuń wszystkie duplikaty tak,
     * aby każdy element pojawiał się tylko raz.

     * Przykład:
     * [1 → 1 → 2 → 3 → 3] → [1 → 2 → 3]
     */
    static SinglyLinkedList<Integer> removeDuplicatesFromSortedList(SinglyLinkedList<Integer> list) {
        if (list.head == null || list.head.next == null) { return list; }
        SinglyLinkedList.Node<Integer> left = list.head;
        SinglyLinkedList.Node<Integer> right = list.head.next;
        while (left.next != null) {
            if (left.value.equals(right.value)) {
                left.next = right.next;
                right = left.next;
            } else {
                left = left.next;
                right = right.next;
            }
        }
        return list;
    }

    /**
     * rotateRight

     * Zadanie:
     * Obróć jednokierunkową listę połączoną o `k` pozycji w prawo (każdy element przemieszcza się o k miejsc).

     * Przykład:
     * [1 → 2 → 3 → 4 → 5], k = 2 → [4 → 5 → 1 → 2 → 3]
     */
    static SinglyLinkedList<Integer> rotateRight(SinglyLinkedList<Integer> list, int k) {
        if (list.head == null || list.head.next == null || k == 0) return list;

        // Step 1: oblicz długość
        int size = 1;
        SinglyLinkedList.Node<Integer> tail = list.head;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }

        // Step 2: k może być większe niż size
        k = k % size;
        if (k == 0) return list;

        // Step 3: znajdź nowy tail (na pozycji size - k - 1)
        int stepsToNewTail = size - k;
        SinglyLinkedList.Node<Integer> newTail = list.head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 4: nowy head to element za newTail
        SinglyLinkedList.Node<Integer> newHead = newTail.next;

        // Step 5: zamknij stary ogon z początkiem
        tail.next = list.head;

        // Step 6: utnij nowy ogon
        newTail.next = null;

        // Step 7: ustaw nowy head
        list.head = newHead;

        return list;
    }

    /**
     * hasCycle

     * Zadanie:
     * Sprawdź, czy dana jednokierunkowa lista zawiera cykl.
     * (czyli czy któryś z węzłów wskazuje z powrotem na poprzedni).

     * Wskazówka: użyj techniki slow/fast pointers.
     */
    static boolean hasCycle(SinglyLinkedList<Integer> list) {
        SinglyLinkedList.Node<Integer> left = list.head;
        SinglyLinkedList.Node<Integer> right = list.head.next;

        while (right.next.next != null) {
            left = left.next;
            right = right.next.next;
            if (left == right) {
                return true;
            }
        }

        return false;
    }

    /**
     * areIdentical

     * Zadanie:
     * Zwróć true, jeśli dwie jednokierunkowe listy są identyczne — zawierają te same elementy w tej samej kolejności.
     */
    static boolean areIdentical(SinglyLinkedList<Integer> a, SinglyLinkedList<Integer> b) {
        if ((a.head == null || a.head.next == null) && (b.head == null || b.head.next == null)) return true;
        if ((a.head == null || a.head.next == null) || (b.head == null || b.head.next == null)) return false;

        SinglyLinkedList.Node<Integer> left = a.head;
        SinglyLinkedList.Node<Integer> right = b.head;
        while (left.next != null) {
            if (!Objects.equals(left.next.value, right.next.value)) {
                return false;
            }

            left = left.next;
            right = right.next;
            if ((left.next == null && right.next != null) || (left.next != null && right.next == null)) {
                return false;
            }
        }

        return true;
    }
}
