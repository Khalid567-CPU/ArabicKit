package io.github.khalid567cpu.arabickit

import org.junit.Assert.assertEquals
import org.junit.Test

public class ArabicNormalizerTest {
    @Test
    public fun removesDiacriticsTatweelAndNormalizesAlef() {
        val input = "إِنَّ الــلَّهَ غَفُورٌ"

        assertEquals("ان الله غفور", ArabicNormalizer.normalize(input))
    }

    @Test
    public fun normalizesAlefMaksuraWithoutChangingTaaMarbuta() {
        assertEquals("علي هدي ورحمة", ArabicNormalizer.normalize("عَلَى هُدًى وَرَحْمَةٍ"))
    }

    @Test
    public fun collapsesWhitespaceAndLowercasesLatin() {
        assertEquals("arabic kit عربي", ArabicNormalizer.normalize("  Arabic   KIT  عربي "))
    }
}
