package pl.jakubtworek.binary_search;

import java.util.List;

public class NonIntuitiveSearchSpace {
    static int cuttingWoods(List<Integer> heights, int k) {
        int left = 0;
        int right = heights.stream().max(Integer::compare).orElse(0);

        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (cutEnoughWood(mid, k, heights)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static boolean cutEnoughWood(int mid, int k, List<Integer> list) {
        return list.stream().filter(x -> x > mid).mapToInt(x -> x - mid).sum() >= k;
    }
}
