package pl.jakubtworek.medium.intervals;

import java.util.*;

class FindingValuesInSortedOrder {

    /**
     * Oblicza maksymalną liczbę nakładających się przedziałów w danym zbiorze.
     *
     * Algorytm:
     * 1. Tworzy strukturę pomocniczą — listę punktów (startów i końców przedziałów) typu Point.
     *    Każdy punkt ma etykietę: "S" (start) lub "E" (end).
     * 2. Umieszcza te punkty w kolejce priorytetowej, sortowanej najpierw po współrzędnej punktu,
     *    a następnie po etykiecie: "S" ma pierwszeństwo przed "E", co oznacza, że przy tej samej wartości
     *    najpierw zliczamy początek przedziału, a dopiero potem jego koniec.
     * 3. Iteruje po posortowanych punktach:
     *    - Dla punktu startowego ("S") zwiększa licznik aktywnych przedziałów.
     *    - Dla punktu końcowego ("E") zmniejsza licznik.
     *    - Po każdym kroku aktualizuje wartość maksymalnej liczby aktywnych przedziałów.
     *
     * Złożoność czasowa:
     * - O(n log n) — dodanie n * 2 punktów do kolejki priorytetowej i ich przetworzenie
     *
     * Złożoność pamięciowa:
     * - O(n) — na przechowywanie punktów w kolejce
     */
    static int largestOverlapOfIntervals(List<Interval> intervals) {
        // Kolejka priorytetowa: najpierw według współrzędnej, potem "S" przed "E"
        Queue<Point> queue = new PriorityQueue<>(
                Comparator.comparing(Point::getPoint)
                        .thenComparing(Point::getLabel)
        );

        // Dla każdego przedziału dodaj jego początek i koniec jako punkt
        for (Interval interval : intervals) {
            queue.add(new Point(interval.getStart(), "S")); // początek
            queue.add(new Point(interval.getEnd(), "E"));   // koniec
        }

        int max = 0;   // maksymalna liczba nakładających się przedziałów
        int temp = 0;  // aktualna liczba aktywnych przedziałów

        int size = queue.size(); // liczba punktów (2 * liczba przedziałów)

        // Przetwarzanie wszystkich punktów
        for (int i = 0; i < size; i++) {
            Point point = queue.poll();

            if (Objects.equals(point.getLabel(), "S")) {
                temp++;               // start przedziału: +1
                max = Math.max(max, temp); // aktualizuj maksimum
            } else if (Objects.equals(point.getLabel(), "E")) {
                temp--;               // koniec przedziału: -1
            }
        }

        return max;
    }
}
