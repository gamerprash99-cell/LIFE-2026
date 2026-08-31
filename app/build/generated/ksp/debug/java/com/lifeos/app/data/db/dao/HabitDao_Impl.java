package com.lifeos.app.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lifeos.app.data.db.Converters;
import com.lifeos.app.data.db.entities.HabitEntity;
import com.lifeos.app.data.db.entities.HabitFrequency;
import java.lang.Class;
import java.lang.Exception;
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
public final class HabitDao_Impl implements HabitDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HabitEntity> __insertionAdapterOfHabitEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfArchive;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public HabitDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHabitEntity = new EntityInsertionAdapter<HabitEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `habits` (`id`,`name`,`icon`,`category`,`frequency`,`customDaysCsv`,`goalCount`,`reminderEpochMillis`,`startDateEpochDay`,`isArchived`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HabitEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getIcon());
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        final String _tmp = __converters.fromFrequency(entity.getFrequency());
        statement.bindString(5, _tmp);
        if (entity.getCustomDaysCsv() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCustomDaysCsv());
        }
        statement.bindLong(7, entity.getGoalCount());
        if (entity.getReminderEpochMillis() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getReminderEpochMillis());
        }
        statement.bindLong(9, entity.getStartDateEpochDay());
        final int _tmp_1 = entity.isArchived() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfArchive = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE habits SET isArchived = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM habits WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final HabitEntity habit, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHabitEntity.insert(habit);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object archive(final String id, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfArchive.acquire();
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
          __preparedStmtOfArchive.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HabitEntity>> observeAll() {
    final String _sql = "SELECT * FROM habits WHERE isArchived = 0 ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"habits"}, new Callable<List<HabitEntity>>() {
      @Override
      @NonNull
      public List<HabitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCustomDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "customDaysCsv");
          final int _cursorIndexOfGoalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "goalCount");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateEpochDay");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<HabitEntity> _result = new ArrayList<HabitEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final HabitFrequency _tmpFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfFrequency);
            _tmpFrequency = __converters.toFrequency(_tmp);
            final String _tmpCustomDaysCsv;
            if (_cursor.isNull(_cursorIndexOfCustomDaysCsv)) {
              _tmpCustomDaysCsv = null;
            } else {
              _tmpCustomDaysCsv = _cursor.getString(_cursorIndexOfCustomDaysCsv);
            }
            final int _tmpGoalCount;
            _tmpGoalCount = _cursor.getInt(_cursorIndexOfGoalCount);
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final boolean _tmpIsArchived;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new HabitEntity(_tmpId,_tmpName,_tmpIcon,_tmpCategory,_tmpFrequency,_tmpCustomDaysCsv,_tmpGoalCount,_tmpReminderEpochMillis,_tmpStartDateEpochDay,_tmpIsArchived,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getById(final String id, final Continuation<? super HabitEntity> $completion) {
    final String _sql = "SELECT * FROM habits WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HabitEntity>() {
      @Override
      @Nullable
      public HabitEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCustomDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "customDaysCsv");
          final int _cursorIndexOfGoalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "goalCount");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateEpochDay");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final HabitEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final HabitFrequency _tmpFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfFrequency);
            _tmpFrequency = __converters.toFrequency(_tmp);
            final String _tmpCustomDaysCsv;
            if (_cursor.isNull(_cursorIndexOfCustomDaysCsv)) {
              _tmpCustomDaysCsv = null;
            } else {
              _tmpCustomDaysCsv = _cursor.getString(_cursorIndexOfCustomDaysCsv);
            }
            final int _tmpGoalCount;
            _tmpGoalCount = _cursor.getInt(_cursorIndexOfGoalCount);
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final boolean _tmpIsArchived;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new HabitEntity(_tmpId,_tmpName,_tmpIcon,_tmpCategory,_tmpFrequency,_tmpCustomDaysCsv,_tmpGoalCount,_tmpReminderEpochMillis,_tmpStartDateEpochDay,_tmpIsArchived,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<HabitEntity> observeById(final String id) {
    final String _sql = "SELECT * FROM habits WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"habits"}, new Callable<HabitEntity>() {
      @Override
      @Nullable
      public HabitEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCustomDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "customDaysCsv");
          final int _cursorIndexOfGoalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "goalCount");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateEpochDay");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final HabitEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final HabitFrequency _tmpFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfFrequency);
            _tmpFrequency = __converters.toFrequency(_tmp);
            final String _tmpCustomDaysCsv;
            if (_cursor.isNull(_cursorIndexOfCustomDaysCsv)) {
              _tmpCustomDaysCsv = null;
            } else {
              _tmpCustomDaysCsv = _cursor.getString(_cursorIndexOfCustomDaysCsv);
            }
            final int _tmpGoalCount;
            _tmpGoalCount = _cursor.getInt(_cursorIndexOfGoalCount);
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final boolean _tmpIsArchived;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new HabitEntity(_tmpId,_tmpName,_tmpIcon,_tmpCategory,_tmpFrequency,_tmpCustomDaysCsv,_tmpGoalCount,_tmpReminderEpochMillis,_tmpStartDateEpochDay,_tmpIsArchived,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
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
  public Object getAllForBackup(final Continuation<? super List<HabitEntity>> $completion) {
    final String _sql = "SELECT * FROM habits";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HabitEntity>>() {
      @Override
      @NonNull
      public List<HabitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCustomDaysCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "customDaysCsv");
          final int _cursorIndexOfGoalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "goalCount");
          final int _cursorIndexOfReminderEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEpochMillis");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateEpochDay");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<HabitEntity> _result = new ArrayList<HabitEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final HabitFrequency _tmpFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfFrequency);
            _tmpFrequency = __converters.toFrequency(_tmp);
            final String _tmpCustomDaysCsv;
            if (_cursor.isNull(_cursorIndexOfCustomDaysCsv)) {
              _tmpCustomDaysCsv = null;
            } else {
              _tmpCustomDaysCsv = _cursor.getString(_cursorIndexOfCustomDaysCsv);
            }
            final int _tmpGoalCount;
            _tmpGoalCount = _cursor.getInt(_cursorIndexOfGoalCount);
            final Long _tmpReminderEpochMillis;
            if (_cursor.isNull(_cursorIndexOfReminderEpochMillis)) {
              _tmpReminderEpochMillis = null;
            } else {
              _tmpReminderEpochMillis = _cursor.getLong(_cursorIndexOfReminderEpochMillis);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final boolean _tmpIsArchived;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new HabitEntity(_tmpId,_tmpName,_tmpIcon,_tmpCategory,_tmpFrequency,_tmpCustomDaysCsv,_tmpGoalCount,_tmpReminderEpochMillis,_tmpStartDateEpochDay,_tmpIsArchived,_tmpCreatedAt,_tmpUpdatedAt);
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
