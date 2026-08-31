package com.lifeos.app.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lifeos.app.data.db.Converters;
import com.lifeos.app.data.db.entities.RepeatRule;
import com.lifeos.app.data.db.entities.TaskEntity;
import com.lifeos.app.data.db.entities.TaskPriority;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskEntity> __insertionAdapterOfTaskEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<TaskEntity> __updateAdapterOfTaskEntity;

  private final SharedSQLiteStatement __preparedStmtOfSetCompleted;

  private final SharedSQLiteStatement __preparedStmtOfSoftDelete;

  private final SharedSQLiteStatement __preparedStmtOfReschedule;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskEntity = new EntityInsertionAdapter<TaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tasks` (`id`,`title`,`description`,`dueDateEpochDay`,`dueTimeMinutes`,`priority`,`category`,`reminderEpochMillis`,`repeatRule`,`repeatDaysCsv`,`isCompleted`,`completedAtEpochMillis`,`isDeleted`,`notes`,`attachmentsJson`,`sourceType`,`sourceId`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getDueDateEpochDay() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getDueDateEpochDay());
        }
        if (entity.getDueTimeMinutes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getDueTimeMinutes());
        }
        final String _tmp = __converters.fromPriority(entity.getPriority());
        statement.bindString(6, _tmp);
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getReminderEpochMillis() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getReminderEpochMillis());
        }
        final String _tmp_1 = __converters.fromRepeatRule(entity.getRepeatRule());
        statement.bindString(9, _tmp_1);
        if (entity.getRepeatDaysCsv() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRepeatDaysCsv());
        }
        final int _tmp_2 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        if (entity.getCompletedAtEpochMillis() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getCompletedAtEpochMillis());
        }
        final int _tmp_3 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(13, _tmp_3);
        if (entity.getNotes() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getNotes());
        }
        statement.bindString(15, entity.getAttachmentsJson());
        if (entity.getSourceType() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getSourceType());
        }
        if (entity.getSourceId() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getSourceId());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
      }
    };
    this.__updateAdapterOfTaskEntity = new EntityDeletionOrUpdateAdapter<TaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`description` = ?,`dueDateEpochDay` = ?,`dueTimeMinutes` = ?,`priority` = ?,`category` = ?,`reminderEpochMillis` = ?,`repeatRule` = ?,`repeatDaysCsv` = ?,`isCompleted` = ?,`completedAtEpochMillis` = ?,`isDeleted` = ?,`notes` = ?,`attachmentsJson` = ?,`sourceType` = ?,`sourceId` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getDueDateEpochDay() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getDueDateEpochDay());
        }
        if (entity.getDueTimeMinutes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getDueTimeMinutes());
        }
        final String _tmp = __converters.fromPriority(entity.getPriority());
        statement.bindString(6, _tmp);
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getReminderEpochMillis() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getReminderEpochMillis());
        }
        final String _tmp_1 = __converters.fromRepeatRule(entity.getRepeatRule());
        statement.bindString(9, _tmp_1);
        if (entity.getRepeatDaysCsv() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRepeatDaysCsv());
        }
        final int _tmp_2 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        if (entity.getCompletedAtEpochMillis() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getCompletedAtEpochMillis());
        }
        final int _tmp_3 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(13, _tmp_3);
        if (entity.getNotes() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getNotes());
        }
        statement.bindString(15, entity.getAttachmentsJson());
        if (entity.getSourceType() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getSourceType());
        }
        if (entity.getSourceId() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getSourceId());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
        statement.bindString(20, entity.getId());
      }
    };
    this.__preparedStmtOfSetCompleted = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET isCompleted = ?, completedAtEpochMillis = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSoftDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET isDeleted = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfReschedule = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET dueDateEpochDay = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final TaskEntity task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTaskEntity.insert(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TaskEntity task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTaskEntity.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setCompleted(final String id, final boolean completed, final Long completedAt,
      final long now, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetCompleted.acquire();
        int _argIndex = 1;
        final int _tmp = completed ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (completedAt == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, completedAt);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, now);
        _argIndex = 4;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetCompleted.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object softDelete(final String id, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSoftDelete.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, now);
        _argIndex = 2;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSoftDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object reschedule(final String id, final long newEpochDay, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfReschedule.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, newEpochDay);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, now);
        _argIndex = 3;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfReschedule.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TaskEntity>> observeForDay(final long epochDay) {
    final String _sql = "SELECT * FROM tasks WHERE isDeleted = 0 AND dueDateEpochDay = ? ORDER BY isCompleted ASC, priority ASC, dueTimeMinutes ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TaskEntity>> observeOverdue(final long todayEpochDay) {
    final String _sql = "SELECT * FROM tasks WHERE isDeleted = 0 AND isCompleted = 0 AND dueDateEpochDay < ? ORDER BY dueDateEpochDay ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, todayEpochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TaskEntity>> observeAll() {
    final String _sql = "SELECT * FROM tasks WHERE isDeleted = 0 ORDER BY isCompleted ASC, dueDateEpochDay ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final String id, final Continuation<? super TaskEntity> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TaskEntity>() {
      @Override
      @Nullable
      public TaskEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TaskEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object search(final String query,
      final Continuation<? super List<TaskEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM tasks\n"
            + "        WHERE isDeleted = 0 AND (title LIKE '%' || ? || '%' OR description LIKE '%' || ? || '%')\n"
            + "        ORDER BY dueDateEpochDay ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countCompletedBetween(final long startMillis, final long endMillis,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE isDeleted = 0 AND isCompleted = 1 AND completedAtEpochMillis BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startMillis);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endMillis);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> observeCountForDay(final long epochDay) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE isDeleted = 0 AND dueDateEpochDay = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> observeCompletedCountForDay(final long epochDay) {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE isDeleted = 0 AND dueDateEpochDay = ? AND isCompleted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCreatedBetween(final long startMillis, final long endMillis,
      final Continuation<? super List<TaskEntity>> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE isDeleted = 0 AND createdAt BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startMillis);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endMillis);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllForBackup(final Continuation<? super List<TaskEntity>> $completion) {
    final String _sql = "SELECT * FROM tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TaskEntity>>() {
      @Override
      @NonNull
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDueDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDateEpochDay");
          final int _cursorIndexOfDueTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTimeMinutes");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfRepeatDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDaysCsv");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Long _tmpDueDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfDueDateEpochDay)) {
              _tmpDueDateEpochDay = null;
            } else {
              _tmpDueDateEpochDay = _cursor.getLong(_cursorIndexOfDueDateEpochDay);
            }
            final Integer _tmpDueTimeMinutes;
            if (_cursor.isNull(_cursorIndexOfDueTimeMinutes)) {
              _tmpDueTimeMinutes = null;
            } else {
              _tmpDueTimeMinutes = _cursor.getInt(_cursorIndexOfDueTimeMinutes);
            }
            final TaskPriority _tmpPriority;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final RepeatRule _tmpRepeatRule;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRepeatRule);
            _tmpRepeatRule = __converters.toRepeatRule(_tmp_1);
            final String _tmpRepeatDaysCsv;
            if (_cursor.isNull(_cursorIndexOfRepeatDaysCsv)) {
              _tmpRepeatDaysCsv = null;
            } else {
              _tmpRepeatDaysCsv = _cursor.getString(_cursorIndexOfRepeatDaysCsv);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Long _tmpCompletedAtEpochMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedAtEpochMillis)) {
              _tmpCompletedAtEpochMillis = null;
            } else {
              _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            }
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSourceId;
            if (_cursor.isNull(_cursorIndexOfSourceId)) {
              _tmpSourceId = null;
            } else {
              _tmpSourceId = _cursor.getString(_cursorIndexOfSourceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TaskEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpDueDateEpochDay,_tmpDueTimeMinutes,_tmpPriority,_tmpCategory,_tmpReminderEpochMillis,_tmpRepeatRule,_tmpRepeatDaysCsv,_tmpIsCompleted,_tmpCompletedAtEpochMillis,_tmpIsDeleted,_tmpNotes,_tmpAttachmentsJson,_tmpSourceType,_tmpSourceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
