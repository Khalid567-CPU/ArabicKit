package io.github.khalid567cpu.arabickit

/**
 * A match range expressed in UTF-16 offsets of the original source text.
 *
 * The [start] offset is inclusive and [endExclusive] is exclusive, matching
 * Android `Spannable` and Kotlin substring conventions.
 */
public data class ArabicMatchRange(
    public val start: Int,
    public val endExclusive: Int,
) {
    init {
        require(start >= 0) { "start must be non-negative." }
        require(endExclusive >= start) { "endExclusive must be greater than or equal to start." }
    }

    /** Number of UTF-16 code units covered by this range. */
    public val length: Int
        get() = endExclusive - start

    /** Returns this range as a Kotlin [IntRange]. */
    public fun toIntRange(): IntRange = start until endExclusive
}
