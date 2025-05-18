package pl.jakubtworek.easy.stacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.stacks.ProcessingDuplicates.removeAdjacentDuplicates;

class ProcessingDuplicatesTest {

    @ParameterizedTest
    @CsvSource({
            "'abbaca', 'ca'",
            "'azxxzy', 'ay'",
            "'', ''",
            "'abc', 'abc'",
            "'aabbccdd', ''",
            "'abba', ''",
            "'a', 'a'",
            "'aa', ''"
    })
    void testRepeatedRemovalOfAdjacentDuplicates(String input, String expected) {
        String result = removeAdjacentDuplicates(input);
        assertEquals(expected, result);
    }
}
