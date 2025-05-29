package pl.jakubtworek.medium.trees;

class Exercise {
    /// EASY

    /**
     * Sprawdza, czy podany węzeł jest liściem (nie ma dzieci).
     *
     * Złożoność czasowa: O(1)
     * Złożoność pamięciowa: O(1)
     */
    static boolean isLeaf(TreeNode node) {
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    /// MEDIUM

    /**
     * Liczy liczbę liści (węzłów bez dzieci) w drzewie binarnym.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(h) — głębokość stosu rekurencji
     */
    static int countLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return countLeaves(root.left) + countLeaves(root.right);
    }

    /// HARD

    /**
     * Oblicza maksymalną głębokość drzewa binarnego.
     *
     * Głębokość = liczba krawędzi od korzenia do najgłębszego liścia.
     *
     * Złożoność czasowa: O(n)
     * Złożoność pamięciowa: O(h)
     */
    static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
