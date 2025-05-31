package pl.jakubtworek.medium.graphs;

import java.util.*;

class DFS {

    /**
     * Tworzy głęboką kopię nieskierowanego grafu zaczynając od danego węzła (root).
     *
     * Algorytm:
     * - Używa DFS do odwiedzenia każdego węzła.
     * - Dla każdego węzła tworzy jego kopię (jeśli jeszcze jej nie utworzono) i zapisuje ją w mapie odwzorowań.
     * - Następnie rekurencyjnie kopiuje sąsiadów.
     *
     * Złożoność czasowa: O(N + E)
     * - N = liczba węzłów
     * - E = liczba krawędzi
     *
     * Złożoność pamięciowa: O(N)
     */
    static GraphNode<Integer> copy(GraphNode<Integer> root) {
        if (root == null) return null;
        return dfs(root, new HashMap<>());
    }

    private static GraphNode<Integer> dfs(GraphNode<Integer> root, Map<GraphNode<Integer>, GraphNode<Integer>> cloneMap) {
        if (cloneMap.containsKey(root)) return cloneMap.get(root);
        GraphNode<Integer> cloned = new GraphNode<>(root.getValue());
        cloneMap.put(root, cloned);
        for (GraphNode<Integer> neighbour : root.getNeighbors()) {
            GraphNode<Integer> clonedNeighbour = dfs(neighbour, cloneMap);
            cloned.addNeighbor(clonedNeighbour);
        }
        return cloned;
    }

    /**
     * Zlicza liczbę "wysp" w macierzy binarnej, gdzie 1 oznacza ląd, a 0 wodę.
     *
     * Definicja wyspy:
     * - Wyspa to zbiór połączonych sąsiadujących komórek o wartości 1 (tylko 4 kierunki: góra/dół/lewo/prawo).
     *
     * Algorytm:
     * - Iteruje po każdej komórce.
     * - Jeśli znajdzie 1, wywołuje DFS, aby oznaczyć całą wyspę jako odwiedzoną (zamienia na 0).
     * - Zwiększa licznik wysp.
     *
     * Złożoność czasowa: O(m * n), gdzie m = liczba wierszy, n = liczba kolumn
     * Złożoność pamięciowa: O(m * n) – w przypadku pełnej rekursji
     */
    static int countIsland(List<List<Integer>> matrix) {
        if (matrix == null || matrix.isEmpty()) return 0;
        int rows = matrix.size(), cols = matrix.get(0).size();
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix.get(i).get(j) == 1) {
                    dfs(matrix, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(List<List<Integer>> matrix, int i, int j) {
        int rows = matrix.size(), cols = matrix.get(0).size();
        if (i < 0 || j < 0 || i >= rows || j >= cols || matrix.get(i).get(j) == 0) return;

        matrix.get(i).set(j, 0); // oznacz jako odwiedzone

        dfs(matrix, i + 1, j);
        dfs(matrix, i - 1, j);
        dfs(matrix, i, j + 1);
        dfs(matrix, i, j - 1);
    }

    /**
     * Sprawdza, czy nieskierowany graf reprezentowany jako lista sąsiedztwa jest dwudzielny (bipartite).
     *
     * Graf jest dwudzielny, jeśli można podzielić zbiór jego wierzchołków na dwa rozłączne zbiory
     * tak, aby każda krawędź łączyła wierzchołki z różnych zbiorów (czyli nie istnieje krawędź między
     * wierzchołkami tego samego koloru).
     *
     * Algorytm:
     * - Wykorzystuje kolorowanie grafu metodą BFS:
     *   1. Przechodzi przez wszystkie wierzchołki (w razie niespójności grafu).
     *   2. Dla każdego nieodwiedzonego wierzchołka przypisuje kolor (np. 0) i uruchamia BFS.
     *   3. Każdemu sąsiadowi przypisuje przeciwny kolor (1 - currentColor).
     *   4. Jeśli napotka sąsiada o takim samym kolorze — graf nie jest dwudzielny.
     *
     * Złożoność czasowa: O(V + E), gdzie V — liczba wierzchołków, E — liczba krawędzi.
     * Złożoność pamięciowa: O(V) — tablica kolorów i kolejka BFS.
     */
    static int bipartiteGraphValidation(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];
        Arrays.fill(colors, -1); // -1 oznacza nieodwiedzone

        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                colors[i] = 0;

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int neighbor : graph.get(current)) {
                        if (colors[neighbor] == -1) {
                            colors[neighbor] = 1 - colors[current]; // przeciwny kolor
                            queue.add(neighbor);
                        } else if (colors[neighbor] == colors[current]) {
                            return 0; // konflikt kolorów => nie jest dwudzielny
                        }
                    }
                }
            }
        }

        return 1; // graf dwudzielny
    }

    /**
     * Znajduje długość najdłuższej rosnącej ścieżki w macierzy.
     *
     * Reguły:
     * - Można poruszać się tylko w 4 kierunkach: góra, dół, lewo, prawo.
     * - Każdy kolejny element na ścieżce musi mieć wartość większą niż poprzedni.
     *
     * Algorytm:
     * - Dla każdej komórki uruchamiamy DFS z memoizacją.
     * - Zapamiętujemy wynik dla każdej komórki w tablicy cache, aby nie powtarzać obliczeń.
     * - DFS odwiedza sąsiadów tylko wtedy, gdy ich wartość jest większa.
     *
     * Złożoność czasowa: O(m * n) — każda komórka odwiedzona maksymalnie raz (przez memoizację).
     * Złożoność pamięciowa: O(m * n) — pamięć dla cache oraz stosu rekurencji.
     */
    static int longestIncreasingPath(List<List<Integer>> matrix) {
        if (matrix == null || matrix.isEmpty()) return 0;

        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int[][] cache = new int[rows][cols];

        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, dfs(matrix, i, j, cache));
            }
        }
        return max;
    }

    private static int dfs(List<List<Integer>> matrix, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) return cache[i][j];

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        int max = 1;

        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && nj >= 0 && ni < matrix.size() && nj < matrix.get(0).size()
                    && matrix.get(ni).get(nj) > matrix.get(i).get(j)) {
                max = Math.max(max, 1 + dfs(matrix, ni, nj, cache));
            }
        }

        cache[i][j] = max;
        return max;
    }
}
