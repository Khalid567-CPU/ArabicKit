# Changelog

All notable changes to ArabicKit will be documented here.

The format follows Keep a Changelog, and releases will follow Semantic Versioning after the first stable API is defined.

## [Unreleased]

### Added

- `ArabicMatchRange` with original UTF-16 source offsets.
- `ArabicSearch.findFirst(...)` and `ArabicSearch.findAll(...)`.
- Optional overlapping matches and result limits.
- Offset-aware normalization across diacritics, Quranic marks, tatweel, alef variants, whitespace collapse, and Latin lowercase expansion.
- Android background highlighting with `ArabicHighlighter`.
- Range-search coverage in the sample app and unit tests.

## [0.1.0] - 2026-08-02

### Added

- Android library foundation.
- Arabic normalization options.
- Arabic-aware normalized search.
- Three-way decimal numeral conversion.
- Dependency-free XML sample app.
- Unit tests, Lint verification, and GitHub Actions CI.
- English and Arabic project documentation.
