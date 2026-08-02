package io.github.khalid567cpu.arabickit

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

public class ArabicSearchTest {
    @Test
    public fun findsTextAcrossDiacriticsAndAlefVariants() {
        assertTrue(ArabicSearch.contains("إِنَّ اللَّهَ غَفُورٌ", "ان الله"))
    }

    @Test
    public fun rejectsAnEmptyNormalizedQuery() {
        assertFalse(ArabicSearch.contains("نص عربي", "  "))
    }
}
