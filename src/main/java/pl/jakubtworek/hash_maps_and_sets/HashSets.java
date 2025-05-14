package pl.jakubtworek.hash_maps_and_sets;

import java.util.*;
import java.util.stream.IntStream;

public class HashSets {

    /**
    * Algorytm verifySudokuBoard:

    * Zadanie:
    * Sprawdza, czy dana plansza Sudoku (9x9) jest poprawna — tzn. nie zawiera duplikatów
    * liczb od 1 do 9 w:
    * - każdym wierszu,
    * - każdej kolumnie,
    * - każdej z 9 podplansz (boxów) 3x3.

    * Zasady:
    * - Puste pola są oznaczone jako 0 i są ignorowane przy sprawdzaniu.
    * - Każda liczba (1–9) może wystąpić co najwyżej raz w każdym rzędzie, kolumnie i boxie.

    * Działanie:
    * - Tworzymy 3 listy po 9 zbiorów (HashSet):
    *   - rows[i] – liczby występujące w i-tym wierszu,
    *   - columns[j] – liczby w j-tej kolumnie,
    *   - boxes[k] – liczby w k-tym boxie 3x3.
    * - Dla każdej komórki `(i, j)`:
    *   - Jeżeli wartość ≠ 0, sprawdzamy czy dana liczba już występuje:
    *     - w odpowiednim wierszu `rows[i]`
    *     - w kolumnie `columns[j]`
    *     - w boxie `(i / 3) * 3 + (j / 3)` → indeks boxa
    *   - Jeśli tak — plansza nie jest poprawna.
    *   - Jeśli nie — dodajemy liczbę do odpowiednich zbiorów.

    * Złożoność obliczeniowa:
    * - Czasowa: O(81) = O(1)
    *   - Stała liczba komórek (9x9), każda sprawdzana raz.
    * - Pamięciowa: O(1)
    *   - Stałe struktury: 3 listy po 9 zbiorów.

    * Uwagi:
    * - Metoda nie sprawdza, czy plansza jest w pełni wypełniona – tylko poprawność aktualnego stanu.
    */
    static boolean verifySudokuBoard(List<List<Integer>> board) {
        List<Set<Integer>> rows = new ArrayList<>();
        List<Set<Integer>> columns = new ArrayList<>();
        List<Set<Integer>> boxes = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            columns.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board.get(i).get(j);
                if (num == 0) continue;

                int boxIndex = (i / 3) * 3 + (j / 3);

                if (rows.get(i).contains(num)) return false;
                if (columns.get(j).contains(num)) return false;
                if (boxes.get(boxIndex).contains(num)) return false;

                rows.get(i).add(num);
                columns.get(j).add(num);
                boxes.get(boxIndex).add(num);
            }
        }

        return true;
    }

    /**
    * Algorytm zeroStriping:

    * Zadanie:
    * Dla danej prostokątnej macierzy (n wierszy, m kolumn), zamienia wszystkie wartości
    * na 0 w tych wierszach i kolumnach, które zawierają co najmniej jedno zero.

    * Działa również dla macierzy niesymetrycznych (np. 5x9, 3x4), a także dla przypadków,
    * gdy niektóre wiersze mają różną długość (niestandardowe dane).

    * Działanie:
    * 1. W pierwszej pętli zbieramy indeksy wierszy i kolumn, które zawierają zero.
    *    - Używamy do tego dwóch zbiorów: zeroRows i zeroCols.
    * 2. W drugiej fazie:
    *    - Każdy wiersz z `zeroRows` w całości ustawiamy na zera.
    *    - Dla każdej kolumny z `zeroCols` ustawiamy wszystkie elementy w tej kolumnie na 0
    *      w tych wierszach, które mają tę kolumnę (ochrona przed IndexOutOfBounds).

    * Złożoność:
    * - Czasowa: O(n × m) — dwa przejścia przez macierz
    * - Pamięciowa: O(n + m) — dodatkowe zbiory dla indeksów wierszy i kolumn z zerami
    */
    static void zeroStriping(List<List<Integer>> matrix) {
        int rowCount = matrix.size();
        if (rowCount == 0) return;

        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        for (int row : zeroRows) {
            for (int j = 0; j < matrix.get(row).size(); j++) {
                matrix.get(row).set(j, 0);
            }
        }

        for (int i = 0; i < rowCount; i++) {
            for (int col : zeroCols) {
                if (col < matrix.get(i).size()) {
                    matrix.get(i).set(col, 0);
                }
            }
        }
    }
}
