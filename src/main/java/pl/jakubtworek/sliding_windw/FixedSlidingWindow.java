package pl.jakubtworek.sliding_windw;

import java.util.HashMap;
import java.util.Map;

public class FixedSlidingWindow {
    /**
    Algorytm substringAnagrams:

    Zadanie:
    Zlicza, ile anagramów napisu `t` znajduje się jako podciąg w napisie `s`.
    Anagram to dowolna permutacja liter — np. "abc" → "bca", "cab", itp.

    Działanie:
    - Tworzymy mapę `expectedFreq`, która zawiera liczbę wystąpień każdego znaku w `t`.
    - Przesuwamy po `s` okno o długości `t.length()` (sliding window).
    - Dla każdego okna tworzymy aktualną mapę znaków `windowFreq`.
    - Jeżeli `windowFreq` równa się `expectedFreq` — oznacza to, że okno zawiera anagram `t`.

    Kroki:
    1. Obsługa przypadków brzegowych:
       - Jeśli `s` lub `t` jest `null`, pusty, lub `t` dłuższe niż `s` → wynik to 0.
    2. Inicjalizacja pierwszego okna:
       - Zliczamy częstotliwość pierwszych `t.length()` znaków w `s`.
    3. Przesuwamy okno po `s`:
       - Dodajemy nowy znak na końcu okna.
       - Usuwamy pierwszy znak z poprzedniego okna.
       - Porównujemy mapy – jeśli są równe, zwiększamy licznik wyników.

    Złożoność:
    - Czasowa: O(n), gdzie n = długość `s`
      - Mapy mają stałą wielkość (maks. 26 znaków dla liter), więc porównywanie map to O(1)
    - Pamięciowa: O(1)
      - Używamy dwóch map o ograniczonym rozmiarze (litery alfabetu)

    Uwagi:
    - Używamy HashMap i metody `equals()` do porównania map znaków.
    - Algorytm nie sortuje — działa efektywnie na zasadzie przesuwania okna (sliding window).
    */
    static int substringAnagrams(String s, String t) {
        if (s == null || t == null || s.length() < t.length() || t.isEmpty()) {
            return 0;
        }

        int result = 0;
        Map<Character, Integer> expectedFreq = new HashMap<>();
        Map<Character, Integer> windowFreq = new HashMap<>();

        for (char c : t.toCharArray()) {
            expectedFreq.put(c, expectedFreq.getOrDefault(c, 0) + 1);
        }

        int windowSize = t.length();

        for (int i = 0; i < windowSize; i++) {
            char c = s.charAt(i);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
        }

        if (expectedFreq.equals(windowFreq)) {
            result++;
        }

        for (int right = windowSize; right < s.length(); right++) {
            char newChar = s.charAt(right);
            char oldChar = s.charAt(right - windowSize);

            windowFreq.put(newChar, windowFreq.getOrDefault(newChar, 0) + 1);

            if (windowFreq.get(oldChar) == 1) {
                windowFreq.remove(oldChar);
            } else {
                windowFreq.put(oldChar, windowFreq.get(oldChar) - 1);
            }

            if (expectedFreq.equals(windowFreq)) {
                result++;
            }
        }

        return result;
    }
}