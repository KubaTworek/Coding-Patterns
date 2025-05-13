package pl.jakubtworek.two_pointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InwardTraversal {
    /**
     * Znajduje dwie liczby w posortowanej rosnąco liście, których suma jest równa zadanej wartości docelowej (target).

     * Zakładamy, że:
     * - Lista wejściowa 'nums' jest posortowana w porządku rosnącym.
     * - Istnieje co najwyżej jedna para spełniająca warunek.

     * Zwraca listę zawierającą dwie liczby tworzące poprawną parę,
     * albo pustą listę, jeśli taka para nie istnieje.

     * Przykład:
     *   nums = [1, 2, 4, 6, 10], target = 8
     *   -> wynik: [2, 6]

     * Złożoność:
         - Czasowa: O(n), ponieważ każdy wskaźnik (left, right) przesuwa się maksymalnie raz przez listę.
         - Pamięciowa: O(1), ponieważ używamy stałej liczby zmiennych pomocniczych, a zwracana lista zawiera co najwyżej dwa elementy.
     */
    static List<Integer> pairSumSorted(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum == target) {
                return List.of(nums.get(left), nums.get(right));
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return List.of();
    }

    /**
     * Zwraca wszystkie unikalne trójki (a, b, c) takie, że a + b + c = target.

     * - Lista wejściowa może zawierać duplikaty, ale wynikowe trójki mają być unikalne.
     * - Każda trójka w wyniku jest posortowana rosnąco.
     * - Zwracana lista może być w dowolnej kolejności.

     * Złożoność:
     * - Czasowa: O(n²)
     *   - Sortowanie listy: O(n log n)
     *   - Główna pętla z dwoma wskaźnikami: O(n^2) w najgorszym przypadku
     * - Pamięciowa: O(k)
     *   - Gdzie k to liczba unikalnych trójek spełniających warunek
     *   - Poza wynikiem używana pamięć jest stała: O(1)
     */
    static List<List<Integer>> tripletSum(List<Integer> nums, int target) {
        nums = new ArrayList<>(nums);
        Collections.sort(nums);
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.size() - 1; i++) {
            if (i > 0 && nums.get(i).equals(nums.get(i - 1))) {
                continue;
            }

            int first = nums.get(i);
            int left = i + 1;
            int right = nums.size() - 1;

            while (left < right) {
                int sum = first + nums.get(left) + nums.get(right);
                if (sum == target) {
                    result.add(List.of(first, nums.get(left), nums.get(right)));

                    int leftVal = nums.get(left);
                    while (left < right && nums.get(left).equals(leftVal)) {
                        left++;
                    }

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * Sprawdza, czy dany ciąg znaków jest palindromem, ignorując znaki nie-alfanumeryczne
     * oraz wielkość liter.

     * Przykłady:
     *   "A man, a plan, a canal: Panama" → true
     *   "race a car" → false
     *   " " → true (pusta zawartość po filtracji)

     * Działanie:
     * - Konwertuje ciąg na małe litery
     * - Pomija znaki, które nie są literami ani cyframi (np. spacje, przecinki)
     * - Porównuje znaki z lewej i prawej strony, przesuwając wskaźniki do środka

     * Złożoność:
     * - Czasowa: O(n), gdzie n = długość łańcucha wejściowego
     *   - Każdy znak jest sprawdzany co najwyżej raz
     * - Pamięciowa: O(1)
     *   - Poza małym narzutem na `toLowerCase()` i zmienne pomocnicze, nie używamy dodatkowej pamięci
     */
    static boolean isPalindromeValid(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        String string = s.toLowerCase();

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(string.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(string.charAt(right))) {
                right--;
            }
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Oblicza największą możliwą ilość "wody", jaką można przechować między dwiema liniami
     * z listy wysokości. Każdy element reprezentuje pionową linię o danej wysokości na osi X.

     * Strategia:
     * - Użyto podejścia z dwoma wskaźnikami (two pointers), startującymi z lewej i prawej strony listy.
     * - W każdej iteracji liczymy pole (szerokość * wysokość), gdzie wysokość to minimum z dwóch słupków.
     * - Następnie przesuwamy ten wskaźnik, który pokazuje na niższy słupek, aby potencjalnie uzyskać większą wysokość.

     * Złożoność obliczeniowa:
     * - Czasowa: O(n)
     *   - Każdy wskaźnik (left, right) przesuwa się maksymalnie raz przez całą listę.
     * - Pamięciowa: O(1)
     *   - Używamy tylko kilku zmiennych pomocniczych; brak dodatkowych struktur danych.
     */
    static int largestContainer(List<Integer> heights) {
        int left = 0;
        int right = heights.size() - 1;
        int maxWater = 0;

        while (left < right) {
            int water = Math.min(heights.get(left), heights.get(right)) * (right - left);
            if (water > maxWater) {
                maxWater = water;
            }

            if (heights.get(left) < heights.get(right)) {
                left++;
            } else if (heights.get(left) > heights.get(right)) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return maxWater;
    }
}
