package pl.jakubtworek.linked_list;

public class Restructuring {
    /**
    Algorytm linkedListReversal:

    Zadanie:
    Odwraca jednokierunkową listę połączoną (singly linked list) — modyfikuje ją w miejscu
    tak, by pierwszy element stał się ostatnim, drugi przedostatnim itd.

    Działanie:
    - Używamy trzech wskaźników:
      - prevNode — poprzedni węzeł (na początku null),
      - currentNode — aktualnie odwiedzany węzeł,
      - nextNode — zapasowy wskaźnik na kolejny węzeł, by nie zgubić ciągu.
    - W każdej iteracji:
      - Odwracamy wskaźnik currentNode.next, by wskazywał na prevNode,
      - Przesuwamy prevNode i currentNode do przodu.
    - Na końcu ustawiamy `linkedList.head = prevNode`.

    Złożoność:
    - Czasowa: O(n) — odwiedzamy każdy węzeł dokładnie raz.
    - Pamięciowa: O(1) — używamy tylko stałej liczby wskaźników pomocniczych.
    */
    static SinglyLinkedList<Integer> linkedListReversal(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node<Integer> prevNode = null;
        SinglyLinkedList.Node<Integer> currentNode = linkedList.head;

        while (currentNode != null) {
            SinglyLinkedList.Node<Integer> nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }

        linkedList.head = prevNode;
        return linkedList;
    }

    /**
    Algorytm removeKthNode:

    Zadanie:
    Usuwa k-ty węzeł od końca z jednokierunkowej listy połączonej (singly linked list).
    Indeksacja od 1: k=1 oznacza ostatni węzeł.

    Działanie:
    - Tworzymy tymczasowy węzeł `dummy` jako sztuczny początek listy (dla uproszczenia brzegów).
    - Używamy dwóch wskaźników:
      - leader — przesuwamy go o k kroków do przodu,
      - trailer — pozostaje z tyłu, by zatrzymać się tuż przed usuwanym węzłem.
    - Gdy leader osiągnie koniec, trailer.next wskazuje na k-ty od końca — więc usuwamy go przez:
      `trailer.next = trailer.next.next`
    - Na końcu aktualizujemy `linkedList.head` w razie usunięcia pierwszego elementu.

    Złożoność:
    - Czasowa: O(n) — dwa przebiegi: jeden do przesunięcia leadera, drugi do końca.
    - Pamięciowa: O(1) — stała liczba wskaźników pomocniczych.

    Uwagi:
    - Jeśli `k` jest większe niż długość listy, lista pozostaje bez zmian.
    */
    static SinglyLinkedList<Integer> removeKthNode(SinglyLinkedList<Integer> linkedList, int k) {
        SinglyLinkedList.Node<Integer> dummy = new SinglyLinkedList.Node<>(0);
        dummy.next = linkedList.head;

        SinglyLinkedList.Node<Integer> trailer = dummy;
        SinglyLinkedList.Node<Integer> leader = dummy;

        for (int i = 0; i < k; i++) {
            if (leader.next == null) return linkedList;
            leader = leader.next;
        }

        while (leader.next != null) {
            System.out.println(trailer.value + " " + leader.value);
            leader = leader.next;
            trailer = trailer.next;
        }

        trailer.next = trailer.next.next;
        linkedList.head = dummy.next;

        return linkedList;
    }
}
