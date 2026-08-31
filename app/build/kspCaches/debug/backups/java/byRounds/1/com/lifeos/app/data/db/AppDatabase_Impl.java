package com.lifeos.app.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.lifeos.app.data.db.dao.CaptureDao;
import com.lifeos.app.data.db.dao.CaptureDao_Impl;
import com.lifeos.app.data.db.dao.DiaryDao;
import com.lifeos.app.data.db.dao.DiaryDao_Impl;
import com.lifeos.app.data.db.dao.ExpenseDao;
import com.lifeos.app.data.db.dao.ExpenseDao_Impl;
import com.lifeos.app.data.db.dao.HabitCompletionDao;
import com.lifeos.app.data.db.dao.HabitCompletionDao_Impl;
import com.lifeos.app.data.db.dao.HabitDao;
import com.lifeos.app.data.db.dao.HabitDao_Impl;
import com.lifeos.app.data.db.dao.NoteDao;
import com.lifeos.app.data.db.dao.NoteDao_Impl;
import com.lifeos.app.data.db.dao.TaskDao;
import com.lifeos.app.data.db.dao.TaskDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile NoteDao _noteDao;

  private volatile TaskDao _taskDao;

  private volatile HabitDao _habitDao;

  private volatile HabitCompletionDao _habitCompletionDao;

  private volatile ExpenseDao _expenseDao;

  private volatile DiaryDao _diaryDao;

  private volatile CaptureDao _captureDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `notes` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `contentJson` TEXT NOT NULL, `plainTextForSearch` TEXT NOT NULL, `folder` TEXT, `tagsCsv` TEXT NOT NULL, `isPinned` INTEGER NOT NULL, `isFavorite` INTEGER NOT NULL, `isArchived` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `attachmentsJson` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tasks` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `dueDateEpochDay` INTEGER, `dueTimeMinutes` INTEGER, `priority` TEXT NOT NULL, `category` TEXT, `reminderEpochMillis` INTEGER, `repeatRule` TEXT NOT NULL, `repeatDaysCsv` TEXT, `isCompleted` INTEGER NOT NULL, `completedAtEpochMillis` INTEGER, `isDeleted` INTEGER NOT NULL, `notes` TEXT, `attachmentsJson` TEXT NOT NULL, `sourceType` TEXT, `sourceId` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `habits` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `icon` TEXT NOT NULL, `category` TEXT, `frequency` TEXT NOT NULL, `customDaysCsv` TEXT, `goalCount` INTEGER NOT NULL, `reminderEpochMillis` INTEGER, `startDateEpochDay` INTEGER NOT NULL, `isArchived` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `habit_completions` (`habitId` TEXT NOT NULL, `dateEpochDay` INTEGER NOT NULL, `progressCount` INTEGER NOT NULL, `completedAtEpochMillis` INTEGER NOT NULL, PRIMARY KEY(`habitId`, `dateEpochDay`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` TEXT NOT NULL, `amount` REAL NOT NULL, `category` TEXT NOT NULL, `dateEpochDay` INTEGER NOT NULL, `timeMinutes` INTEGER NOT NULL, `merchant` TEXT, `paymentMethod` TEXT NOT NULL, `note` TEXT, `tagsCsv` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `diary_entries` (`id` TEXT NOT NULL, `title` TEXT, `content` TEXT NOT NULL, `mood` TEXT, `tagsCsv` TEXT NOT NULL, `dateEpochDay` INTEGER NOT NULL, `timeMinutes` INTEGER NOT NULL, `aiGenerated` INTEGER NOT NULL, `isReviewed` INTEGER NOT NULL, `attachmentsJson` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `captures` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `filePath` TEXT, `thumbnailPath` TEXT, `caption` TEXT, `transcript` TEXT, `mood` TEXT, `tagsCsv` TEXT NOT NULL, `dateEpochDay` INTEGER NOT NULL, `timeMinutes` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4f9ebe477899186e2ed4606be020f79a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `notes`");
        db.execSQL("DROP TABLE IF EXISTS `tasks`");
        db.execSQL("DROP TABLE IF EXISTS `habits`");
        db.execSQL("DROP TABLE IF EXISTS `habit_completions`");
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
        db.execSQL("DROP TABLE IF EXISTS `diary_entries`");
        db.execSQL("DROP TABLE IF EXISTS `captures`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsNotes = new HashMap<String, TableInfo.Column>(13);
        _columnsNotes.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("contentJson", new TableInfo.Column("contentJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("plainTextForSearch", new TableInfo.Column("plainTextForSearch", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("folder", new TableInfo.Column("folder", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("tagsCsv", new TableInfo.Column("tagsCsv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("isPinned", new TableInfo.Column("isPinned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("isArchived", new TableInfo.Column("isArchived", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("attachmentsJson", new TableInfo.Column("attachmentsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotes = new TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes);
        final TableInfo _existingNotes = TableInfo.read(db, "notes");
        if (!_infoNotes.equals(_existingNotes)) {
          return new RoomOpenHelper.ValidationResult(false, "notes(com.lifeos.app.data.db.entities.NoteEntity).\n"
                  + " Expected:\n" + _infoNotes + "\n"
                  + " Found:\n" + _existingNotes);
        }
        final HashMap<String, TableInfo.Column> _columnsTasks = new HashMap<String, TableInfo.Column>(19);
        _columnsTasks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("dueDateEpochDay", new TableInfo.Column("dueDateEpochDay", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("dueTimeMinutes", new TableInfo.Column("dueTimeMinutes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("reminderEpochMillis", new TableInfo.Column("reminderEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("repeatRule", new TableInfo.Column("repeatRule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("repeatDaysCsv", new TableInfo.Column("repeatDaysCsv", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("completedAtEpochMillis", new TableInfo.Column("completedAtEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("attachmentsJson", new TableInfo.Column("attachmentsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("sourceType", new TableInfo.Column("sourceType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("sourceId", new TableInfo.Column("sourceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTasks = new TableInfo("tasks", _columnsTasks, _foreignKeysTasks, _indicesTasks);
        final TableInfo _existingTasks = TableInfo.read(db, "tasks");
        if (!_infoTasks.equals(_existingTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "tasks(com.lifeos.app.data.db.entities.TaskEntity).\n"
                  + " Expected:\n" + _infoTasks + "\n"
                  + " Found:\n" + _existingTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsHabits = new HashMap<String, TableInfo.Column>(12);
        _columnsHabits.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("frequency", new TableInfo.Column("frequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("customDaysCsv", new TableInfo.Column("customDaysCsv", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("goalCount", new TableInfo.Column("goalCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("reminderEpochMillis", new TableInfo.Column("reminderEpochMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("startDateEpochDay", new TableInfo.Column("startDateEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("isArchived", new TableInfo.Column("isArchived", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHabits = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHabits = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHabits = new TableInfo("habits", _columnsHabits, _foreignKeysHabits, _indicesHabits);
        final TableInfo _existingHabits = TableInfo.read(db, "habits");
        if (!_infoHabits.equals(_existingHabits)) {
          return new RoomOpenHelper.ValidationResult(false, "habits(com.lifeos.app.data.db.entities.HabitEntity).\n"
                  + " Expected:\n" + _infoHabits + "\n"
                  + " Found:\n" + _existingHabits);
        }
        final HashMap<String, TableInfo.Column> _columnsHabitCompletions = new HashMap<String, TableInfo.Column>(4);
        _columnsHabitCompletions.put("habitId", new TableInfo.Column("habitId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitCompletions.put("dateEpochDay", new TableInfo.Column("dateEpochDay", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitCompletions.put("progressCount", new TableInfo.Column("progressCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitCompletions.put("completedAtEpochMillis", new TableInfo.Column("completedAtEpochMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHabitCompletions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHabitCompletions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHabitCompletions = new TableInfo("habit_completions", _columnsHabitCompletions, _foreignKeysHabitCompletions, _indicesHabitCompletions);
        final TableInfo _existingHabitCompletions = TableInfo.read(db, "habit_completions");
        if (!_infoHabitCompletions.equals(_existingHabitCompletions)) {
          return new RoomOpenHelper.ValidationResult(false, "habit_completions(com.lifeos.app.data.db.entities.HabitCompletionEntity).\n"
                  + " Expected:\n" + _infoHabitCompletions + "\n"
                  + " Found:\n" + _existingHabitCompletions);
        }
        final HashMap<String, TableInfo.Column> _columnsExpenses = new HashMap<String, TableInfo.Column>(10);
        _columnsExpenses.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("dateEpochDay", new TableInfo.Column("dateEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("timeMinutes", new TableInfo.Column("timeMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("merchant", new TableInfo.Column("merchant", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("note", new TableInfo.Column("note", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("tagsCsv", new TableInfo.Column("tagsCsv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExpenses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExpenses = new TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses);
        final TableInfo _existingExpenses = TableInfo.read(db, "expenses");
        if (!_infoExpenses.equals(_existingExpenses)) {
          return new RoomOpenHelper.ValidationResult(false, "expenses(com.lifeos.app.data.db.entities.ExpenseEntity).\n"
                  + " Expected:\n" + _infoExpenses + "\n"
                  + " Found:\n" + _existingExpenses);
        }
        final HashMap<String, TableInfo.Column> _columnsDiaryEntries = new HashMap<String, TableInfo.Column>(12);
        _columnsDiaryEntries.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("mood", new TableInfo.Column("mood", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("tagsCsv", new TableInfo.Column("tagsCsv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("dateEpochDay", new TableInfo.Column("dateEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("timeMinutes", new TableInfo.Column("timeMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("aiGenerated", new TableInfo.Column("aiGenerated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("isReviewed", new TableInfo.Column("isReviewed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("attachmentsJson", new TableInfo.Column("attachmentsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiaryEntries.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDiaryEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDiaryEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDiaryEntries = new TableInfo("diary_entries", _columnsDiaryEntries, _foreignKeysDiaryEntries, _indicesDiaryEntries);
        final TableInfo _existingDiaryEntries = TableInfo.read(db, "diary_entries");
        if (!_infoDiaryEntries.equals(_existingDiaryEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "diary_entries(com.lifeos.app.data.db.entities.DiaryEntity).\n"
                  + " Expected:\n" + _infoDiaryEntries + "\n"
                  + " Found:\n" + _existingDiaryEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsCaptures = new HashMap<String, TableInfo.Column>(11);
        _columnsCaptures.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("filePath", new TableInfo.Column("filePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("thumbnailPath", new TableInfo.Column("thumbnailPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("caption", new TableInfo.Column("caption", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("transcript", new TableInfo.Column("transcript", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("mood", new TableInfo.Column("mood", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("tagsCsv", new TableInfo.Column("tagsCsv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("dateEpochDay", new TableInfo.Column("dateEpochDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("timeMinutes", new TableInfo.Column("timeMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCaptures = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCaptures = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCaptures = new TableInfo("captures", _columnsCaptures, _foreignKeysCaptures, _indicesCaptures);
        final TableInfo _existingCaptures = TableInfo.read(db, "captures");
        if (!_infoCaptures.equals(_existingCaptures)) {
          return new RoomOpenHelper.ValidationResult(false, "captures(com.lifeos.app.data.db.entities.CaptureEntity).\n"
                  + " Expected:\n" + _infoCaptures + "\n"
                  + " Found:\n" + _existingCaptures);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4f9ebe477899186e2ed4606be020f79a", "beca0a7fdffc71e23bab8af1cebf5dc4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "notes","tasks","habits","habit_completions","expenses","diary_entries","captures");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `notes`");
      _db.execSQL("DELETE FROM `tasks`");
      _db.execSQL("DELETE FROM `habits`");
      _db.execSQL("DELETE FROM `habit_completions`");
      _db.execSQL("DELETE FROM `expenses`");
      _db.execSQL("DELETE FROM `diary_entries`");
      _db.execSQL("DELETE FROM `captures`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(NoteDao.class, NoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TaskDao.class, TaskDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HabitDao.class, HabitDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HabitCompletionDao.class, HabitCompletionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DiaryDao.class, DiaryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CaptureDao.class, CaptureDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public NoteDao noteDao() {
    if (_noteDao != null) {
      return _noteDao;
    } else {
      synchronized(this) {
        if(_noteDao == null) {
          _noteDao = new NoteDao_Impl(this);
        }
        return _noteDao;
      }
    }
  }

  @Override
  public TaskDao taskDao() {
    if (_taskDao != null) {
      return _taskDao;
    } else {
      synchronized(this) {
        if(_taskDao == null) {
          _taskDao = new TaskDao_Impl(this);
        }
        return _taskDao;
      }
    }
  }

  @Override
  public HabitDao habitDao() {
    if (_habitDao != null) {
      return _habitDao;
    } else {
      synchronized(this) {
        if(_habitDao == null) {
          _habitDao = new HabitDao_Impl(this);
        }
        return _habitDao;
      }
    }
  }

  @Override
  public HabitCompletionDao habitCompletionDao() {
    if (_habitCompletionDao != null) {
      return _habitCompletionDao;
    } else {
      synchronized(this) {
        if(_habitCompletionDao == null) {
          _habitCompletionDao = new HabitCompletionDao_Impl(this);
        }
        return _habitCompletionDao;
      }
    }
  }

  @Override
  public ExpenseDao expenseDao() {
    if (_expenseDao != null) {
      return _expenseDao;
    } else {
      synchronized(this) {
        if(_expenseDao == null) {
          _expenseDao = new ExpenseDao_Impl(this);
        }
        return _expenseDao;
      }
    }
  }

  @Override
  public DiaryDao diaryDao() {
    if (_diaryDao != null) {
      return _diaryDao;
    } else {
      synchronized(this) {
        if(_diaryDao == null) {
          _diaryDao = new DiaryDao_Impl(this);
        }
        return _diaryDao;
      }
    }
  }

  @Override
  public CaptureDao captureDao() {
    if (_captureDao != null) {
      return _captureDao;
    } else {
      synchronized(this) {
        if(_captureDao == null) {
          _captureDao = new CaptureDao_Impl(this);
        }
        return _captureDao;
      }
    }
  }
}
