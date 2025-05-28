package pl.jakubtworek.medium.intervals;

import java.util.*;

class Exercise {
    /**
     * Sprawdza, czy każdy przedział nachodzi na przynajmniej jeden inny.
     *
     * Złożoność: O(n^2)
     */
    static boolean allIntervalsOverlapAtLeastOne(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) return false;

        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(Interval::getStart));

        Interval current = sorted.get(0);

        for (int i = 1; i < sorted.size(); i++) {
            Interval next = sorted.get(i);

            if (current.getEnd() >= next.getStart()) {
                if (current.getEnd() <= next.getEnd()) {
                    current = next;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Zwraca minimalną liczbę przedziałów do usunięcia, by reszta się nie nakładała.
     *
     * Przykład:
     * Input: [[1,3], [2,4], [3,5]]
     * Output: 1 (np. usuń [2,4])
     */
    static int eraseOverlappingIntervals(List<Interval> intervals) {
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(Interval::getStart));

        Interval current = sorted.get(0);
        int prevEnd = current.getEnd();
        int count = 0;

        for (int i = 1; i < sorted.size(); i++) {
            Interval next = sorted.get(i);

            if (prevEnd > next.getStart()) {
                count++;
            }
            if (next.getEnd() < prevEnd) {
                prevEnd = next.getEnd();
            }

        }

        return count;
    }
}
