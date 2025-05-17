package pl.jakubtworek.easy.two_pointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InwardTraversal {

    /**
       Algorytm: findPairWithTargetSum

       Zadanie:
       Znajduje dwie liczby w posortowanej rosnąco liście, których suma jest równa zadanej wartości `target`.

       Złożoność:
       - Czasowa: O(n) — każdy wskaźnik porusza się maksymalnie raz.
       - Pamięciowa: O(1) — stała liczba zmiennych pomocniczych.
     */
    static List<Integer> findPairWithTargetSum(List<Integer> nums, int target) {
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
       Algorytm: findTripletsWithTargetSum

       Zadanie:
       Zwraca wszystkie unikalne trójki (a, b, c), których suma wynosi `target`.

       Złożoność:
       - Czasowa: O(n²) — zewnętrzna pętla O(n), wewnętrzne przesuwanie wskaźników O(n).
       - Pamięciowa: O(k) — k to liczba unikalnych trójek.
     */
    static List<List<Integer>> findTripletsWithTargetSum(List<Integer> nums, int target) {
        List<Integer> sorted = new ArrayList<>(nums);
        Collections.sort(sorted);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < sorted.size() - 2; i++) {
            if (i > 0 && sorted.get(i).equals(sorted.get(i - 1))) {
                continue;
            }

            int first = sorted.get(i);
            int left = i + 1;
            int right = sorted.size() - 1;

            while (left < right) {
                int sum = first + sorted.get(left) + sorted.get(right);
                if (sum == target) {
                    result.add(List.of(first, sorted.get(left), sorted.get(right)));

                    int leftValue = sorted.get(left);
                    while (left < right && sorted.get(left).equals(leftValue)) {
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
       Algorytm: isPalindromeIgnoringNonAlphanumeric

       Zadanie:
       Sprawdza, czy ciąg znaków jest palindromem, ignorując znaki niealfanumeryczne i wielkość liter.

       Złożoność:
       - Czasowa: O(n) — każdy znak analizowany co najwyżej raz.
       - Pamięciowa: O(1) — bez dodatkowych struktur.
     */
    static boolean isPalindromeIgnoringNonAlphanumeric(String s) {
        if (s.isEmpty() || s.length() == 1) return true;

        String input = s.toLowerCase();
        int left = 0, right = input.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(input.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(input.charAt(right))) {
                right--;
            }
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
       Algorytm: maxWaterContainer

       Zadanie:
       Oblicza maksymalną ilość wody, jaką można zatrzymać między dwoma pionowymi liniami reprezentowanymi jako wysokości.

       Złożoność:
       - Czasowa: O(n) — każdy wskaźnik przesuwa się co najwyżej raz.
       - Pamięciowa: O(1) — nie używamy dodatkowych struktur danych.
     */
    static int maxWaterContainer(List<Integer> heights) {
        int left = 0;
        int right = heights.size() - 1;
        int maxArea = 0;

        while (left < right) {
            int height = Math.min(heights.get(left), heights.get(right));
            int width = right - left;
            maxArea = Math.max(maxArea, height * width);

            if (heights.get(left) < heights.get(right)) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
