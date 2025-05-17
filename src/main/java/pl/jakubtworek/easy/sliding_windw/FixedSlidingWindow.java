package pl.jakubtworek.easy.sliding_windw;

import java.util.HashMap;
import java.util.Map;

class FixedSlidingWindow {

    /**
       Algorytm countAnagramSubstrings:

       Zadanie:
       Zlicza liczbę podciągów w napisie `text`, które są anagramami napisu `pattern`.
       Anagram to dowolna permutacja liter wzorca (np. dla "abc": "bac", "cab", itp.).

       Działanie:
       1. Tworzymy mapę `patternFreq`, która przechowuje liczbę wystąpień każdego znaku w `pattern`.
       2. Tworzymy drugą mapę `windowFreq`, która śledzi znaki w aktualnym oknie długości `pattern.length()`.
       3. Przesuwamy okno przez `text` i aktualizujemy `windowFreq`:
          - Dodajemy nowy znak (wchodzący z prawej strony).
          - Usuwamy znak, który opuszcza okno (z lewej strony).
          - Jeśli obie mapy są równe — inkrementujemy wynik.

       Złożoność:
       - Czasowa: O(n), gdzie n = długość `text`
         - Mimo porównywania map, alfabet jest ograniczony (np. 26 liter), więc porównanie to O(1)
       - Pamięciowa: O(1)
         - Obie mapy mają ograniczony rozmiar (liczba unikalnych znaków)
     */
    static int countAnagramSubstrings(String text, String pattern) {
        if (text == null || pattern == null || text.length() < pattern.length() || pattern.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> patternFreq = new HashMap<>();
        Map<Character, Integer> windowFreq = new HashMap<>();
        int patternLength = pattern.length();
        int count = 0;

        // Zliczamy częstotliwości w pattern
        for (char c : pattern.toCharArray()) {
            patternFreq.put(c, patternFreq.getOrDefault(c, 0) + 1);
        }

        // Inicjalizacja pierwszego okna
        for (int i = 0; i < patternLength; i++) {
            char c = text.charAt(i);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
        }
        if (patternFreq.equals(windowFreq)) {
            count++;
        }

        // Przesuwamy okno
        for (int i = patternLength; i < text.length(); i++) {
            char charToAdd = text.charAt(i);
            char charToRemove = text.charAt(i - patternLength);

            // Dodajemy nowy znak
            windowFreq.put(charToAdd, windowFreq.getOrDefault(charToAdd, 0) + 1);

            // Usuwamy stary znak
            windowFreq.put(charToRemove, windowFreq.get(charToRemove) - 1);
            if (windowFreq.get(charToRemove) == 0) {
                windowFreq.remove(charToRemove);
            }

            // Porównujemy mapy
            if (patternFreq.equals(windowFreq)) {
                count++;
            }
        }

        return count;
    }
}