package io.github.khalid567cpu.arabickit

import io.github.khalid567cpu.arabickit.internal.ArabicNormalizationEngine

/**
 * Arabic-aware search helpers built on deterministic normalization.
 */
public object ArabicSearch {
    @JvmStatic
    @JvmOverloads
    public fun contains(
        text: CharSequence,
        query: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): Boolean = findFirst(text, query, options) != null

    @JvmStatic
    @JvmOverloads
    public fun equalsNormalized(
        first: CharSequence,
        second: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): Boolean = ArabicNormalizer.normalize(first, options) ==
        ArabicNormalizer.normalize(second, options)

    /**
     * Returns the first normalized match as offsets in the original [text].
     */
    @JvmStatic
    @JvmOverloads
    public fun findFirst(
        text: CharSequence,
        query: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): ArabicMatchRange? = findAll(
        text = text,
        query = query,
        options = options,
        allowOverlaps = false,
        maxResults = 1,
    ).firstOrNull()

    /**
     * Returns normalized matches as UTF-16 ranges in the original [text].
     *
     * An empty query after normalization produces an empty result. By default,
     * matches do not overlap. Set [allowOverlaps] to `true` to advance one
     * normalized UTF-16 code unit after every match.
     */
    @JvmStatic
    @JvmOverloads
    public fun findAll(
        text: CharSequence,
        query: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
        allowOverlaps: Boolean = false,
        maxResults: Int = Int.MAX_VALUE,
    ): List<ArabicMatchRange> {
        require(maxResults >= 0) { "maxResults must be non-negative." }
        if (maxResults == 0) return emptyList()

        val normalizedQuery = ArabicNormalizer.normalize(query, options)
        if (normalizedQuery.isEmpty()) return emptyList()

        val normalizedText = ArabicNormalizationEngine.normalize(text, options)
        if (normalizedText.text.isEmpty()) return emptyList()

        val results = ArrayList<ArabicMatchRange>()
        var searchFrom = 0

        while (searchFrom <= normalizedText.text.length - normalizedQuery.length) {
            val normalizedStart = normalizedText.text.indexOf(
                string = normalizedQuery,
                startIndex = searchFrom,
            )

            if (normalizedStart < 0) break

            val normalizedEndExclusive = normalizedStart + normalizedQuery.length
            val sourceRange = normalizedText.sourceRange(
                normalizedStart = normalizedStart,
                normalizedEndExclusive = normalizedEndExclusive,
            )

            if (results.lastOrNull() != sourceRange) {
                results += sourceRange
            }

            if (results.size >= maxResults) break

            searchFrom = normalizedStart + if (allowOverlaps) {
                1
            } else {
                normalizedQuery.length
            }
        }

        return results
    }
}
