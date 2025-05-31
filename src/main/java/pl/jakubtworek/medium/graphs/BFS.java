package pl.jakubtworek.medium.graphs;

import java.util.*;

class BFS {

    /**
     * Oblicza minimalny czas potrzebny na zainfekowanie wszystkich zdrowych komórek (wartość 1)
     * w dwuwymiarowej macierzy. Infekcja rozprzestrzenia się z zainfekowanych komórek (wartość 2)
     * w czterech kierunkach (góra, dół, lewo, prawo), rozprzestrzeniając się w każdej jednostce czasu.
     *
     * Komórki z wartością 0 są puste i nie biorą udziału w infekcji ani nie mogą być zainfekowane.
     * Jeśli nie da się zainfekować wszystkich zdrowych komórek (np. są odcięte przez zera), metoda zwraca -1.
     *
     * Algorytm:
     * 1. Wyszukuje wszystkie zainfekowane komórki (wartość 2) i dodaje je do kolejki (BFS).
     * 2. Zlicza wszystkie zdrowe komórki (wartość 1).
     * 3. Rozpoczyna BFS: w każdej jednostce czasu infekuje sąsiadujące zdrowe komórki.
     * 4. Jeśli po zakończeniu BFS pozostały zdrowe komórki — zwraca -1. W przeciwnym razie zwraca liczbę kroków.
     *
     * Złożoność czasowa: O(m * n) — każda komórka jest odwiedzona najwyżej raz.
     * Złożoność pamięciowa: O(m * n) — na potrzeby kolejki BFS i przechowywania macierzy.
     *
     */
    static int matrixInfection(List<List<Integer>> matrix) {
        if (matrix == null || matrix.isEmpty()) return 0;

        int rows = matrix.size();
        int cols = matrix.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        int healthy = 0;

        // 1. Zlicz zdrowe komórki i dodaj zainfekowane do kolejki
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val = matrix.get(i).get(j);
                if (val == 2) {
                    queue.offer(new int[]{i, j});
                } else if (val == 1) {
                    healthy++;
                }
            }
        }

        if (healthy == 0) return 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        // 2. BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infectedThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for (int[] dir : dirs) {
                    int ni = pos[0] + dir[0];
                    int nj = pos[1] + dir[1];

                    if (ni >= 0 && nj >= 0 && ni < rows && nj < cols && matrix.get(ni).get(nj) == 1) {
                        matrix.get(ni).set(nj, 2);
                        queue.offer(new int[]{ni, nj});
                        healthy--;
                        infectedThisRound = true;
                    }
                }
            }

            if (infectedThisRound) minutes++;
        }

        return healthy == 0 ? minutes : -1;
    }

    /**
     * Oblicza długość najkrótszej sekwencji transformacji od słowa początkowego `start`
     * do słowa docelowego `end`, tak aby:
     * - Każda transformacja zmienia dokładnie jeden znak.
     * - Każde pośrednie słowo musi należeć do słownika `dictionary`.
     *
     * Używany algorytm:
     * - BFS (Breadth-First Search), traktując każde słowo jako wierzchołek grafu,
     *   a połączenia między słowami różniącymi się jednym znakiem jako krawędzie.
     *
     * Złożoność czasowa: O(N * L²), gdzie:
     *   - N to liczba słów w słowniku,
     *   - L to długość każdego słowa.
     *
     * Złożoność pamięciowa: O(N)
     *
     */
    static int shortestTransformationSequence(String start, String end, List<String> dictionary) {
        Set<String> wordSet = new HashSet<>(dictionary);
        if (!wordSet.contains(end)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int steps = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String word = queue.poll();
                if (word.equals(end)) return steps;

                for (String neighbor : getNeighbors(word, wordSet)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            steps++;
        }

        return 0;
    }

    private static List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = old;
        }

        return neighbors;
    }
}
