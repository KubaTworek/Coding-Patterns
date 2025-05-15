package pl.jakubtworek.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.fast_and_slow_pointers.FractionalPointIdentification.isHappyNumber;

public class FractionalPointIdentificationTest {
    @ParameterizedTest
    @CsvSource({
            "1, true",
            "7, true",
            "10, true",
            "13, true",
            "19, true",
            "2, false",
            "4, false",
            "20, false",
            "21, false",
            "123, false"
    })
    void testIsHappyNumber(int input, boolean expected) {
        boolean result = isHappyNumber(input);
        assertEquals(expected, result);
    }

}
