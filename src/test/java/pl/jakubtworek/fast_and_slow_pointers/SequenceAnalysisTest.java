package pl.jakubtworek.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.linked_list.SinglyLinkedList;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.fast_and_slow_pointers.SequenceAnalysis.getMidpoint;

public class SequenceAnalysisTest {
    @ParameterizedTest
    @MethodSource("provideListsForMidpoint")
    void testGetMidpoint(List<Integer> values, Integer expectedMidValue) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        values.forEach(list::append);

        var midpoint = getMidpoint(list);
        var actualValue = midpoint != null ? midpoint.value : null;

        assertEquals(expectedMidValue, actualValue);
    }

    private static Stream<Arguments> provideListsForMidpoint() {
        return Stream.of(
                // 🧪 Pusta lista → null
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(), null
                ),
                // 🧪 Jeden element → ten element
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(42), 42
                ),
                // 🧪 Dwa elementy → drugi (slow przechodzi raz)
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2), 2
                ),
                // 🧪 Trzy elementy → drugi
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3), 2
                ),
                // 🧪 Cztery elementy → trzeci
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(10, 20, 30, 40), 30
                ),
                // 🧪 Pięć elementów → trzeci
                org.junit.jupiter.params.provider.Arguments.of(
                        List.of(1, 2, 3, 4, 5), 3
                )
        );
    }
}
