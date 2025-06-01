package pl.jakubtworek.hard.math_and_geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Geometry {
    /**
     * Zwraca listę elementów macierzy odwiedzanych w kolejności spiralnej.
     *
     * Algorytm:
     * - Używamy czterech wskaźników: top, bottom, left, right
     * - Iterujemy przez kolejne warstwy macierzy zgodnie z ruchem wskazówek zegara:
     *   → prawa → dół → lewa → góra
     * - Przesuwamy granice po każdej iteracji
     *
     * Złożoność czasowa: O(m * n)
     * Złożoność pamięciowa: O(1) poza wynikiem
     */
    static List<Integer> spiralTraversal(List<List<Integer>> matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.isEmpty()) return result;

        int top = 0;
        int bottom = matrix.size() - 1;
        int left = 0;
        int right = matrix.get(0).size() - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) result.add(matrix.get(top).get(i));
            top++;
            for (int i = top; i <= bottom; i++) result.add(matrix.get(i).get(right));
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) result.add(matrix.get(bottom).get(i));
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix.get(i).get(left));
                left++;
            }
        }

        return result;
    }

    /**
     * Dla danej listy punktów 2D oblicza maksymalną liczbę punktów leżących na jednej prostej.
     *
     * Algorytm:
     * - Dla każdego punktu bazowego liczymy nachylenie (slope) względem każdego innego.
     * - Przechowujemy częstość danego nachylenia w mapie.
     * - Uwzględniamy duplikaty oraz pionowe linie (dzielenie przez 0).
     *
     * Złożoność czasowa: O(n^2)
     * Złożoność pamięciowa: O(n)
     */
    static int maximumCollinearPoints(List<List<Integer>> points) {
        if (points.size() <= 1) return points.size();

        int max = 0;
        for (int i = 0; i < points.size(); i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicate = 1;
            int localMax = 0;

            for (int j = 0; j < points.size(); j++) {
                if (i == j) continue;
                int dx = points.get(j).get(0) - points.get(i).get(0);
                int dy = points.get(j).get(1) - points.get(i).get(1);

                if (dx == 0 && dy == 0) {
                    duplicate++;
                } else {
                    int gcd = gcd(dx, dy);
                    dx /= gcd;
                    dy /= gcd;
                    String slope = dy + "/" + dx;
                    slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                    localMax = java.lang.Math.max(localMax, slopeMap.get(slope));
                }
            }

            max = java.lang.Math.max(max, localMax + duplicate);
        }

        return max;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? java.lang.Math.abs(a) : gcd(b, a % b);
    }
}
