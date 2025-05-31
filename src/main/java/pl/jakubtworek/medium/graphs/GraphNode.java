package pl.jakubtworek.medium.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphNode<T> {
    private final T value;
    private final List<GraphNode<T>> neighbors;

    public GraphNode(T value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    /** Zwraca wartość węzła */
    public T getValue() {
        return value;
    }

    /** Zwraca listę sąsiadów */
    public List<GraphNode<T>> getNeighbors() {
        return neighbors;
    }

    /** Dodaje krawędź do sąsiada */
    public void addNeighbor(GraphNode<T> neighbor) {
        neighbors.add(neighbor);
    }

    /** Usuwa sąsiada (jeśli istnieje) */
    public void removeNeighbor(GraphNode<T> neighbor) {
        neighbors.remove(neighbor);
    }

    @Override
    public String toString() {
        return "Node(" + value + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GraphNode<?> other)) return false;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
