package pl.jakubtworek.easy.linked_list;

class Traversal {

    /**
       Algorytm: findIntersectionNode

       Zadanie:
       Znajduje punkt przecięcia dwóch jednokierunkowych list połączonych.
       Punkt przecięcia to pierwszy wspólny obiekt (w pamięci), który należy do obu list.

       Przykład:
       ListA: 1 → 2 → [7 → 8 → 9]
       ListB:      3 → [7 → 8 → 9]
       → wynik: Node z wartością 7 (ten sam obiekt w pamięci)

       Działanie:
       - Używamy dwóch wskaźników: `pointerA` (dla listy A) i `pointerB` (dla listy B).
       - W każdej iteracji sprawdzamy, czy wskazują na ten sam obiekt.
       - Jeśli któryś z nich osiągnie koniec swojej listy, zaczyna od początku drugiej.
       - Gwarantuje to, że oba wskaźniki przejdą tyle samo kroków:
         n (długość A) + m (długość B) i spotkają się:
           • na wspólnym węźle (jeśli istnieje),
           • lub na null (jeśli nie ma przecięcia).

       Złożoność:
       - Czasowa: O(n + m)
         • n – długość listy A
         • m – długość listy B
         • Każdy wskaźnik wykonuje co najwyżej n + m kroków

       - Pamięciowa: O(1)
         • Stała ilość wskaźników, bez struktur pomocniczych
     */
    static SinglyLinkedList.Node<Integer> findIntersectionNode(
            SinglyLinkedList<Integer> listA,
            SinglyLinkedList<Integer> listB
    ) {
        SinglyLinkedList.Node<Integer> pointerA = listA.head;
        SinglyLinkedList.Node<Integer> pointerB = listB.head;

        // Iterujemy, aż wskaźniki spotkają się (lub oba będą null)
        while (pointerA != pointerB) {
            pointerA = (pointerA != null) ? pointerA.next : listB.head;
            pointerB = (pointerB != null) ? pointerB.next : listA.head;
        }

        return pointerA; // null jeśli nie ma przecięcia, albo wspólny węzeł
    }
}
