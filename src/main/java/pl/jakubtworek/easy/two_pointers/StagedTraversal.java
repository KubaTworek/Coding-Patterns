package pl.jakubtworek.easy.two_pointers;

class StagedTraversal {
    /**
       Algorytm nextLexicographicalSequence:

       Zadanie:
       Dla podanego ciągu znaków `s`, znajduje jego następną permutację leksykograficzną.
       Jeśli `s` jest już największą możliwą permutacją, zwraca najmniejszą (czyli sortowaną rosnąco).

       Złożoność:
       - Czasowa: O(n), gdzie n = długość ciągu (maksymalnie jedno przejście od końca + jedno sortowanie/odwrócenie końcówki).
       - Pamięciowa: O(n), ze względu na zamianę ciągu w tablicę znaków.
     */
    public static String nextLexicographicalSequence(String s) {
        char[] letters = s.toCharArray();

        // Krok 1: Znajdź pivot (czyli pierwszą literę od końca, która jest mniejsza od swojej prawej sąsiadki)
        int pivot = letters.length - 2;
        while (pivot >= 0 && letters[pivot] >= letters[pivot + 1]) {
            pivot--;
        }

        // Krok 2: Jeśli nie znaleziono pivotu — zwróć posortowaną wersję (najmniejsza permutacja)
        if (pivot == -1) {
            reverse(letters, 0, letters.length - 1);
            return new String(letters);
        }

        // Krok 3: Znajdź najmniejszy większy znak od pivotu (na prawo od niego)
        int successor = letters.length - 1;
        while (letters[successor] <= letters[pivot]) {
            successor--;
        }

        // Krok 4: Zamień pivot z sukcesorem
        swap(letters, pivot, successor);

        // Krok 5: Odwróć końcówkę po pivocie
        reverse(letters, pivot + 1, letters.length - 1);

        return new String(letters);
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}
