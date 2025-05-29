package pl.jakubtworek.medium.trees;

import java.util.*;

class DFS {
    /**
     * Odwraca drzewo binarne (zamienia lewe i prawe poddrzewo każdego węzła).
     *
     * Algorytm:
     * - Rekurencyjnie odwiedzaj każdy węzeł:
     *    1. Odwróć lewe poddrzewo.
     *    2. Odwróć prawe poddrzewo.
     *    3. Zamień je miejscami.
     *
     * Przykład:
     *     1             1
     *    / \    →      / \
     *   2   3         3   2
     *
     * Złożoność czasowa: O(n) — każdy węzeł odwiedzany raz
     * Złożoność pamięciowa: O(h) — maksymalna głębokość rekurencji (h = wysokość drzewa)
     */
    static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * Sprawdza, czy dane drzewo binarne jest pełne — tzn. każdy węzeł:
     * - ma albo dokładnie 2 dzieci (lewe i prawe),
     * - albo jest liściem (nie ma żadnych dzieci).
     *
     * Warunki uznania drzewa za niepełne:
     * - Występuje węzeł z jednym tylko dzieckiem (np. tylko lewe lub tylko prawe).
     *
     * Przykład:
     * - [1, 2, 3] → true (pełne drzewo binarne)
     * - [1, 2, null] → false (brakuje prawego dziecka)
     *
     * Złożoność czasowa: O(n), gdzie n to liczba węzłów w drzewie (odwiedzamy każdy raz)
     * Złożoność pamięciowa: O(h), gdzie h to wysokość drzewa (głębokość rekurencji)
     */
    static boolean isValid(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        return isValid(root.left) && isValid(root.right);
    }

    /**
     * Sprawdza, czy dane drzewo binarne spełnia warunki drzewa BST (Binary Search Tree).
     *
     * Zasady BST:
     * - Dla każdego węzła: wszystkie wartości w lewym poddrzewie < node.val
     * - wszystkie wartości w prawym poddrzewie > node.val
     *
     * Algorytm:
     * - Rekurencyjnie sprawdza czy każda wartość mieści się w poprawnym zakresie [min, max].
     *
     * Złożoność czasowa: O(n) — odwiedzamy każdy węzeł raz
     * Złożoność pamięciowa: O(h) — głębokość rekurencji (wysokość drzewa)
     */
    static boolean binarySearchTreeValidation(TreeNode root) {
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;

        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }

    /**
     * Znajduje najniższego wspólnego przodka (Lowest Common Ancestor) dwóch węzłów `p` i `q` w drzewie binarnym.
     *
     * Algorytm (DFS):
     * - Jeśli `root` jest `null`, zwraca `null`.
     * - Jeśli `root` jest równe `p` lub `q`, zwraca `root`.
     * - Rekurencyjnie przeszukuje lewe i prawe poddrzewo.
     * - Jeśli `p` i `q` są znalezione po przeciwnych stronach (lewe i prawe nie są `null`),
     *   `root` jest ich wspólnym przodkiem.
     * - Jeśli tylko jedno poddrzewo zawiera `p` lub `q`, zwraca to poddrzewo.
     *
     * Złożoność czasowa: O(n) — odwiedzamy każdy węzeł raz.
     * Złożoność pamięciowa: O(h) — głębokość rekurencji (wysokość drzewa).
     */
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    /**
     * Odtwarza drzewo binarne na podstawie list preorder i inorder.
     *
     * Dane:
     * - preorder: kolejność odwiedzania (root -> left -> right)
     * - inorder: kolejność inorder (left -> root -> right)
     *
     * Algorytm:
     * 1. Pierwszy element preorder to korzeń drzewa.
     * 2. Znajdź ten element w inorder — dzieli on lewą/prawą część drzewa.
     * 3. Rekurencyjnie buduj lewe i prawe poddrzewo.
     *
     * Złożoność czasowa: O(n) — każde poddrzewo odwiedzane raz
     * Złożoność pamięciowa: O(n) — dla mapy i stosu rekurencji
     */
    static TreeNode buildABinaryTreeFromPreorderAndInorder(List<Integer> preorder, List<Integer> inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderIndexMap.put(inorder.get(i), i);
        }

        return build(preorder, 0, preorder.size() - 1,
                inorder, 0, inorder.size() - 1,
                inorderIndexMap);
    }

    private static TreeNode build(List<Integer> preorder, int preStart, int preEnd,
                                  List<Integer> inorder, int inStart, int inEnd,
                                  Map<Integer, Integer> inorderIndexMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preorder.get(preStart);
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInInorder = inorderIndexMap.get(rootVal);
        int leftTreeSize = rootIndexInInorder - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftTreeSize,
                inorder, inStart, rootIndexInInorder - 1,
                inorderIndexMap);

        root.right = build(preorder, preStart + leftTreeSize + 1, preEnd,
                inorder, rootIndexInInorder + 1, inEnd,
                inorderIndexMap);

        return root;
    }

    /**
     * Oblicza maksymalną sumę ciągłej ścieżki w drzewie binarnym.
     * Ścieżka to dowolna liczba kolejnych węzłów połączonych krawędziami (od węzła do węzła),
     * ale niekoniecznie od korzenia do liścia.
     *
     * Dopuszczalne kierunki: lewo ↔ root ↔ prawo (nie można przeskakiwać lub zawracać).
     *
     * Algorytm:
     * - Dla każdego węzła obliczamy największą możliwą sumę po lewej i prawej stronie.
     * - Maksymalna suma przechodząca przez dany węzeł to: left + node + right
     * - Zwracamy do rodzica największą gałąź (left + node lub right + node), żeby kontynuować ścieżkę.
     *
     * Złożoność czasowa: O(n), odwiedzamy każdy węzeł raz.
     * Złożoność pamięciowa: O(h), głębokość rekurencji.
     */
    static int maximumSumOfContinuousPathInBinaryTree(TreeNode root) {
        int[] maxSum = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, maxSum);
        return maxSum[0];
    }

    private static int maxPathSum(TreeNode node, int[] maxSum) {
        if (node == null) return 0;

        int left = Math.max(0, maxPathSum(node.left, maxSum));
        int right = Math.max(0, maxPathSum(node.right, maxSum));

        int current = node.val + left + right;

        maxSum[0] = Math.max(maxSum[0], current);

        return node.val + Math.max(left, right);
    }}
