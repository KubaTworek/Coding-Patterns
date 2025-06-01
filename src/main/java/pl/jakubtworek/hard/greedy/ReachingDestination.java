package pl.jakubtworek.hard.greedy;

import java.util.List;

class ReachingDestination {
    /**
     * Sprawdza, czy można dotrzeć do ostatniego indeksu tablicy nums,
     * gdzie nums[i] oznacza maksymalną liczbę kroków, jaką można wykonać z pozycji i.
     *
     * Używa algorytmu zachłannego — śledzi najdalej osiągalny indeks.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(1)
     */
    static boolean jumpToTheEnd(List<Integer> nums) {
        int farthest = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i > farthest) return false;
            farthest = Math.max(farthest, i + nums.get(i));
        }
        return true;
    }

    /**
     * Zwraca indeks stacji, z której można rozpocząć podróż dookoła okręgu
     * (jeśli to możliwe), zakładając że na każdej stacji zatankujesz gas[i]
     * i potrzebujesz cost[i] paliwa do kolejnej.
     *
     * Algorytm zachłanny — zaczynamy od 0 i szukamy punktu, gdzie suma gazu staje się ujemna.
     * Jeśli całkowita różnica gazu >= 0, trasa jest możliwa.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(1)
     */
    static int gasStations(List<Integer> gas, List<Integer> cost) {
        int total = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.size(); i++) {
            int diff = gas.get(i) - cost.get(i);
            tank += diff;
            total += diff;
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }

        return total >= 0 ? start : -1;
    }
}
