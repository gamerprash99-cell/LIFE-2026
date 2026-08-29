# 16 — Known Issues

---

### Issue #1 — Missing Gradle wrapper scripts

- **Severity**: 🟠 High (blocks command-line builds)
- **Description**: `gradle/wrapper/gradle-wrapper.properties` exists but
  `gradlew`, `gradlew.bat`, and `gradle/wrapper/gradle-wrapper.jar` do not.
- **Reproduction**: Run `./gradlew assembleDebug` from a fresh clone.
- **Expected behavior**: Gradle wrapper downloads/builds the project.
- **Actual behavior**: `bash: ./gradlew: No such file or directory`
- **Possible cause**: These files were not generated because the project
  was authored without ever running Gradle (no SDK/network access available
  during development — see `docs/13_DEPLOYMENT.md`).
- **Current workaround**: Open in Android Studio (usually self-heals), or
  run `gradle wrapper --gradle-version 8.9` from a machine with Gradle installed.
  The CI workflow (`.github/workflows/android-build.yml`, added after this
  issue was first logged) works around it by installing Gradle directly via
  `gradle/actions/setup-gradle` and running `gradle assembleDebug` instead
  of `./gradlew assembleDebug` — but this is a workaround, not a fix; local
  command-line builds still need one of the two options above.
- **Status**: Open (CI workaround in place; local `gradlew` still missing)

---

### Issue #2 — No release signing configuration

- **Severity**: 🟠 High (blocks any real release)
- **Description**: `app/build.gradle.kts` has no `signingConfigs` block.
- **Reproduction**: Run `./gradlew assembleRelease`.
- **Expected behavior**: A signed, installable release APK.
- **Actual behavior**: An unsigned APK is produced (or the build may need
  additional configuration depending on Gradle/AGP defaults).
- **Current workaround**: None — this must be set up before any Play
  Store submission or distribution outside development devices.
- **Status**: Open

---

### Issue #3 — Backup restore has no UI entry point

- **Severity**: 🟡 Medium
- **Description**: `data/repository/BackupRepository.kt`'s
  `importFromFile()` function is fully implemented but is never called from
  any screen. `ui/settings/SettingsScreen.kt` only wires up Export and Share.
- **Reproduction**: Open Settings → Backup & Export — there is no "Import"
  or "Restore" button.
- **Expected behavior**: A user should be able to pick a previously exported
  JSON file and restore their data from it.
- **Actual behavior**: No such UI exists; the feature is code-complete but unreachable.
- **Current workaround**: None via the UI. A developer could call
  `backupRepository.importFromFile(file)` directly for testing.
- **Status**: Open

---

### Issue #4 — Task recurrence (`RepeatRule`) is stored but never acted on

- **Severity**: 🟡 Medium
- **Description**: `TaskEntity.repeatRule` (`NONE/DAILY/WEEKLY/MONTHLY/CUSTOM_DAYS`)
  and `repeatDaysCsv` fields exist and can be set, but no scheduler or job
  ever reads them to auto-create the next occurrence of a recurring task.
- **Reproduction**: Create a task with a repeat rule (not currently exposed
  in any Add Task dialog UI either — see Issue #5) and complete it; no new
  instance is generated for the next day/week/month.
- **Expected behavior**: A completed recurring task should spawn its next occurrence.
- **Actual behavior**: Nothing happens; the field is inert.
- **Current workaround**: Manually re-create the task.
- **Status**: Open

---

### Issue #5 — No UI to set task priority, category, description, or repeat rule

- **Severity**: 🟢 Low
- **Description**: `TaskEntity` supports `priority`, `category`,
  `description`, and `repeatRule`, but `ui/tasks/TasksScreen.kt`'s "Add task"
  dialog only exposes a title field and a reminder-time picker.
- **Reproduction**: Open Tasks → "+" — only a title field and reminder option appear.
- **Expected behavior**: Full task creation matching what the data model supports.
- **Actual behavior**: New tasks are always created with default priority
  (`MEDIUM`), no category, no description.
- **Current workaround**: None via UI.
- **Status**: Open

---

### Issue #6 — Habit completions are not cleaned up when a habit is deleted

- **Severity**: 🟢 Low
- **Description**: `HabitRepository.delete()` deletes the `HabitEntity` row
  only; no `@ForeignKey(onDelete = CASCADE)` exists on `HabitCompletionEntity`,
  and no manual cleanup query is called.
- **Reproduction**: Create a habit, log a few completions, delete the habit.
- **Expected behavior**: Associated `habit_completions` rows are also removed.
- **Actual behavior**: Orphaned rows remain in the `habit_completions` table indefinitely.
- **Current workaround**: None — orphaned data has no functional impact
  today (nothing queries by an unknown `habitId`), but it is a data-hygiene issue.
- **Status**: Open

---

### Issue #7 — AI Assistant chat does not use real app data as context

- **Severity**: 🟢 Low (functional limitation, not a bug)
- **Description**: `AiRepository.chat()` supports an optional `contextBlock`
  parameter, but `ui/ai/AiAssistantScreen.kt` always passes `null`.
- **Reproduction**: Ask the AI Assistant "What tasks do I have today?"
- **Expected behavior** (if this were wired up): A context-aware answer
  referencing real task data.
- **Actual behavior**: The AI has no way to know; it will either say so or
  hallucinate a generic answer.
- **Current workaround**: None — this is a scoped-out feature, not a defect
  in what exists.
- **Status**: Open

---

### Issue #8 — No database encryption / AI key not encrypted

- **Severity**: 🟠 High (security)
- **Description**: See `docs/08_SECURITY.md` findings #1 and #2 in full.
- **Status**: Open — tracked here for visibility alongside functional issues.

---

### Issue #9 — No automated tests exist

- **Severity**: 🟡 Medium (process/quality risk, not a functional bug)
- **Description**: See `docs/14_TESTING.md`.
- **Status**: Open
