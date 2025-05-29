package pl.jakubtworek.medium.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.trees.DFS.*;

class DFSTest {

    @ParameterizedTest
    @MethodSource("provideInvertTreeCases")
    void testInvertTree(TreeNode input, TreeNode expected) {
        TreeNode actual = invertTree(input);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> provideInvertTreeCases() {
        return Stream.of(
                Arguments.of(
                        // Input tree:      1
                        //                /   \
                        //               2     3
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3);
                        }},
                        // Expected:        1
                        //                /   \
                        //               3     2
                        new TreeNode(1) {{
                            left = new TreeNode(3);
                            right = new TreeNode(2);
                        }}
                ),
                Arguments.of(
                        // Input: null tree
                        null,
                        null
                ),
                Arguments.of(
                        // Input:      1
                        //              \
                        //               2
                        new TreeNode(1) {{
                            right = new TreeNode(2);
                        }},
                        // Expected:   1
                        //            /
                        //           2
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                        }}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTreesForValidation")
    void testIsValid(TreeNode root, boolean expected) {
        assertEquals(expected, isValid(root));
    }

    static Stream<Arguments> provideTreesForValidation() {
        return Stream.of(
                Arguments.of(
                        // Pełne drzewo binarne
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3);
                        }},
                        true
                ),
                Arguments.of(
                        // Węzeł ma tylko lewe dziecko — niepoprawne
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                        }},
                        false
                ),
                Arguments.of(
                        // Liść — poprawne
                        new TreeNode(1),
                        true
                ),
                Arguments.of(
                        // Zagnieżdżone, ale pełne
                        new TreeNode(1) {{
                            left = new TreeNode(2) {{
                                left = new TreeNode(4);
                                right = new TreeNode(5);
                            }};
                            right = new TreeNode(3) {{
                                left = new TreeNode(6);
                                right = new TreeNode(7);
                            }};
                        }},
                        true
                ),
                Arguments.of(
                        // Zagnieżdżone, ale brak jednego dziecka
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3) {{
                                left = new TreeNode(6); // brak prawego dziecka
                            }};
                        }},
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBSTValidationCases")
    void testBinarySearchTreeValidation(TreeNode root, boolean expected) {
        assertEquals(expected, binarySearchTreeValidation(root));
    }

    static Stream<Arguments> provideBSTValidationCases() {
        return Stream.of(
                Arguments.of(
                        // Poprawne BST
                        new TreeNode(5) {{
                            left = new TreeNode(3);
                            right = new TreeNode(7);
                        }},
                        true
                ),
                Arguments.of(
                        // Niepoprawne — lewe poddrzewo zawiera wartość większą niż root
                        new TreeNode(5) {{
                            left = new TreeNode(6);
                            right = new TreeNode(7);
                        }},
                        false
                ),
                Arguments.of(
                        // Niepoprawne — głębsze naruszenie zasad BST
                        new TreeNode(10) {{
                            left = new TreeNode(5);
                            right = new TreeNode(15) {{
                                left = new TreeNode(6); // błędne: 6 < 10
                                right = new TreeNode(20);
                            }};
                        }},
                        false
                ),
                Arguments.of(
                        new TreeNode(1), // pojedynczy węzeł — zawsze OK
                        true
                ),
                Arguments.of(
                        null, // puste drzewo
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideLcaTestCases")
    void testLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, TreeNode expected) {
        TreeNode actual = lowestCommonAncestor(root, p, q);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> provideLcaTestCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p1 = root.left;              // 5
        TreeNode q1 = root.right;             // 1
        TreeNode expected1 = root;            // 3

        TreeNode p2 = root.left;              // 5
        TreeNode q2 = root.left.right.right;  // 4
        TreeNode expected2 = root.left;       // 5

        TreeNode p3 = root.left.right.left;   // 7
        TreeNode q3 = root.left.right.right;  // 4
        TreeNode expected3 = root.left.right; // 2

        return Stream.of(
                Arguments.of(root, p1, q1, expected1),
                Arguments.of(root, p2, q2, expected2),
                Arguments.of(root, p3, q3, expected3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTreesForReconstruction")
    void testBuildABinaryTreeFromPreorderAndInorder(List<Integer> preorder, List<Integer> inorder, TreeNode expected) {
        TreeNode actual = buildABinaryTreeFromPreorderAndInorder(preorder, inorder);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> provideTreesForReconstruction() {
        return Stream.of(
                Arguments.of(
                        List.of(3, 9, 20, 15, 7),       // preorder
                        List.of(9, 3, 15, 20, 7),       // inorder
                        new TreeNode(3) {{
                            left = new TreeNode(9);
                            right = new TreeNode(20) {{
                                left = new TreeNode(15);
                                right = new TreeNode(7);
                            }};
                        }}
                ),
                Arguments.of(
                        List.of(1, 2), List.of(2, 1),
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                        }}
                ),
                Arguments.of(
                        List.of(1), List.of(1),
                        new TreeNode(1)
                ),
                Arguments.of(
                        List.of(), List.of(), null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTreeForMaximumSum")
    void testMaximumSumOfContinuousPathInBinaryTree(TreeNode root, int expected) {
        assertEquals(expected, maximumSumOfContinuousPathInBinaryTree(root));
    }

    static Stream<Arguments> provideTreeForMaximumSum() {
        return Stream.of(
                Arguments.of(
                        //       -10
                        //       /  \
                        //      9   20
                        //         /  \
                        //        15   7
                        new TreeNode(-10) {{
                            left = new TreeNode(9);
                            right = new TreeNode(20) {{
                                left = new TreeNode(15);
                                right = new TreeNode(7);
                            }};
                        }},
                        42 // 15 + 20 + 7
                ),
                Arguments.of(
                        new TreeNode(1),
                        1
                ),
                Arguments.of(
                        new TreeNode(2) {{
                            left = new TreeNode(-1);
                            right = new TreeNode(3);
                        }},
                        5 // 2 + 3
                ),
                Arguments.of(
                        new TreeNode(-3),
                        -3
                )
        );
    }
}
