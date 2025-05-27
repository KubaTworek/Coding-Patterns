package pl.jakubtworek.easy.binary_search;

import java.util.List;

class Exercise {

    /**
     * Zadanie 1: findSquareRootFloor
     * Znajdź największą liczbę całkowitą `x`, taką że `x * x <= n`, bez użycia funkcji pierwiastkowania.
     */
    static int findSquareRootFloor(int n) {
        if (n < 1000) return 0;
        if (n == 1) return 1;
        int lower = 0, upper = n;
        int x = (lower + upper) / 2;
        while (true) {
            if (x * x > n) {
                int temp = (lower + x) / 2;
                x = temp;
                if (x * x > n) {
                    lower = temp;
                    upper = x;
                } else if (x * x < n) {
                    lower = x;
                    upper = temp;
                } else {
                    return x;
                }
            } else if (x * x < n) {
                int temp = (x + upper) / 2;
                x = (x + upper) / 2;
                if (x * x > n) {
                    lower = temp;
                    upper = x;
                } else if (x * x < n) {
                    lower = x;
                    upper = temp;
                } else {
                    return x;
                }
            } else {
                return x;
            }
            System.out.println("x " + x);
        }
    }

    /**
     * Zadanie 2: findMinimumInRotatedSortedArray
     * Dla posortowanej i obróconej tablicy `nums`, znajdź najmniejszy element.
     * Przykład: [4,5,6,7,0,1,2] → 0
     */
    static int findMinimumInRotatedSortedArray(List<Integer> nums) {
        return 0;
    }

    /**
     * Zadanie 3: searchInRotatedSortedArray
     * Wyszukaj wartość `target` w posortowanej i obróconej tablicy `nums`.
     * Zwróć jej indeks lub -1, jeśli nie istnieje.
     */
    static int searchInRotatedSortedArray(List<Integer> nums, int target) {
        return 0;
    }
}
