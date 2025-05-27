package pl.jakubtworek.easy.sliding_windw;

import java.util.*;

class Exercise {
    /**
     * Znajdź najmniejsze okno w `s`, które zawiera wszystkie znaki ze stringa `pattern` (włącznie z liczbą wystąpień).
     * Jeśli takie okno nie istnieje — zwróć pusty string.
     *
     * Przykład:
     * Input: s = "ADOBECODEBANC", pattern = "ABC" → Output: "BANC"
     *
     * Sliding Window - Dynamiczny | Czas: O(n), Pamięć: O(m)
     */
    static String minWindowContainingAllChars(String s, String pattern) {
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        List<Character> chars = new ArrayList<>();
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < pattern.length(); i++) {
            chars.add(pattern.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if (chars.contains(s.charAt(i))) {
                map.put(s.charAt(i), i);
            }
            right++;

            System.out.println(map + " " + pattern);
            if (map.size() == pattern.length()) {
                if (right - left < min) {
                    min = right - left;
                }
                System.out.println(left);
                left = map.values().stream().distinct().sorted().skip(1).findFirst().orElse(0);
                map.remove(s.charAt(left));
            }

        }
        return s.substring(left - 1, right);
    }

    /**
     * Zlicz liczbę podciągów w tablicy `nums`, których suma wynosi dokładnie `k`.
     * Wszystkie liczby są nieujemne.
     *
     * Przykład:
     * Input: nums = [1,2,3], k = 3 → Output: 2 ([1,2] i [3])
     *
     * Sliding Window - Dynamiczny | Czas: O(n), Pamięć: O(1)
     */
    static int countSubarraysWithSumK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int pointer = left;
        int sum = 0;
        int result = 0;

        while (left < nums.length && right < nums.length) {
            System.out.println(Arrays.toString(nums) + " " + pointer + " " + sum);
            sum += nums[pointer];
            pointer++;
            if (pointer > right) {
                if (sum == k) {
                    System.out.println(left + " " + right);
                    result++;
                    sum = 0;
                }
                left++;
                right++;
                pointer = left;
            }
        }

        return result;
    }

    /**
     * Zwróć listę liczby unikalnych znaków w każdym oknie długości `k` w ciągu `s`.
     *
     * Przykład:
     * Input: s = "abcba", k = 3 → Output: [3,2,2]
     *
     * Sliding Window - Statyczny | Czas: O(n), Pamięć: O(k)
     */
    static List<Integer> countDistinctCharsInEveryWindow(String s, int k) {
        int left = 0;
        int right = k - 1;
        int pointer = left;
        HashSet<Character> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        while (left < s.length() && right < s.length()) {
            set.add(s.charAt(pointer));
            pointer++;
            if (pointer > right) {
                result.add(set.size());
                set.clear();
                left++;
                right++;
                pointer = left;
            }
        }

        return result;
    }
}
