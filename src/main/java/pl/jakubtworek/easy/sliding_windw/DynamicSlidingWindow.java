package pl.jakubtworek.easy.sliding_windw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DynamicSlidingWindow {

    /**
       Algorytm: lengthOfLongestUniqueSubstring

       Zadanie:
       Znaleźć długość najdłuższego podciągu w danym stringu `s`, w którym wszystkie znaki są unikalne (bez powtórzeń).

       Działanie:
       - Używamy techniki przesuwającego się okna (sliding window).
       - Prowadzimy dwa wskaźniki `left` i `right`, które definiują aktualne okno w stringu.
       - Gdy napotkamy duplikat znaku (już w zbiorze), usuwamy znaki od lewej strony, aż znów okno zawiera tylko unikalne znaki.
       - Na bieżąco aktualizujemy maksymalny rozmiar okna.

       Złożoność:
       - Czasowa: O(n), ponieważ każdy znak jest dodany i usunięty co najwyżej raz.
       - Pamięciowa: O(m), gdzie m to liczba różnych znaków (alfabet).
     */
    static int lengthOfLongestUniqueSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (seen.contains(c)) {
                seen.remove(s.charAt(left++));
            }
            seen.add(c);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
       Algorytm: lengthOfLongestUniformSubstringAfterReplacement

       Zadanie:
       Dla danego stringu `s`, znaleźć długość najdłuższego podciągu złożonego z jednego znaku (np. "AAA..."),
       który można uzyskać po co najwyżej `k` zamianach znaków.

       Działanie:
       - Dla każdego okna przesuwającego się w stringu:
         - Liczymy wystąpienia każdego znaku w tym oknie (mapa `freqs`).
         - Obliczamy ile znaków musielibyśmy zmienić, by cały substring był jednolity (`windowSize - highestFreq`).
         - Jeśli liczba potrzebnych zmian > `k`, przesuwamy lewą granicę okna.
         - Aktualizujemy maksymalną długość poprawnego okna.

       Złożoność:
       - Czasowa: O(n), gdzie n to długość stringa — każde okno przesuwamy tylko raz.
       - Pamięciowa: O(1) — tylko 26 liter alfabetu, stała liczba unikalnych znaków.
     */
    static int lengthOfLongestUniformSubstringAfterReplacement(String s, int k) {
        Map<Character, Integer> freqs = new HashMap<>();
        int left = 0, maxLength = 0, highestFreq = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
            highestFreq = Math.max(highestFreq, freqs.get(c));

            int windowSize = right - left + 1;
            int replacementsNeeded = windowSize - highestFreq;

            if (replacementsNeeded > k) {
                char leftChar = s.charAt(left++);
                freqs.put(leftChar, freqs.get(leftChar) - 1);
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
