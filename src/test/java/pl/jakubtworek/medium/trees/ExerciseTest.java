package pl.jakubtworek.medium.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.medium.trees.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @MethodSource("provideIsLeafCases")
    void testIsLeaf(TreeNode node, boolean expected) {
        assertEquals(expected, isLeaf(node));
    }

    static Stream<Arguments> provideIsLeafCases() {
        TreeNode leaf = new TreeNode(1);
        TreeNode internal = new TreeNode(2);
        internal.left = new TreeNode(3);

        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(leaf, true),
                Arguments.of(internal, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCountLeavesCases")
    void testCountLeaves(TreeNode root, int expected) {
        assertEquals(expected, countLeaves(root));
    }

    static Stream<Arguments> provideCountLeavesCases() {
        return Stream.of(
                Arguments.of(null, 0),
                Arguments.of(new TreeNode(1), 1),
                Arguments.of(
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3);
                        }},
                        2
                ),
                Arguments.of(
                        new TreeNode(1) {{
                            left = new TreeNode(2) {{
                                left = new TreeNode(4);
                            }};
                            right = new TreeNode(3);
                        }},
                        2 // 4 i 3 są liśćmi
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxDepthCases")
    void testMaxDepth(TreeNode root, int expected) {
        assertEquals(expected, maxDepth(root));
    }

    static Stream<Arguments> provideMaxDepthCases() {
        return Stream.of(
                Arguments.of(null, 0),
                Arguments.of(new TreeNode(1), 1),
                Arguments.of(
                        new TreeNode(1) {{
                            left = new TreeNode(2);
                            right = new TreeNode(3);
                        }},
                        2
                ),
                Arguments.of(
                        new TreeNode(1) {{
                            left = new TreeNode(2) {{
                                left = new TreeNode(3) {{
                                    left = new TreeNode(4);
                                }};
                            }};
                        }},
                        4
                )
        );
    }
}
