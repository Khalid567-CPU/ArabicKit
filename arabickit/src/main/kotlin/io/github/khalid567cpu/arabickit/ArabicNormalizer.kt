package io.github.khalid567cpu.arabickit

import io.github.khalid567cpu.arabickit.internal.ArabicNormalizationEngine

/**
 * Deterministic Arabic text normalization for search and comparison.
 */
public object ArabicNormalizer {
    @JvmStatic
    @JvmOverloads
    public fun normalize(
        input: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): String = ArabicNormalizationEngine.normalize(input, options).text
}
