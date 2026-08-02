package io.github.khalid567cpu.arabickit

import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan

/**
 * Android text-highlighting helpers backed by [ArabicSearch] ranges.
 */
public object ArabicHighlighter {
    /**
     * Finds all normalized matches and applies a background-color span to each.
     */
    @JvmStatic
    @JvmOverloads
    public fun highlightBackground(
        text: CharSequence,
        query: CharSequence,
        color: Int,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
        allowOverlaps: Boolean = false,
    ): SpannableString = applyBackground(
        text = text,
        ranges = ArabicSearch.findAll(
            text = text,
            query = query,
            options = options,
            allowOverlaps = allowOverlaps,
        ),
        color = color,
    )

    /**
     * Applies a background-color span to precomputed original-text [ranges].
     */
    @JvmStatic
    public fun applyBackground(
        text: CharSequence,
        ranges: Iterable<ArabicMatchRange>,
        color: Int,
    ): SpannableString {
        val highlighted = SpannableString(text)

        ranges.forEach { range ->
            require(range.endExclusive <= text.length) {
                "Match range $range exceeds source length ${text.length}."
            }

            if (range.length > 0) {
                highlighted.setSpan(
                    BackgroundColorSpan(color),
                    range.start,
                    range.endExclusive,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
                )
            }
        }

        return highlighted
    }
}
