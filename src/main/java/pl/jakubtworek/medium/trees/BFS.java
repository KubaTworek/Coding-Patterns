package pl.jakubtworek.medium.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFS {
    /**
     * Zwraca listę wartości węzłów widocznych z prawej strony drzewa binarnego.
     *
     * Algorytm:
     * - Wykonuje BFS (przechodzenie poziomami).
     * - Dla każdego poziomu zapisuje **ostatni** węzeł (czyli najbardziej prawy).
     *
     * Przykład:
     *        1
     *       / \
     *      2   3
     *       \    \
     *        5    4
     * Wynik: [1, 3, 4] — węzły najbardziej na prawo na każdym poziomie
     *
     * Złożoność czasowa: O(n), gdzie n to liczba węzłów (każdy odwiedzany raz)
     * Złożoność pamięciowa: O(w), gdzie w to maksymalna szerokość drzewa (kolejka poziomu)
     */
    static List<Integer> rightmostNodesOfBinaryTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i == levelSize - 1) {
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    /**
     * Oblicza maksymalną szerokość poziomu drzewa binarnego.
     * Szerokość to różnica między najbardziej lewym i prawym węzłem na danym poziomie,
     * liczona względem indeksu w pełnym drzewie binarnym (uwzględnia puste miejsca).
     *
     * Algorytm:
     * - BFS (kolejka), przechowuje pary (węzeł, indeks).
     * - Dla każdego poziomu zapamiętuje pierwszy i ostatni indeks, liczy szerokość.
     *
     * Złożoność czasowa: O(n), każdy węzeł odwiedzany raz.
     * Złożoność pamięciowa: O(n), ze względu na kolejkę poziomu.
     */
    static int widestBinaryTreeLevel(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0)); // węzeł + indeks (0)

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelMin = queue.peek().second;
            int first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                int index = current.second - levelMin; // normalizacja do 0
                TreeNode node = current.first;

                if (i == 0) first = index;
                if (i == size - 1) last = index;

                if (node.left != null)
                    queue.add(new Pair<>(node.left, 2 * index));
                if (node.right != null)
                    queue.add(new Pair<>(node.right, 2 * index + 1));
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }

}
