package io.github.khalid567cpu.arabickit

import java.util.Locale

/**
 * Deterministic Arabic text normalization for search and comparison.
 */
public object ArabicNormalizer {
    private val arabicDiacritics = Regex("[\\u064B-\\u065F\\u0670]")
    private val quranicAnnotationMarks = Regex("[\\u0610-\\u061A\\u06D6-\\u06ED]")
    private val alefVariants = Regex("[\\u0622\\u0623\\u0625\\u0671]")
    private val whitespace = Regex("\\s+")

    @JvmStatic
    @JvmOverloads
    public fun normalize(
        input: CharSequence,
        options: ArabicNormalizationOptions = ArabicNormalizationOptions(),
    ): String {
        var result = input.toString()

        if (options.removeDiacritics) {
            result = result.replace(arabicDiacritics, "")
        }
        if (options.removeQuranicMarks) {
            result = result.replace(quranicAnnotationMarks, "")
        }
        if (options.removeTatweel) {
            result = result.replace("\u0640", "")
        }
        if (options.normalizeAlef) {
            result = result.replace(alefVariants, "\u0627")
        }
        if (options.normalizeAlefMaksura) {
            result = result.replace('\u0649', '\u064A')
        }
        if (options.normalizeWhitespace) {
            result = result.replace(whitespace, " ").trim()
        }
        if (options.lowercaseLatin) {
            result = result.lowercase(Locale.ROOT)
        }

        return result
    }
}
