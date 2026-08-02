# ArabicKit

Production-ready Kotlin utilities for Arabic text normalization, search, and numeral conversion on Android.

> ArabicKit `0.1.0` is the first public foundation release. The API is tested, but `0.x` releases may still evolve before the first stable release.

## Current capabilities

- Remove Arabic diacritics and Quranic annotation marks.
- Remove tatweel.
- Normalize alef variants and alef maksura.
- Normalize whitespace and Latin casing.
- Search through normalized Arabic text.
- Convert Western, Arabic-Indic, and Eastern Arabic-Indic digits.
- Java-friendly APIs through `@JvmStatic` and `@JvmOverloads`.
- A dependency-free Android sample app.
- Unit tests, Android Lint, and GitHub Actions CI.

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

```kotlin
val normalized = ArabicNormalizer.normalize("إِنَّ الــلَّهَ غَفُورٌ")
// "ان الله غفور"

val matches = ArabicSearch.contains(
    text = "إِنَّ اللَّهَ غَفُورٌ",
    query = "ان الله",
)
// true

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

## Project status

ArabicKit is preparing the public `0.1.0` release. See [ROADMAP.md](ROADMAP.md) and [CHANGELOG.md](CHANGELOG.md).

## Contributing

Read [CONTRIBUTING.md](CONTRIBUTING.md) before opening an issue or pull request.

## License

Apache License 2.0. See [LICENSE](LICENSE).
