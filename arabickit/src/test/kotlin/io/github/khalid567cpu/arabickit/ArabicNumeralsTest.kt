package io.github.khalid567cpu.arabickit

import org.junit.Assert.assertEquals
import org.junit.Test

public class ArabicNumeralsTest {
    @Test
    public fun convertsMixedDigitsToArabicIndic() {
        assertEquals(
            "الإصدار ٢٠٢٦",
            ArabicNumerals.convert("الإصدار 20۲۶", ArabicNumeralSystem.ARABIC_INDIC),
        )
    }

    @Test
    public fun convertsArabicDigitsToWestern() {
        assertEquals(
            "Page 123",
            ArabicNumerals.convert("Page ١۲3", ArabicNumeralSystem.WESTERN),
        )
    }
}
