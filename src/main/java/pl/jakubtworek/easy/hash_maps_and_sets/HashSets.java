package pl.jakubtworek.easy.hash_maps_and_sets;

import java.util.*;

class HashSets {

    /**
       Algorytm isValidSudoku:

       Zadanie:
       Sprawdza, czy dana plansza Sudoku (9x9) jest poprawna zgodnie z zasadami gry:
       - W każdym wierszu, kolumnie i podplanszy 3x3 liczby 1–9 mogą występować tylko raz.
       - Zera reprezentują puste pola i są ignorowane przy walidacji.

       Działanie:
       - Tworzymy 3 listy po 9 zbiorów:
         - rows[i] — zbiór liczb w wierszu i
         - cols[j] — zbiór liczb w kolumnie j
         - boxes[k] — zbiór liczb w podplanszy (boxie) k
       - Dla każdej komórki (i, j) sprawdzamy, czy wartość:
         - Już występuje w danym wierszu, kolumnie lub boxie → jeśli tak: niepoprawna
         - Jeśli nie: dodajemy ją do odpowiednich zbiorów

       Złożoność:
       - Czasowa: O(81) = O(1)
         • 9x9 plansza jest stała — stała liczba operacji
       - Pamięciowa: O(1)
         • Przechowujemy 3×9 zbiorów, a każdy z nich maksymalnie 9 elementów
     */
    static boolean isValidSudoku(List<List<Integer>> board) {
        List<Set<Integer>> rows = new ArrayList<>();
        List<Set<Integer>> cols = new ArrayList<>();
        List<Set<Integer>> boxes = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board.get(i).get(j);
                if (num == 0) continue;

                int boxIndex = (i / 3) * 3 + (j / 3);

                if (rows.get(i).contains(num) ||
                        cols.get(j).contains(num) ||
                        boxes.get(boxIndex).contains(num)) {
                    return false;
                }

                rows.get(i).add(num);
                cols.get(j).add(num);
                boxes.get(boxIndex).add(num);
            }
        }

        return true;
    }

    /**
       Algorytm applyZeroStriping:

       Zadanie:
       Dla dowolnej prostokątnej macierzy (o dowolnej liczbie wierszy i kolumn),
       jeśli jakikolwiek element to 0, cały jego wiersz i kolumna zostają ustawione na 0.

       Przykład:
       Input:  [[1, 2, 3],       Output: [[0, 2, 3],
                [4, 0, 6],                [0, 0, 0],
                [7, 8, 9]]                [0, 8, 9]]

       Działanie:
       - Pierwsza pętla: zbieramy indeksy wierszy i kolumn, które zawierają 0.
       - Druga pętla:
         • Ustawiamy całe wiersze z `zeroRows` na zera.
         • Ustawiamy wszystkie `zeroCols` w pozostałych wierszach na 0,
           o ile taka kolumna istnieje (ochrona przed IndexOutOfBounds).

       Złożoność:
       - Czasowa: O(n × m)
         • Musimy przejść przez każdą komórkę maksymalnie 2 razy.
       - Pamięciowa: O(n + m)
         • Przechowujemy zbiory indeksów wierszy i kolumn zawierających 0.

       Uzasadnienie:
       - Nie możemy modyfikować macierzy podczas pierwszego przejścia — wpływałoby to na wynik.
       - Dlatego robimy dwa przebiegi: najpierw oznaczamy, potem modyfikujemy.
     */
    static void applyZeroStriping(List<List<Integer>> matrix) {
        int rowCount = matrix.size();
        if (rowCount == 0) return;

        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        // Faza 1: wykryj wiersze i kolumny z zerem
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // Faza 2: wyzeruj wskazane wiersze
        for (int row : zeroRows) {
            for (int j = 0; j < matrix.get(row).size(); j++) {
                matrix.get(row).set(j, 0);
            }
        }

        // Faza 3: wyzeruj wskazane kolumny
        for (int i = 0; i < rowCount; i++) {
            for (int col : zeroCols) {
                if (col < matrix.get(i).size()) {
                    matrix.get(i).set(col, 0);
                }
            }
        }
    }
}
