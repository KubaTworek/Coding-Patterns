package pl.jakubtworek.easy.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Exercise {
    /**
     * Zadanie:
     * Usuń zduplikowane elementy z posortowanej listy liczb całkowitych.
     * Modyfikuj listę w miejscu i zwróć listę bez duplikatów.
     *
     * Przykład:
     * Input: [1, 1, 2, 2, 3] → Output: [1, 2, 3]
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1) — pomijając wynik
     */
    static List<Integer> removeDuplicates(List<Integer> sorted) {
        List<Integer> result = new ArrayList<>(sorted);
        int left = 0;
        int right = 1;

        while (right < result.size()) {
            if (Objects.equals(result.get(right), result.get(left))) {
                result.remove(left);
            } else {
                left++;
                right++;
            }
        }
        return result;
    }

    /**
     * Zadanie:
     * Sprawdź, czy istnieją dwie liczby w posortowanej liście takie, że
     * jedna jest dokładnie podwójnością drugiej (i ≠ j).
     *
     * Przykład:
     * Input: [1, 2, 3, 6] → Output: true (bo 3 * 2 == 6)
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1)
     */
    static boolean hasDoubleValuePair(List<Integer> nums) {
        int left = 0;
        int right = 1;

        while (right < nums.size()) {
            if (nums.get(right) == nums.get(left) * 2) {
                return true;
            } else if (nums.get(right) > nums.get(left) * 2) {
                left++;
            } else if (nums.get(right) < nums.get(left) * 2) {
                right++;
            }
        }
        return false;
    }

    /**
     * Zadanie:
     * Zlicz, ile istnieje unikalnych par liczb w liście `nums`, takich że
     * różnica między nimi wynosi dokładnie `k` (czyli |a - b| == k).
     *
     * Przykład:
     * Input: [1, 7, 5, 9, 2, 12, 3], k = 2 → Output: 4
     * (bo pary: (1,3), (3,5), (5,7), (7,9))
     *
     * Złożoność:
     * - Czasowa: O(n log n) (przez sortowanie i przesuwanie wskaźników)
     * - Pamięciowa: O(1)
     */
    static int countPairsWithDifference(List<Integer> nums, int k) {
        int left = 0;
        int right = 1;
        int result = 0;
        List<Integer> list = new ArrayList<>(nums);
        list.sort(Integer::compare);

        while (right < list.size() && left <= right) {
            if (Math.abs(list.get(right) - list.get(left)) == k) {
                result++;
                if (right == list.size() - 1) {
                    left++;
                } else {
                    right++;
                }
            } else if (Math.abs(list.get(right) - list.get(left)) > k) {
                left++;
            } else if (Math.abs(list.get(right) - list.get(left)) < k) {
                right++;
            }
        }
        return result;
    }

    /**
     * Zadanie:
     * Sprawdź, czy z liter w ciągu `s` da się ułożyć palindrom (permutacja).
     *
     * Przykład:
     * Input: "civic" → true
     * Input: "ivicc" → true
     * Input: "hello" → false
     *
     * Warunek:
     * - Maksymalnie jeden znak może mieć nieparzystą liczbę wystąpień.
     *
     * Złożoność:
     * - Czasowa: O(n)
     * - Pamięciowa: O(1) dla alfabetu ASCII
     */
    static boolean canFormPalindrome(String s) {
        int one = 0;
        int left = 0;
        int right = 1;

        while (left < s.length() / 2 && left != right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right = left + 1;
            } else if (right == s.length()-1) {
                one++;
                left++;
                right = left + 1;
            } else {
                right++;
            }
            if (one > 1) {
                return false;
            }
        }
        return true;
    }

    /*
     * Polecenie:
     * Dana jest niemalejąco posortowana tablica liczb całkowitych `nums` oraz liczba całkowita `target`.
     * Znajdź dwie liczby w tablicy, których suma jest równa `target`, i zwróć ich indeksy w postaci tablicy int[2].
     * Gwarantuje się, że dokładnie jedna para liczb spełnia warunek.
     * Należy zaimplementować rozwiązanie w czasie O(n), używając techniki two pointers.
     *
     * Przykład:
     * Input: nums = [1, 2, 4, 6, 10], target = 8
     * Output: [1, 3]  // ponieważ nums[1] + nums[3] == 2 + 6 == 8
     */
    static int[] twoSum(int[] nums, int target) {
        int leftPointer = 0;
        int rightPointer = 1;
        while (rightPointer != nums.length && leftPointer != nums.length) {
            int leftValue = nums[leftPointer];
            int rightValue = nums[rightPointer];
            int sum = leftValue + rightValue;

            if (sum > target) {
                leftPointer--;
            } else if (sum < target) {
                leftPointer++;
                rightPointer++;
            } else {
                return new int[] {leftPointer, rightPointer};
            }
        }
        return new int[] {-1, -1};
    }

    /*
     * Polecenie:
     * Dana jest tablica liczb całkowitych `nums`. Znajdź wszystkie unikalne trójki (i, j, k),
     * takie że: i != j, i != k, j != k i nums[i] + nums[j] + nums[k] == 0.
     *
     * Zwróć listę trójek liczb (nie indeksów), przy czym:
     * - Każda trójka powinna być posortowana w kolejności niemalejącej.
     * - Wynik nie może zawierać duplikatów (czyli [−1, 0, 1] i [0, −1, 1] to to samo).
     *
     * Oczekiwany czas: O(n^2) przy użyciu sortowania i two pointers.
     *
     * Przykład:
     * Input: nums = [-1, 0, 1, 2, -1, -4]
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     */
    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int firstPointer = 0;
        int leftPointer = 1;
        int rightPointer = 2;
        while (!(firstPointer == nums.length - 2 && leftPointer == nums.length - 1 && rightPointer == nums.length)) {
            int firstValue = nums[firstPointer];
            int leftValue = nums[leftPointer];
            int rightValue = nums[rightPointer];
            int sum = firstValue + leftValue + rightValue;

            if (sum == 0) result.add(new ArrayList<>(Arrays.asList(firstValue, leftValue, rightValue)));

            if (rightPointer == nums.length - 1 && leftPointer != nums.length - 2) {
                leftPointer++;
            }

            if (rightPointer != nums.length - 1 && sum <= 0) {
                rightPointer++;
            }

            if (rightPointer == nums.length - 1 && leftPointer == nums.length - 2) {
                firstPointer++;
                leftPointer = firstPointer + 1;
                rightPointer = firstPointer + 2;
            }
        }

        return result;
    }

    /*
     * Polecenie:
     * Dana jest tablica dodatnich liczb całkowitych `nums` i liczba całkowita `target`.
     * Znajdź **minimalną długość ciągłego podciągu** (podtablicy), którego suma jest większa lub równa `target`.
     * Zwróć długość tej podtablicy. Jeśli nie istnieje taka podtablica — zwróć 0.
     *
     * Oczekiwany czas: O(n), użyj techniki two pointers (sliding window).
     *
     * Przykład:
     * Input: nums = [2, 3, 1, 2, 4, 3], target = 7
     * Output: 2  // bo [4, 3] to najkrótsza podtablica o sumie ≥ 7
     *
     * Input: nums = [1, 1, 1, 1, 1], target = 11
     * Output: 0
     */
    static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 1;
        int result = Integer.MAX_VALUE;

        while (right < nums.length) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }

            if (sum < target) {
                right++;
            } else {
                result = Math.min(result, right - left + 1);
                left++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
