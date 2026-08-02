# ArabicKit

Production-ready Kotlin utilities for Arabic text normalization, search, range mapping, highlighting, and numeral conversion on Android.

> ArabicKit `0.2.0` adds original-text search ranges, multiple-match support, and Android highlighting. The API is tested, but `0.x` releases may still evolve before the first stable release.

## Current capabilities

- Remove Arabic diacritics and Quranic annotation marks.
- Remove tatweel.
- Normalize alef variants and alef maksura.
- Normalize whitespace and Latin casing.
- Search through normalized Arabic text.
- Return match ranges in the original UTF-16 source text.
- Find first, multiple, or overlapping normalized matches.
- Highlight normalized matches with Android `SpannableString`.
- Convert Western, Arabic-Indic, and Eastern Arabic-Indic digits.
- Java-friendly APIs through `@JvmStatic` and `@JvmOverloads`.
- A dependency-free Android sample app.
- Unit tests, Android Lint, GitHub Actions CI, and connected-device instrumentation coverage.

## Modules

- `:arabickit` — the Android library.
- `:sample` — a small XML + ViewBinding demonstration app.

## Local verification

```bash
./gradlew verifyFoundation
```

On Windows:

```powershell
.\gradlew.bat verifyFoundation
```

## Quick usage

### Normalize Arabic text

```kotlin
val normalized = ArabicNormalizer.normalize("إِنَّ الــلَّهَ غَفُورٌ")
// "ان الله غفور"
```

### Find original-text ranges

```kotlin
val source = "إِنَّ اللَّهَ غَفُورٌ، إن الله رحيم"

val ranges = ArabicSearch.findAll(
    text = source,
    query = "ان الله",
)

ranges.forEach { range ->
    println(source.substring(range.start, range.endExclusive))
}
```

The returned offsets target the original source, including its diacritics and tatweel, so they can be used directly with `substring` or Android spans.

### Highlight matches on Android

```kotlin
val highlighted = ArabicHighlighter.highlightBackground(
    text = source,
    query = "ان الله",
    color = Color.YELLOW,
)

textView.text = highlighted
```

### Convert numerals

```kotlin
val western = ArabicNumerals.convert(
    input = "الإصدار ٢٠٢٦",
    target = ArabicNumeralSystem.WESTERN,
)
// "الإصدار 2026"
```

## Compatibility

- Android minSdk 24
- compileSdk 37
- Java bytecode target 17
- Kotlin support is provided by Android Gradle Plugin built-in Kotlin.

## Releases

The current release is `v0.2.0`. Binary AAR assets are available from GitHub Releases.

## Project status

ArabicKit `0.2.0` delivers original-text search ranges and Android highlighting. The next planned release is `0.3.0`, focused on advanced Arabic utilities. See [ROADMAP.md](ROADMAP.md), [CHANGELOG.md](CHANGELOG.md), and the [0.2.0 implementation plan](docs/0.2.0-search-ranges-plan.md).

## Contributing

Read [CONTRIBUTING.md](CONTRIBUTING.md) before opening an issue or pull request.

## License

Apache License 2.0. See [LICENSE](LICENSE).
