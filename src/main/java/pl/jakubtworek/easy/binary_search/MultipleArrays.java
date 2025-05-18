package pl.jakubtworek.easy.binary_search;

import java.util.List;

class MultipleArrays {

    /**
       Algorytm: findMedianOfTwoSortedArrays

       Zadanie:
       Dla dwóch posortowanych tablic (lub list) `nums1` i `nums2`, znajdź medianę zbioru
       powstałego z ich połączenia (w porządku rosnącym).

       Działanie:
       - Używamy binary search po krótszej tablicy, aby znaleźć punkt podziału.
       - Szukamy takiego indeksu, by wszystkie elementy po lewej stronie były mniejsze
         lub równe wszystkim elementom po prawej stronie (z obu list).
       - Gdy znajdziemy właściwy podział, wyliczamy medianę:
         • Jeśli łączna liczba elementów parzysta: średnia z max(left) i min(right)
         • Jeśli nieparzysta: min(right)

       Złożoność:
       - Czasowa: O(log(min(m, n))) — binarne przeszukiwanie tylko po krótszej tablicy
       - Pamięciowa: O(1) — stała liczba zmiennych pomocniczych
     */
    static double findMedianOfTwoSortedArrays(List<Integer> nums1, List<Integer> nums2) {
        // Upewnij się, że nums1 jest krótsze
        if (nums1.size() > nums2.size()) {
            return findMedianOfTwoSortedArrays(nums2, nums1);
        }

        int m = nums1.size();
        int n = nums2.size();
        int halfLen = (m + n) / 2;

        int left = 0;
        int right = m;

        while (true) {
            int i = (left + right) / 2;          // podział w nums1
            int j = halfLen - i;                 // odpowiadający podział w nums2

            int L1 = (i == 0) ? Integer.MIN_VALUE : nums1.get(i - 1);
            int R1 = (i == m) ? Integer.MAX_VALUE : nums1.get(i);
            int L2 = (j == 0) ? Integer.MIN_VALUE : nums2.get(j - 1);
            int R2 = (j == n) ? Integer.MAX_VALUE : nums2.get(j);

            if (L1 > R2) {
                right = i - 1;  // za daleko w prawo w nums1
            } else if (L2 > R1) {
                left = i + 1;   // za daleko w lewo w nums1
            } else {
                if ((m + n) % 2 == 0) {
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                } else {
                    return Math.min(R1, R2);
                }
            }
        }
    }
}
