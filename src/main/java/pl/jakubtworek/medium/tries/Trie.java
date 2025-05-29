package pl.jakubtworek.medium.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * Struktura Trie (drzewo prefiksowe) do przechowywania i wyszukiwania słów.
 *
 * Złożoność operacji:
 * - insert: O(m)
 * - search: O(m)
 * - startsWith: O(m)
 * gdzie m = długość słowa/prefiksu
 */
public class Trie {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private final TrieNode root = new TrieNode();

    /** Dodaje słowo do Trie */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, _ -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    /** Sprawdza, czy słowo znajduje się w Trie */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    /** Sprawdza, czy istnieje słowo pasujące do wzorca z kropkami (wildcard '.') */
    public boolean searchWithWildcard(String word) {
        return searchRecursive(root, word, 0);
    }

    /** Sprawdza, czy jakiekolwiek słowo w Trie zaczyna się od danego prefiksu */
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    /** Pomocnicza metoda do przemieszczania się po Trie */
    private TrieNode findNode(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) return null;
        }
        return current;
    }

    private boolean searchRecursive(TrieNode node, String word, int index) {
        if (node == null) return false;
        if (index == word.length()) return node.isEndOfWord;

        char ch = word.charAt(index);
        if (ch == '.') {
            for (TrieNode child : node.children.values()) {
                if (searchRecursive(child, word, index + 1)) return true;
            }
            return false;
        } else {
            return searchRecursive(node.children.get(ch), word, index + 1);
        }
    }
}
