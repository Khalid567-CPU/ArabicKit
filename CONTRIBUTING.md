# Contributing to ArabicKit

Thank you for helping improve Arabic text support on Android.

## Before opening a pull request

1. Create or reference an issue for non-trivial changes.
2. Branch from `develop`.
3. Keep each pull request focused.
4. Add tests for behavior changes.
5. Run:

```powershell
.\gradlew.bat verifyFoundation
```

## Branch names

- `feature/<name>`
- `fix/<name>`
- `docs/<name>`
- `chore/<name>`

## Commit style

Use concise imperative messages, for example:

- `feat: add configurable hamza normalization`
- `fix: preserve non-decimal Arabic symbols`
- `docs: clarify search behavior`

## API principles

Arabic normalization can change meaning. New transformations must be configurable, documented, and covered by Arabic-language test cases.
