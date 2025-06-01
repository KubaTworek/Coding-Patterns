package pl.jakubtworek.hard.bit_manipulation;

import java.util.List;

class Masks {
    /**
     * Zamienia miejscami wszystkie pary bitów nieparzystych i parzystych
     * w liczbie całkowitej.
     *
     * Parzyste bity: maska 0xAAAAAAAA (101010...)
     * Nieparzyste bity: maska 0x55555555 (010101...)
     *
     * Złożoność czasowa: O(1)
     */
    static int swapOddAndEvenBits(int n) {
        int even = (n & 0xAAAAAAAA) >>> 1;
        int odd = (n & 0x55555555) << 1;
        return even | odd;
    }

}
