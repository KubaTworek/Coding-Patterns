package pl.jakubtworek.easy.hash_maps_and_sets;

import java.util.*;

class Exercise {
    /**
     * Zadanie:
     * Zwróć długość najdłuższego podciągu bez powtarzających się znaków.
     * <p>
     * Przykład: "abcabcbb" → 3 (dla "abc")
     */
    static int findLongestSubstringWithoutRepeatingCharacters(String s) {
        Set<Character> set = new HashSet<>();
        int result = 0;
        int tempResult = 0;

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                tempResult = 1;
                set.clear();
                set.add(s.charAt(i));
            } else {
                tempResult++;
                set.add(s.charAt(i));
            }
            if (tempResult > result) {
                result = tempResult;
            }
        }
        return result;
    }

    /**
     * Zadanie:
     * Zlicz liczbę podtablic, których suma elementów wynosi dokładnie `target`.
     * <p>
     * Wskazówka: użyj mapy prefix sumów.
     * Przykład: [1,1,1], target=2 → wynik: 2 ([1,1], [1,1])
     */
    static int countSubarraysWithSum(List<Integer> nums, int target) {
        return 0;
    }

    /**
     * Zadanie:
     * Zwróć `k` najczęściej występujących elementów z listy liczb.
     * <p>
     * Wynik może być w dowolnej kolejności.
     */
    static List<Integer> topKFrequentElements(List<Integer> nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .map(Map.Entry::getKey)
                .limit(k)
                .toList();
    }

    /**
     * Zadanie:
     * Sprawdź, czy dwa ciągi znaków są izomorficzne — znaki jednego można jednoznacznie przemapować na znaki drugiego.
     * <p>
     * Przykład: "egg" i "add" → true, "foo" i "bar" → false
     */
    static boolean areIsomorphicStrings(String s, String t) {
        Map<Character, Integer> mapOne = new HashMap<>();
        Map<Character, Integer> mapTwo = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            mapOne.put(s.charAt(i), mapOne.getOrDefault(s.charAt(i), 0) + 1);
            mapTwo.put(t.charAt(i), mapTwo.getOrDefault(t.charAt(i), 0) + 1);
        }
        return mapOne.values().stream().allMatch(mapTwo::containsValue);
    }

    /**
     * Zadanie:
     * Sprawdź, czy ciąg słów pasuje do wzorca literowego.
     * <p>
     * Przykład: pattern = "abba", s = "dog cat cat dog" → true
     */
    static boolean wordPatternFollows(String pattern, String s) {
        LinkedHashMap<String, String> mapOne = new LinkedHashMap<>();
        List<String> sList = Arrays.asList(s.split(" "));

        if (sList.size() != pattern.length()) {
            return false;
        }

        for (int i = 0; i < sList.size(); i++) {
            if (mapOne.containsKey(sList.get(i))) {
                if (!mapOne.get(sList.get(i)).equals(String.valueOf(pattern.charAt(i)))) {
                    return false;
                }
            } else {
                mapOne.put(sList.get(i), mapOne.getOrDefault(sList.get(i), String.valueOf(pattern.charAt(i))));
            }
        }

        Set<String> valueSet = new HashSet<>(mapOne.values());
        return valueSet.size() == mapOne.size();
    }

    /**
     * Napisz metodę, która zwraca pierwszą unikalną (czyli niepowtarzającą się) liczbę
     * w tablicy liczb całkowitych. Jeśli żadna taka liczba nie istnieje, zwróć Integer.MIN_VALUE.
     *
     * Przykład:
     * Input: [4, 5, 1, 2, 0, 4]
     * Output: 5
     *
     * Input: [1, 2, 2, 1]
     * Output: Integer.MIN_VALUE
     */
    static int findFirstUnique(int[] nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * Napisz metodę, która znajduje długość najkrótszego podciągu (ciągłych elementów)
     * w tablicy liczb całkowitych, który zawiera **wszystkie unikalne liczby** z tej tablicy.
     *
     * Przykład:
     * Input: [1, 2, 2, 3, 1, 2, 4, 3]
     * Unikalne liczby: [1, 2, 3, 4]
     * Najkrótszy podciąg zawierający 1,2,3,4 to: [3,1,2,4] → długość = 4
     *
     * Input: [7, 5, 2, 7, 2, 7, 4, 7]
     * Unikalne liczby: [2, 4, 5, 7]
     * Najkrótszy taki podciąg: [5,2,7,2,7,4] → długość = 6
     *
     * Jeśli tablica jest pusta, zwróć 0.
     */
    public class SubarrayFinder {
        public static int shortestSubarrayWithAllUniques(int[] nums) {
            if (nums.length == 0) return 0;
            Map<Integer, Integer> map = new LinkedHashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int left = 0;
            int right = 0;
            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[i]) > 1) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                } else {
                    left = i;
                    break;
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[nums.length - i - 1]) > 1) {
                    map.put(nums[nums.length - i - 1], map.get(nums[nums.length - i - 1]) - 1);
                } else {
                    right = nums.length - i - 1;
                    break;
                }
            }

            return right - left + 1;
        }
    }


    /**
     * Napisz metodę, która zwraca maksymalną liczbę różnych (unikalnych) liczb
     * występujących w jakimkolwiek podciągu (ciągłym fragmencie) tablicy o długości dokładnie `k`.
     *
     * Przykład:
     * Input: nums = [1, 2, 1, 3, 4, 2, 3], k = 4
     * Okna:
     * - [1,2,1,3] → 3 unikalne
     * - [2,1,3,4] → 4 unikalne ✅
     * - [1,3,4,2] → 4 unikalne
     * - [3,4,2,3] → 3 unikalne
     * Output: 4
     *
     * Input: nums = [1, 1, 1, 1], k = 2
     * Output: 1
     *
     * Uwagi:
     * - Jeśli k > nums.length → zwróć 0
     * - Jeśli k <= 0 → zwróć 0
     */
    static int maxUniqueInWindow(int[] nums, int k) {
        int result = 0;

        int left = 0;
        int right = k;
        while (right < nums.length) {
            HashSet<Integer> set = new HashSet<>();
            int tempResult = k;
            for (int i = left; i < right; i++) {
                if (set.contains(nums[i])) {
                    tempResult--;
                } else {
                    set.add(nums[i]);
                }
            }
            result = Math.max(result, tempResult);
            left++;
            right++;
        }
        return result;
    }
}
