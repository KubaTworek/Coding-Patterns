package pl.jakubtworek.medium.tries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Traversal {

    /**
     * Znajduje wszystkie słowa z listy `words`, które można utworzyć na planszy `board`.
     * Można poruszać się w 4 kierunkach (góra, dół, lewo, prawo), bez odwiedzania tej samej komórki więcej niż raz.
     *
     * Algorytm:
     * - Buduje Trie z listy słów, by efektywnie przerywać ścieżki.
     * - Dla każdej komórki planszy wykonuje DFS.
     * - Przerywa ścieżkę, jeśli prefiks nie występuje w Trie.
     *
     * Złożoność czasowa: O(M * N * 4^L), gdzie:
     * - M x N — rozmiar planszy
     * - L — maksymalna długość słowa
     *
     * Złożoność pamięciowa: O(K), gdzie K to całkowita liczba znaków we wszystkich słowach
     */
    static List<String> findAllWordsOnBoard(List<List<Character>> board, List<String> words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> result = new HashSet<>();
        int rows = board.size(), cols = board.getFirst().size();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                dfs(board, i, j, "", trie, result, visited);
            }
        }

        return new ArrayList<>(result);
    }

    private static void dfs(List<List<Character>> board, int i, int j, String path, Trie trie, Set<String> result, boolean[][] visited) {
        int rows = board.size(), cols = board.get(0).size();
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j]) return;

        path += board.get(i).get(j);
        if (!trie.startsWith(path)) return;

        if (trie.search(path)) {
            result.add(path);
        }

        visited[i][j] = true;

        dfs(board, i + 1, j, path, trie, result, visited);
        dfs(board, i - 1, j, path, trie, result, visited);
        dfs(board, i, j + 1, path, trie, result, visited);
        dfs(board, i, j - 1, path, trie, result, visited);

        visited[i][j] = false;
    }
}
