package pl.jakubtworek.medium.prefix_sums;

import java.util.HashMap;
import java.util.Map;

class Exercise {
    /**
     * Znajduje długość najdłuższej podtablicy o sumie równej k.
     *
     * Przykład:
     * arr = [1, -1, 5, -2, 3], k = 3 → 4 (bo [1, -1, 5, -2])
     *
     * Złożoność czasowa: O(n)
     */
    static int longestSubarraySumEqualsK(int[] arr, int k) {
        Map<Integer, Integer> sumIndex = new HashMap<>();
        int sum = 0, maxLen = 0;
        sumIndex.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sumIndex.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - sumIndex.get(sum - k));
            }
            sumIndex.putIfAbsent(sum, i);
        }

        return maxLen;
    }

}
