package pl.jakubtworek.fast_and_slow_pointers;

public class FractionalPointIdentification {
    /**
    Algorytm isHappyNumber:

    Zadanie:
    Sprawdza, czy liczba całkowita jest tzw. szczęśliwą liczbą (happy number).
    Liczba jest szczęśliwa, jeśli iteracyjnie sumując kwadraty jej cyfr, otrzymamy 1.
    Jeśli proces wpadnie w cykl, który nie zawiera 1 — liczba nie jest szczęśliwa.

    Działanie:
    - Używamy algorytmu wykrywania cyklu (fast & slow pointers).
    - `slow` oblicza kolejną wartość przez sumę kwadratów cyfr,
      `fast` robi to dwukrotnie szybciej (czyli dwa razy z rzędu).
    - Jeśli `fast` osiągnie 1 → liczba jest szczęśliwa.
    - Jeśli `slow == fast` (i nie jest to 1) → wykryto cykl, liczba nie jest szczęśliwa.

    Złożoność:
    - Czasowa: O(log n) — długość ścieżki zależy od liczby cyfr i wartości pośrednich.
    - Pamięciowa: O(1) — nie używamy dodatkowych struktur (np. zbiorów).

    Uwagi:
    - Pomocnicza funkcja `getNextNumber` sumuje kwadraty cyfr danej liczby.
    */
    static boolean isHappyNumber(int number) {
        int slow = number;
        int fast = getNextNumber(number);

        while (fast != 1 && slow != fast) {
            slow = getNextNumber(slow);
            fast = getNextNumber(getNextNumber(fast));
        }

        return fast == 1;
    }

    private static int getNextNumber(int number) {
        int nextNumber = 0;
        while (number > 0) {
            int newNumber = number % 10;
            nextNumber += newNumber * newNumber;
            number = number / 10;
        }

        return nextNumber;
    }
}
