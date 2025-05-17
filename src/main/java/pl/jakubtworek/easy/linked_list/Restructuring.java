package pl.jakubtworek.easy.linked_list;

class Restructuring {

    /**
       Algorytm reverse:

       Zadanie:
       Odwraca kolejność elementów w jednokierunkowej liście połączonej (in-place).

       Działanie:
       - Używamy trzech wskaźników:
         - `prev` – poprzedni węzeł (na początku null),
         - `current` – aktualny węzeł,
         - `next` – tymczasowy wskaźnik na kolejny węzeł, aby nie zgubić ciągu.
       - W pętli:
         - Przestawiamy `current.next` tak, aby wskazywał wstecz,
         - Przesuwamy wskaźniki do przodu.
       - Na końcu ustawiamy `head` na ostatni przetworzony węzeł (`prev`).

       Złożoność:
       - Czasowa: O(n) – każdy węzeł odwiedzany raz.
       - Pamięciowa: O(1) – stała liczba zmiennych pomocniczych.
     */
    static SinglyLinkedList<Integer> reverse(SinglyLinkedList<Integer> list) {
        SinglyLinkedList.Node<Integer> prev = null;
        SinglyLinkedList.Node<Integer> current = list.head;

        while (current != null) {
            SinglyLinkedList.Node<Integer> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        list.head = prev;
        return list;
    }

    /**
       Algorytm removeKthFromEnd:

       Zadanie:
       Usuwa k-ty element od końca z listy jednokierunkowej.
       (Przykład: k = 1 → usuwa ostatni element).

       Działanie:
       - Wprowadzamy węzeł tymczasowy `dummy`, aby uprościć brzegowe przypadki (np. usunięcie pierwszego).
       - Używamy dwóch wskaźników:
         - `leader` – idzie o k kroków do przodu,
         - `trailer` – pozostaje za `leader`, zatrzymując się tuż przed elementem do usunięcia.
       - Po dojściu `leader` do końca:
         - Usuwamy `trailer.next`, przestawiając wskaźnik.
       - Aktualizujemy `head` na wypadek usunięcia pierwszego elementu.

       Złożoność:
       - Czasowa: O(n) – pełne przejście przez listę.
       - Pamięciowa: O(1) – brak dodatkowych struktur.

       Dlaczego taka złożoność:
       - Musimy odwiedzić całą listę, aby zidentyfikować k-ty element od końca.
     */
    static SinglyLinkedList<Integer> removeKthFromEnd(SinglyLinkedList<Integer> list, int k) {
        SinglyLinkedList.Node<Integer> dummy = new SinglyLinkedList.Node<>(0);
        dummy.next = list.head;

        SinglyLinkedList.Node<Integer> leader = dummy;
        SinglyLinkedList.Node<Integer> trailer = dummy;

        // Przesuń leader o k kroków
        for (int i = 0; i < k; i++) {
            if (leader.next == null) return list; // k > długość
            leader = leader.next;
        }

        // Przesuwaj oba wskaźniki do końca
        while (leader.next != null) {
            leader = leader.next;
            trailer = trailer.next;
        }

        // Usuń k-ty węzeł od końca
        trailer.next = trailer.next.next;
        list.head = dummy.next;

        return list;
    }
}
