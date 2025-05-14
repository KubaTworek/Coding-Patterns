package pl.jakubtworek.linked_list;

public class Traversal {
    /**
    Algorytm intersection:

    Zadanie:
    Znajduje punkt przecięcia dwóch jednokierunkowych list połączonych.
    Przecięcie oznacza, że od pewnego węzła (Node) obie listy mają wspólny fragment w pamięci
    (te same obiekty Node, nie tylko równe wartości).

    Działanie:
    - Używamy dwóch wskaźników: `pointerA` i `pointerB`, które zaczynają odpowiednio
      na początku listy A i B.
    - W każdej iteracji porównujemy, czy oba wskaźniki wskazują na ten sam obiekt (`pointerA == pointerB`).
    - Jeśli któryś wskaźnik osiąga koniec listy, „przeskakuje” na początek drugiej listy.
    - Dzięki temu oba wskaźniki przejdą dokładnie `lengthA + lengthB` kroków i spotkają się:
      - na wspólnym węźle (jeśli przecięcie istnieje),
      - lub na `null` (jeśli przecięcia nie ma).

    Przykład:
    ListA: 1 → 2 → [7 → 8 → 9]
    ListB:      3 → [7 → 8 → 9]
    → wynik: węzeł z wartością 7 (ten sam obiekt Node)

    Złożoność:
    - Czasowa: O(n + m)
      - n — długość listy A, m — długość listy B
      - Każdy wskaźnik przechodzi maksymalnie 2 razy przez swoją listę
    - Pamięciowa: O(1)
      - Stała liczba zmiennych pomocniczych, bez dodatkowych struktur danych

    Zalety:
    - Nie wymaga znajomości długości list
    - Nie modyfikuje list
    - Nie używa dodatkowej pamięci
    */
    static SinglyLinkedList.Node<Integer> intersection(SinglyLinkedList<Integer> linkedListA, SinglyLinkedList<Integer> linkedListB) {
        SinglyLinkedList.Node<Integer> pointerA = linkedListA.head;
        SinglyLinkedList.Node<Integer> pointerB = linkedListB.head;

        while (pointerA != pointerB) {
            pointerA = (pointerA != null) ? pointerA.next : linkedListB.head;
            pointerB = (pointerB != null) ? pointerB.next : linkedListA.head;
        }

        return pointerA;
    }
}
