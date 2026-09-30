# Reviewing Recipes (Android)

These are the team's rules for arranging pull requests in [LogiKFlow](https://logikflow.yemzikk.in). AI agents read this file automatically, and **Add the team checklist** in arrange mode adds the checklist below.

## Order

- **Build setup first, together.** `gradle/libs.versions.toml` and `app/build.gradle.kts` go in one section at the top, catalog first, so reviewers see a dependency before its use.
- **Flags next.** `config/FeatureFlags.kt` comes before the code that checks it.
- **Data layer bottom-up:** models (`data/model/`), then stores, then repositories.
- **Unit tests right after the class they test**, never at the end.
- **Then ViewModels, then Activities.** New screens before existing screens that link to them.
- **`AndroidManifest.xml` after the Activities it registers.**
- **Resources last:** layouts first (screen layouts before the items they include), then `menu/`, then `drawable/`, and `values/strings.xml` at the very end.

## Notes

- Say what the app does with the feature flag **off**.
- Point out anything that runs on the main thread, or touches `SharedPreferences` on startup.
- Label vector drawables and other generated or copied assets as **Generated**, and string-only files as **Skim**.

## Checklist

- With the feature flag off, the app behaves exactly as before
- No disk or network work on the main thread
- Survives rotation and process death without losing state
- New user-facing text is in `strings.xml`, not hard-coded
- New layouts work in dark mode and with large font sizes
- Repository logic is covered by unit tests
