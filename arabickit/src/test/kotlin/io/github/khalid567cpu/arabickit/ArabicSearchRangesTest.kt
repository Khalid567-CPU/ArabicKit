package io.github.khalid567cpu.arabickit

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

public class ArabicSearchRangesTest {
    @Test
    public fun mapsDiacriticsAndTatweelBackToOriginalText() {
        val source = "إِنَّ الــلَّهَ غَفُورٌ"

        val range = requireNotNull(ArabicSearch.findFirst(source, "ان الله"))

        assertEquals("إِنَّ الــلَّهَ", source.substring(range.start, range.endExclusive))
    }

    @Test
    public fun returnsMultipleRangesInOriginalText() {
        val source = "إِنَّ اللَّهَ غفور، إن الله رحيم"

        val ranges = ArabicSearch.findAll(source, "ان الله")

        assertEquals(2, ranges.size)
        assertEquals("إِنَّ اللَّهَ", source.substring(ranges[0].start, ranges[0].endExclusive))
        assertEquals("إن الله", source.substring(ranges[1].start, ranges[1].endExclusive))
    }

    @Test
    public fun collapsedWhitespaceMapsAcrossTheOriginalWhitespaceRun() {
        val source = "  مرحبا   بالعالم  "

        val range = requireNotNull(ArabicSearch.findFirst(source, "مرحبا بالعالم"))

        assertEquals("مرحبا   بالعالم", source.substring(range.start, range.endExclusive))
    }

    @Test
    public fun supportsOptionalOverlappingMatches() {
        val source = "ااا"

        assertEquals(1, ArabicSearch.findAll(source, "اا").size)
        assertEquals(2, ArabicSearch.findAll(source, "اا", allowOverlaps = true).size)
    }

    @Test
    public fun respectsMaximumResultCount() {
        val ranges = ArabicSearch.findAll(
            text = "الله الله الله",
            query = "الله",
            maxResults = 2,
        )

        assertEquals(2, ranges.size)
    }

    @Test
    public fun returnsNoMatchForAnEmptyNormalizedQuery() {
        assertTrue(ArabicSearch.findAll("نص عربي", "  ").isEmpty())
        assertNull(ArabicSearch.findFirst("نص عربي", "َـ"))
    }

    @Test
    public fun preservesOptionsWhenMappingRanges() {
        val options = ArabicNormalizationOptions(normalizeAlef = false)

        assertNull(ArabicSearch.findFirst("إسلام", "اسلام", options))
    }

    @Test
    public fun mapsLowercaseExpansionBackToOneSourceCharacter() {
        val source = "İ"

        val range = requireNotNull(ArabicSearch.findFirst(source, "i̇"))

        assertEquals(ArabicMatchRange(0, 1), range)
    }

    @Test
    public fun mapsQuranicMarksAndAlefWasla() {
        val source = "ٱللَّهُۚ"

        val range = requireNotNull(ArabicSearch.findFirst(source, "الله"))

        assertEquals(source, source.substring(range.start, range.endExclusive))
    }
}
