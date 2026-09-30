# LogiKFlow Android demo: Recipes

A small Android app (Kotlin, XML views) used to try [LogiKFlow](https://logikflow.yemzikk.in), which lets a pull request's author (or their AI agent) arrange its changed files into a reading order with sections, notes and review checklists.

## Try it

1. Open the pull request **Add favorites** in this repository.
2. Swap `github.com` for `logikflow.yemzikk.in` in its URL, or paste the link on the LogiKFlow dashboard.
3. Compare GitHub's alphabetical order with a reading order.

   | GitHub order | Reading order |
   |---|---|
   | build file, manifest, activities | flag and model |
   | then resources | then storage and repository, with the unit test beside it |
   | then the unit test | then the ViewModel and screens |
   | then the version catalog | then layouts, menu, drawables and strings |

To arrange it with an agent, connect the LogiKFlow MCP server and ask:

```text
Arrange the "Add favorites" PR in LogiKFlow in logical order.
```

## Build

Open the project in Android Studio, or:

```bash
./gradlew :app:assembleDebug :app:testDebugUnitTest
```

Requires JDK 17 or newer (Android Studio's bundled JDK works).
