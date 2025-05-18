package pl.jakubtworek.easy.binary_search;

import java.util.List;

class Matrix {

    /**
       Algorytm: searchTargetInSortedMatrix

       Zadanie:
       Dla posortowanej dwuwymiarowej macierzy `matrix`, gdzie:
       - każda z wierszy jest posortowana rosnąco,
       - pierwszy element każdego wiersza jest większy od ostatniego elementu poprzedniego wiersza,

       Znajdź, czy dana liczba `target` istnieje w tej macierzy.

       Działanie:
       - Traktujemy macierz jako jedną posortowaną tablicę 1D (indeksy od 0 do m * n - 1).
       - Wykonujemy klasyczne wyszukiwanie binarne:
         • przeliczamy indeks "środkowy" na współrzędne wiersza i kolumny: r = mid / n, c = mid % n,
         • porównujemy `matrix[r][c]` z `target`.
       - Jeśli trafimy w cel – zwracamy true, inaczej odpowiednio przesuwamy zakres binarnego przeszukiwania.

       Złożoność:
       - Czasowa: O(log(m * n)) = O(log n) dla n = liczba wszystkich elementów
         • Klasyczne przeszukiwanie binarne po jednej wymiarze.
       - Pamięciowa: O(1)
         • Nie używamy dodatkowej pamięci – operujemy na indeksach.
     */
    public static boolean searchTargetInSortedMatrix(List<List<Integer>> matrix, int target) {
        if (matrix == null || matrix.isEmpty() || matrix.getFirst().isEmpty()) return false;

        int rowCount = matrix.size();
        int colCount = matrix.getFirst().size();
        int left = 0;
        int right = rowCount * colCount - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int row = mid / colCount;
            int col = mid % colCount;
            int value = matrix.get(row).get(col);

            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
