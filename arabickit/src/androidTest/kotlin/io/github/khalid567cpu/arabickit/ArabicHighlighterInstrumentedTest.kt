package io.github.khalid567cpu.arabickit

import android.graphics.Color
import android.text.style.BackgroundColorSpan
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class ArabicHighlighterInstrumentedTest {
    @Test
    public fun highlightBackground_mapsNormalizedMatchesToOriginalArabicText() {
        val source = "إِنَّ الــلَّهَ غَفُورٌ، إن الله رحيم ٢٠٢٦"

        val highlighted = ArabicHighlighter.highlightBackground(
            text = source,
            query = "ان الله",
            color = Color.YELLOW,
        )

        val spans = highlighted
            .getSpans(0, highlighted.length, BackgroundColorSpan::class.java)
            .sortedBy(highlighted::getSpanStart)

        assertEquals(2, spans.size)
        assertTrue(spans.all { it.backgroundColor == Color.YELLOW })

        val highlightedSubstrings = spans.map { span ->
            source.substring(
                highlighted.getSpanStart(span),
                highlighted.getSpanEnd(span),
            )
        }

        assertEquals(
            listOf(
                "إِنَّ الــلَّهَ",
                "إن الله",
            ),
            highlightedSubstrings,
        )
    }
}
