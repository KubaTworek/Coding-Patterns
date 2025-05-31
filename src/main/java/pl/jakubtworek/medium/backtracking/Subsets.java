package pl.jakubtworek.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

class Subsets {

    /**
     * Zwraca wszystkie możliwe podzbiory zbioru wejściowego.
     *
     * Algorytm:
     * - Backtracking: na każdej pozycji wybieramy, czy dodać element, czy go pominąć.
     * - Zawiera również zbiór pusty.
     *
     * Złożoność czasowa: O(2^n), gdzie n to liczba elementów.
     * Złożoność pamięciowa: O(2^n)
     */
    static List<List<Integer>> findAllSubsets(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsets(List<Integer> nums, int index, List<Integer> temp, List<List<Integer>> result) {
        result.add(new ArrayList<>(temp));

        for (int i = index; i < nums.size(); i++) {
            temp.add(nums.get(i));
            backtrackSubsets(nums, i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
}
