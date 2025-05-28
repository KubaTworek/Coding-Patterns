package pl.jakubtworek.medium.intervals;

import java.util.*;

class ManagingOverlaps {
    /**
     * Scalanie nakładających się przedziałów.
     *
     * Algorytm:
     * 1. Umieszcza wszystkie przedziały w kolejce priorytetowej posortowanej po początku przedziału.
     * 2. W pętli:
     *    - Pobiera dwa kolejne przedziały z kolejki.
     *    - Jeśli się nakładają (czyli koniec pierwszego >= początek drugiego), scala je w nowy przedział i dodaje go z powrotem do kolejki.
     *    - Jeśli się nie nakładają, dodaje pierwszy do wyniku, a drugi z powrotem do kolejki.
     * 3. Gdy zostanie jeden przedział, dodaje go do wyniku.
     *
     * Złożoność czasowa:
     * - Sortowanie przez kolejkę priorytetową: O(n log n)
     * - Przetwarzanie wszystkich elementów: O(n log n) (ze względu na operacje dodawania i pobierania z kolejki)
     * - Całkowita złożoność: **O(n log n)**
     *
     * Złożoność pamięciowa:
     * - O(n) — dla przechowywania przedziałów w kolejce i wyniku
     */
    static List<Interval> mergeOverlappingIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        // Kolejka priorytetowa sortująca po początku przedziału
        Queue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(Interval::getStart));
        queue.addAll(intervals);

        // Przetwarzanie kolejki do momentu aż będzie pusta
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                Interval actual = queue.poll();  // pierwszy przedział
                Interval next = queue.poll();    // kolejny przedział

                // Sprawdzenie, czy przedziały się nakładają
                if (actual.getEnd() >= next.getStart()) {
                    // Scalanie — tworzymy nowy przedział i dodajemy z powrotem do kolejki
                    queue.add(new Interval(actual.getStart(), Math.max(actual.getEnd(), next.getEnd())));
                } else {
                    // Nie nachodzą — dodaj aktualny do wyniku, a następny z powrotem do kolejki
                    result.add(actual);
                    queue.add(next);
                }
            } else {
                // Ostatni element — można go bezpiecznie dodać do wyniku
                result.add(queue.poll());
            }
        }

        return result;
    }

    /**
     * Identyfikuje i scala wszystkie nakładające się przedziały z podanej listy
     * do wspólnych przedziałów reprezentujących obszary nakładania.
     *
     * Algorytm:
     * 1. Sortuje przedziały rosnąco względem początku.
     * 2. Iteruje po posortowanej liście, sprawdzając, czy kolejne przedziały się nakładają.
     *    - Jeśli tak, oblicza wspólny fragment: [max(startA, startB), min(endA, endB)].
     *    - Jeśli ten fragment nachodzi się z poprzednim fragmentem wynikowym, scala je.
     *    - W przeciwnym razie dodaje nowy fragment do listy wynikowej.
     * 3. Zwra ca listę nienakładających się, maksymalnie scalonych przedziałów reprezentujących
     *    obszary nakładania.
     *
     * Przykład:
     * Dla wejścia: [ [2, 5], [4, 6], [5, 7] ]
     * Zwraca:     [ [4, 7] ]  (jeden wspólny fragment nachodzenia)
     *
     * Złożoność czasowa:
     * - Sortowanie: O(n log n)
     * - Iteracja po liście: O(n)
     * - Całkowita złożoność: **O(n log n)**
     *
     * Złożoność pamięciowa:
     * - O(n) w najgorszym przypadku — gdy wszystkie przedziały mają nakładania
     */
    static List<Interval> getOverlappingIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() < 2) return result;

        // Sortujemy po początku
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(Interval::getStart));

        // Bierzemy pierwszy jako aktualny
        Interval current = sorted.get(0);

        for (int i = 1; i < sorted.size(); i++) {
            Interval next = sorted.get(i);

            if (current.getEnd() >= next.getStart()) {
                // Przedziały się nakładają, tworzymy zakres wspólny
                int overlapStart = Math.max(current.getStart(), next.getStart());
                int overlapEnd = Math.min(current.getEnd(), next.getEnd());

                // Jeśli lista pusta lub ostatni nie pokrywa się z nowym
                if (result.isEmpty() || result.get(result.size() - 1).getEnd() < overlapStart) {
                    result.add(new Interval(overlapStart, overlapEnd));
                } else {
                    // Scal z ostatnim zakresem
                    Interval last = result.remove(result.size() - 1);
                    result.add(new Interval(Math.min(last.getStart(), overlapStart), Math.max(last.getEnd(), overlapEnd)));
                }

                // Utrzymuj połączony zakres (maksymalnie zasięgnięty przez oba)
                current = new Interval(current.getStart(), Math.max(current.getEnd(), next.getEnd()));
            } else {
                // Przedziały się nie nakładają – przesuwamy current
                current = next;
            }
        }

        return result;
    }
}
