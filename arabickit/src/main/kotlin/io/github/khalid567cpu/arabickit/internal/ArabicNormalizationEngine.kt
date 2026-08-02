package io.github.khalid567cpu.arabickit.internal

import io.github.khalid567cpu.arabickit.ArabicMatchRange
import io.github.khalid567cpu.arabickit.ArabicNormalizationOptions
import java.util.Locale

internal data class NormalizedArabicText(
    val text: String,
    private val sourceStarts: IntArray,
    private val sourceEndsExclusive: IntArray,
) {
    init {
        check(text.length == sourceStarts.size)
        check(text.length == sourceEndsExclusive.size)
    }

    fun sourceRange(
        normalizedStart: Int,
        normalizedEndExclusive: Int,
    ): ArabicMatchRange {
        require(normalizedStart in text.indices)
        require(normalizedEndExclusive in 1..text.length)
        require(normalizedStart < normalizedEndExclusive)

        return ArabicMatchRange(
            start = sourceStarts[normalizedStart],
            endExclusive = sourceEndsExclusive[normalizedEndExclusive - 1],
        )
    }
}

internal object ArabicNormalizationEngine {
    fun normalize(
        input: CharSequence,
        options: ArabicNormalizationOptions,
    ): NormalizedArabicText {
        val normalized = StringBuilder(input.length)
        val starts = ArrayList<Int>(input.length)
        val endsExclusive = ArrayList<Int>(input.length)
        var pendingLeadingStart: Int? = null

        input.forEachIndexed { index, character ->
            val sourceStart = pendingLeadingStart ?: index
            val sourceEndExclusive = index + 1

            if (shouldRemove(character, options)) {
                if (normalized.isNotEmpty()) {
                    endsExclusive[endsExclusive.lastIndex] = sourceEndExclusive
                } else if (pendingLeadingStart == null) {
                    pendingLeadingStart = index
                }
                return@forEachIndexed
            }

            if (options.normalizeWhitespace && isAsciiWhitespace(character)) {
                when {
                    normalized.isEmpty() -> {
                        if (pendingLeadingStart == null) {
                            pendingLeadingStart = index
                        }
                    }

                    normalized.last() == ' ' -> {
                        endsExclusive[endsExclusive.lastIndex] = sourceEndExclusive
                    }

                    else -> {
                        normalized.append(' ')
                        starts += sourceStart
                        endsExclusive += sourceEndExclusive
                        pendingLeadingStart = null
                    }
                }
                return@forEachIndexed
            }

            val transformed = transform(character, options)
            val emitted = if (options.lowercaseLatin) {
                transformed.toString().lowercase(Locale.ROOT)
            } else {
                transformed.toString()
            }

            emitted.forEach { emittedCharacter ->
                normalized.append(emittedCharacter)
                starts += sourceStart
                endsExclusive += sourceEndExclusive
            }

            pendingLeadingStart = null
        }

        if (options.normalizeWhitespace && normalized.lastOrNull() == ' ') {
            normalized.deleteCharAt(normalized.lastIndex)
            starts.removeAt(starts.lastIndex)
            endsExclusive.removeAt(endsExclusive.lastIndex)
        }

        return NormalizedArabicText(
            text = normalized.toString(),
            sourceStarts = starts.toIntArray(),
            sourceEndsExclusive = endsExclusive.toIntArray(),
        )
    }

    private fun shouldRemove(
        character: Char,
        options: ArabicNormalizationOptions,
    ): Boolean =
        (options.removeDiacritics && isArabicDiacritic(character)) ||
            (options.removeQuranicMarks && isQuranicAnnotationMark(character)) ||
            (options.removeTatweel && character == '\u0640')

    private fun transform(
        character: Char,
        options: ArabicNormalizationOptions,
    ): Char = when {
        options.normalizeAlef && character in ALEF_VARIANTS -> '\u0627'
        options.normalizeAlefMaksura && character == '\u0649' -> '\u064A'
        else -> character
    }

    private fun isArabicDiacritic(character: Char): Boolean =
        character in '\u064B'..'\u065F' || character == '\u0670'

    private fun isQuranicAnnotationMark(character: Char): Boolean =
        character in '\u0610'..'\u061A' || character in '\u06D6'..'\u06ED'

    private fun isAsciiWhitespace(character: Char): Boolean =
        character == ' ' || character in '\t'..'\r'

    private val ALEF_VARIANTS: Set<Char> = setOf(
        '\u0622',
        '\u0623',
        '\u0625',
        '\u0671',
    )
}
