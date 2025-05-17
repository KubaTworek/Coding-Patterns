package pl.jakubtworek.easy.fast_and_slow_pointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubtworek.easy.fast_and_slow_pointers.FractionalPointIdentification.isHappy;

class FractionalPointIdentificationTest {

    @ParameterizedTest(name = "[{index}] isHappy({0}) = {1}")
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
    void testIsHappy(int input, boolean expected) {
        boolean result = isHappy(input);
        assertEquals(expected, result);
    }
}
