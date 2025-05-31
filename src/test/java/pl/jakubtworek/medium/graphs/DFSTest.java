package pl.jakubtworek.medium.graphs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.jakubtworek.medium.graphs.DFS.*;

class DFSTest {

    @ParameterizedTest(name = "{index}: testCloneGraph[{0}]")
    @MethodSource("provideGraphs")
    void testGraphClone(GraphNode<Integer> root, int expectedNodeCount) {
        GraphNode<Integer> cloned = copy(root);

        if (root == null) {
            assertNull(cloned);
            return;
        }

        Set<GraphNode<Integer>> visitedOriginal = new HashSet<>();
        Set<GraphNode<Integer>> visitedClone = new HashSet<>();

        // DFS to count nodes
        dfsVisit(root, visitedOriginal);
        dfsVisit(cloned, visitedClone);

        assertEquals(expectedNodeCount, visitedOriginal.size());
        assertEquals(expectedNodeCount, visitedClone.size());

        // Ensure different instances
        for (GraphNode<Integer> node : visitedOriginal) {
            for (GraphNode<Integer> copy : visitedClone) {
                assertNotSame(node, copy);
            }
        }
    }

    private static void dfsVisit(GraphNode<Integer> node, Set<GraphNode<Integer>> visited) {
        if (node == null || visited.contains(node)) return;
        visited.add(node);
        for (GraphNode<Integer> neighbor : node.getNeighbors()) {
            dfsVisit(neighbor, visited);
        }
    }

    static Stream<Arguments> provideGraphs() {
        // Graph 1: null
        GraphNode<Integer> nullGraph = null;

        // Graph 2: single node
        GraphNode<Integer> single = new GraphNode<>(1);

        // Graph 3: 3-node cycle
        GraphNode<Integer> n1 = new GraphNode<>(1);
        GraphNode<Integer> n2 = new GraphNode<>(2);
        GraphNode<Integer> n3 = new GraphNode<>(3);
        n1.addNeighbor(n2);
        n2.addNeighbor(n3);
        n3.addNeighbor(n1); // cycle

        return Stream.of(
                Arguments.of(nullGraph, 0),
                Arguments.of(single, 1),
                Arguments.of(n1, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("matrixProvider")
    void testCountIsland(List<List<Integer>> input, int expected) {
        // deep copy, bo metoda modyfikuje input
        List<List<Integer>> copy = input.stream()
                .map(row -> new ArrayList<>(row)) // tworzysz modyfikowalną kopię każdego wiersza
                .collect(Collectors.toList());    // zbierasz do nowej modyfikowalnej listy
        assertEquals(expected, countIsland(copy));
    }

    static Stream<Arguments> matrixProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(1, 1, 0, 0),
                        List.of(1, 0, 0, 1),
                        List.of(0, 0, 1, 1),
                        List.of(0, 0, 0, 0)
                ), 2),
                Arguments.of(List.of(
                        List.of(0, 0, 0),
                        List.of(0, 0, 0)
                ), 0),
                Arguments.of(List.of(
                        List.of(1)
                ), 1),
                Arguments.of(List.of(
                        List.of(1, 0, 1, 0, 1)
                ), 3)
        );
    }

    @ParameterizedTest
    @MethodSource("graphProvider")
    void testBipartiteGraph(List<List<Integer>> graph, int expected) {
        assertEquals(expected, bipartiteGraphValidation(graph));
    }

    static Stream<Arguments> graphProvider() {
        return Stream.of(
                // graf dwudzielny: 0-1, 0-3, 1-2
                Arguments.of(List.of(
                        List.of(1, 3),
                        List.of(0, 2),
                        List.of(1, 3),
                        List.of(0, 2)
                ), 1),

                // nie dwudzielny (cykl długości 3)
                Arguments.of(List.of(
                        List.of(1, 2),
                        List.of(0, 2),
                        List.of(0, 1)
                ), 0),

                // graf niespójny — ale dwudzielny
                Arguments.of(List.of(
                        List.of(), // 0
                        List.of(2), // 1
                        List.of(1), // 2
                        List.of(4), // 3
                        List.of(3)  // 4
                ), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("matrixProvider1")
    void testLIP(List<List<Integer>> matrix, int expected) {
        assertEquals(expected, longestIncreasingPath(matrix));
    }

    static Stream<Arguments> matrixProvider1() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(9, 9, 4),
                        List.of(6, 6, 8),
                        List.of(2, 1, 1)
                ), 4), // [1→2→6→9]

                Arguments.of(List.of(
                        List.of(3, 4, 5),
                        List.of(3, 2, 6),
                        List.of(2, 2, 1)
                ), 4), // [3→4→5→6]

                Arguments.of(List.of(
                        List.of(1)
                ), 1)
        );
    }
}
