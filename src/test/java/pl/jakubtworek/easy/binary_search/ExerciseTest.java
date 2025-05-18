package pl.jakubtworek.easy.binary_search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.binary_search.Exercise.*;

class ExerciseTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "8, 2",
            "10, 3",
            "26, 5",
            "100, 10"
    })
    void testFindSquareRootFloor(int input, int expected) {
        assertEquals(expected, findSquareRootFloor(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'4,5,6,7,0,1,2', 0",
            "'3,4,5,1,2', 1",
            "'1,2,3,4,5', 1",
            "'2,1', 1",
            "'1', 1"
    })
    void testFindMinimumInRotatedSortedArray(String input, int expected) {
        List<Integer> nums = parseList(input);
        assertEquals(expected, findMinimumInRotatedSortedArray(nums));
    }

    @ParameterizedTest
    @CsvSource({
            "'4,5,6,7,0,1,2', 0, 4",
            "'4,5,6,7,0,1,2', 3, -1",
            "'1', 1, 0",
            "'1,3', 3, 1",
            "'5,1,2,3,4', 1, 1"
    })
    void testSearchInRotatedSortedArray(String input, int target, int expectedIndex) {
        List<Integer> nums = parseList(input);
        assertEquals(expectedIndex, searchInRotatedSortedArray(nums, target));
    }

    private List<Integer> parseList(String csv) {
        return List.of(csv.split(",")).stream().map(String::trim).map(Integer::parseInt).toList();
    }
}
