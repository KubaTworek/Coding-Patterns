package pl.jakubtworek.easy.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.easy.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.fast_and_slow_pointers.SequenceAnalysis.findMiddleNode;

class SequenceAnalysisTest {

    @ParameterizedTest(name = "[{index}] list={0} → middle={1}")
    @MethodSource("midpointTestData")
    void testFindMiddleNode(List<Integer> input, Integer expectedMidValue) {
        SinglyLinkedList<Integer> list = SinglyLinkedList.of(input.toArray(new Integer[0]));
        var midpoint = findMiddleNode(list);
        var actualValue = midpoint != null ? midpoint.value : null;

        assertEquals(expectedMidValue, actualValue);
    }

    static Stream<Arguments> midpointTestData() {
        return Stream.of(
                Arguments.of(List.of(), null),                  // 🧪 pusty
                Arguments.of(List.of(42), 42),                 // 🧪 jeden element
                Arguments.of(List.of(1, 2), 2),                // 🧪 dwa elementy → drugi
                Arguments.of(List.of(1, 2, 3), 2),             // 🧪 trzy elementy → środek
                Arguments.of(List.of(10, 20, 30, 40), 30),     // 🧪 cztery → drugi z dwóch środkowych
                Arguments.of(List.of(1, 2, 3, 4, 5), 3)         // 🧪 pięć → środkowy
        );
    }
}
