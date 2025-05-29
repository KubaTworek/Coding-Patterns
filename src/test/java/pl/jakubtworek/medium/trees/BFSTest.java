package pl.jakubtworek.medium.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.trees.BFS.rightmostNodesOfBinaryTree;
import static pl.jakubtworek.medium.trees.BFS.widestBinaryTreeLevel;

class BFSTest {
    @ParameterizedTest
    @MethodSource("provideTreesForRightView")
    void testRightmostNodesOfBinaryTree(TreeNode root, List<Integer> expected) {
        assertEquals(expected, rightmostNodesOfBinaryTree(root));
    }

    static Stream<Arguments> provideTreesForRightView() {
        return Stream.of(
                Arguments.of(
                        //        1
                        //       / \
                        //      2   3
                        //       \    \
                        //        5    4
                        new TreeNode(1) {{
                            left = new TreeNode(2) {{
                                right = new TreeNode(5);
                            }};
                            right = new TreeNode(3) {{
                                right = new TreeNode(4);
                            }};
                        }},
                        List.of(1, 3, 4)
                ),
                Arguments.of(
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3);
                        }},
                        List.of(1, 3)
                ),
                Arguments.of(
                        new TreeNode(1),
                        List.of(1)
                ),
                Arguments.of(
                        null,
                        List.of()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideWidestLevelTrees")
    void testWidestBinaryTreeLevel(TreeNode root, int expected) {
        assertEquals(expected, widestBinaryTreeLevel(root));
    }

    static Stream<Arguments> provideWidestLevelTrees() {
        return Stream.of(
                Arguments.of(
                        // Przykład:
                        //        1
                        //       / \
                        //      3   2
                        //     /     \
                        //    5       9
                        //   /         \
                        //  6           7
                        new TreeNode(1) {{
                            left = new TreeNode(3) {{
                                left = new TreeNode(5) {{
                                    left = new TreeNode(6);
                                }};
                            }};
                            right = new TreeNode(2) {{
                                right = new TreeNode(9) {{
                                    right = new TreeNode(7);
                                }};
                            }};
                        }},
                        8 // szerokość poziomu z 6 i 7 (pełna szerokość binarnego poziomu)
                ),
                Arguments.of(new TreeNode(1), 1),
                Arguments.of(null, 0)
        );
    }

}
