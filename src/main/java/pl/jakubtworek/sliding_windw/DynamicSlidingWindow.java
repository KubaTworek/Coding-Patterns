package pl.jakubtworek.sliding_windw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynamicSlidingWindow {
    static int longestSubstringWithUniqueCharacters(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();

        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            } else {
                set.add(s.charAt(right));
                right++;
            }
            if (set.size() > max) {
                max = set.size();
            }
        }
        return max;
    }

    static int longestUniformSubstringAfterReplacement(String s, int k) {
        System.out.println(s + " " + k);
        int max = 0;
        int left = 0;
        int highestFreq = 0;
        Map<Character, Integer> freqs = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            freqs.put(currentChar, freqs.getOrDefault(currentChar, 0) + 1);

            highestFreq = Math.max(highestFreq, freqs.get(currentChar));

            int windowSize = right - left + 1;
            int numCharsToReplace = windowSize - highestFreq;

            if (numCharsToReplace > k) {
                char leftChar = s.charAt(left);
                freqs.put(leftChar, freqs.get(leftChar) - 1);
                left++;
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }

}
