package io.github.khalid567cpu.arabickit

/**
 * Supported decimal digit glyph sets.
 */
public enum class ArabicNumeralSystem {
    WESTERN,
    ARABIC_INDIC,
    EASTERN_ARABIC_INDIC,
}

/**
 * Converts decimal digits while preserving every non-digit character.
 */
public object ArabicNumerals {
    private const val WESTERN = "0123456789"
    private const val ARABIC_INDIC = "٠١٢٣٤٥٦٧٨٩"
    private const val EASTERN_ARABIC_INDIC = "۰۱۲۳۴۵۶۷۸۹"

    @JvmStatic
    public fun convert(
        input: CharSequence,
        target: ArabicNumeralSystem,
    ): String {
        val targetDigits = when (target) {
            ArabicNumeralSystem.WESTERN -> WESTERN
            ArabicNumeralSystem.ARABIC_INDIC -> ARABIC_INDIC
            ArabicNumeralSystem.EASTERN_ARABIC_INDIC -> EASTERN_ARABIC_INDIC
        }

        return buildString(input.length) {
            input.forEach { character ->
                val digit = digitValue(character)
                append(if (digit >= 0) targetDigits[digit] else character)
            }
        }
    }

    private fun digitValue(character: Char): Int {
        val westernIndex = WESTERN.indexOf(character)
        if (westernIndex >= 0) return westernIndex

        val arabicIndicIndex = ARABIC_INDIC.indexOf(character)
        if (arabicIndicIndex >= 0) return arabicIndicIndex

        return EASTERN_ARABIC_INDIC.indexOf(character)
    }
}
