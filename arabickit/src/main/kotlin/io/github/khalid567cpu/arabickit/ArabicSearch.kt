package io.github.khalid567cpu.arabickit

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
    ): Boolean {
        val normalizedQuery = ArabicNormalizer.normalize(query, options)
        if (normalizedQuery.isEmpty()) return false

        return ArabicNormalizer.normalize(text, options).contains(normalizedQuery)
    }

    @JvmStatic
    @JvmOverloads
    public fun equalsNormalized(
        first: CharSequence,
        second: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): Boolean = ArabicNormalizer.normalize(first, options) ==
        ArabicNormalizer.normalize(second, options)
}
