package pl.jakubtworek.easy.fast_and_slow_pointers;

class FractionalPointIdentification {

    /**
       Algorytm isHappy:

       Zadanie:
       Sprawdza, czy dana liczba całkowita jest tzw. "happy number".
       Liczba jest szczęśliwa, jeśli proces sumowania kwadratów jej cyfr, powtarzany iteracyjnie,
       doprowadzi do wartości 1. Jeśli proces zapętli się bez osiągnięcia 1 — liczba nie jest szczęśliwa.

       Przykład:
       19 → 1² + 9² = 82 → 8² + 2² = 68 → 6² + 8² = 100 → 1² + 0² + 0² = 1 → ✅ szczęśliwa

       Działanie:
       - Wykorzystujemy podejście "fast & slow pointer" do wykrywania cyklu w ciągu sum kwadratów cyfr.
         • `slow` porusza się o 1 krok: next(n),
         • `fast` porusza się o 2 kroki: next(next(n)).
       - Jeśli `fast` osiągnie 1 → liczba szczęśliwa.
       - Jeśli `slow == fast` → wykryto cykl (chyba że to 1).

       Złożoność:
       - Czasowa: O(log n)
         • Dla liczby n, liczba jej cyfr to O(log n).
         • Operacja sumy kwadratów cyfr wykonuje się na coraz mniejszych liczbach — szybko zbiega do cyklu lub 1.
       - Pamięciowa: O(1)
         • Brak dodatkowych struktur jak Set, tylko kilka zmiennych.

       Uwagi:
       - Szybsze niż rozwiązanie ze zbiorem (które ma O(n) pamięci).
       - Zawsze zakończy się, bo możliwych wartości pośrednich jest ograniczona liczba (np. < 1000).
     */
    static boolean isHappy(int number) {
        int slow = number;
        int fast = getNext(number);

        // Dopóki fast nie osiągnie 1 lub nie wykryjemy cyklu
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);                 // 1 krok
            fast = getNext(getNext(fast));        // 2 kroki
        }

        return fast == 1;
    }

    /**
       Pomocnicza funkcja: zwraca sumę kwadratów cyfr danej liczby.
       Przykład: 82 → 8² + 2² = 64 + 4 = 68
     */
    private static int getNext(int number) {
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }

        return sum;
    }
}
