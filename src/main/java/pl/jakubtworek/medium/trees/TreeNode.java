package pl.jakubtworek.medium.trees;

import java.util.Objects;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    // opcjonalna pomocnicza metoda do porównywania struktury drzewa
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode)) return false;
        TreeNode o = (TreeNode) obj;
        return val == o.val &&
                Objects.equals(left, o.left) &&
                Objects.equals(right, o.right);
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
    }
}
