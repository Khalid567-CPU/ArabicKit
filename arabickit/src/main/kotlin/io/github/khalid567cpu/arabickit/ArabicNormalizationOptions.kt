package io.github.khalid567cpu.arabickit

/**
 * Controls how [ArabicNormalizer] transforms text.
 *
 * Conservative defaults improve search without merging letters such as
 * taa marbuta (ة) and haa (ه), which can alter meaning.
 */
public data class ArabicNormalizationOptions(
    public val removeDiacritics: Boolean = true,
    public val removeQuranicMarks: Boolean = true,
    public val removeTatweel: Boolean = true,
    public val normalizeAlef: Boolean = true,
    public val normalizeAlefMaksura: Boolean = true,
    public val normalizeWhitespace: Boolean = true,
    public val lowercaseLatin: Boolean = true,
)
