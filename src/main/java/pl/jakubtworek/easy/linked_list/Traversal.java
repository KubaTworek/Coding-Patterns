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

    /**
       Algorytm: isLinkedListPalindrome

       Zadanie:
       Sprawdza, czy jednokierunkowa lista połączona (`SinglyLinkedList`) jest palindromem —
       czyli czy jej zawartość czytana od początku i od końca jest taka sama.

       Działanie:
       1. Znajdujemy środkowy węzeł listy za pomocą wskaźników typu slow/fast.
       2. Odwracamy drugą połowę listy od środkowego elementu.
       3. Porównujemy elementy z pierwszej połowy i odwróconej drugiej połowy.
       4. (Opcjonalnie: można przywrócić strukturę listy do oryginału po sprawdzeniu).

       Złożoność:
       - Czasowa: O(n)
         • Jedno przejście do środka listy (n/2)
         • Jedno przejście do końca + odwrócenie drugiej połowy (n/2)
         • Jedno porównanie dwóch połówek (n/2)
         → Łącznie: O(n)

       - Pamięciowa: O(1)
         • Używamy stałej liczby wskaźników — bez dodatkowej struktury danych.
     */
    static boolean isLinkedListPalindrome(SinglyLinkedList<Integer> list) {
        if (list.head == null || list.head.next == null) return true;

        // Krok 1: Znajdź środek listy (slow zatrzyma się w połowie)
        SinglyLinkedList.Node<Integer> slow = list.head;
        SinglyLinkedList.Node<Integer> fast = list.head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Krok 2: Odwróć drugą połowę listy
        SinglyLinkedList.Node<Integer> secondHalfReversed = reverse(slow);

        // Krok 3: Porównaj pierwszą i drugą połowę (odwróconą)
        SinglyLinkedList.Node<Integer> pointer1 = list.head;
        SinglyLinkedList.Node<Integer> pointer2 = secondHalfReversed;

        while (pointer2 != null) {
            if (!pointer1.value.equals(pointer2.value)) {
                return false;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return true;
    }

    /**
     * Pomocnicza metoda do odwracania listy jednokierunkowej.
     * Zwraca nową głowę odwróconej listy.
     */
    private static SinglyLinkedList.Node<Integer> reverse(SinglyLinkedList.Node<Integer> head) {
        SinglyLinkedList.Node<Integer> prev = null;
        SinglyLinkedList.Node<Integer> current = head;

        while (current != null) {
            SinglyLinkedList.Node<Integer> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
