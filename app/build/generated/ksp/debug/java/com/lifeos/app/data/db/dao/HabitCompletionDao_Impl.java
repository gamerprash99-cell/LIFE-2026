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
import com.lifeos.app.data.db.entities.HabitCompletionEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class HabitCompletionDao_Impl implements HabitCompletionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HabitCompletionEntity> __insertionAdapterOfHabitCompletionEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public HabitCompletionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHabitCompletionEntity = new EntityInsertionAdapter<HabitCompletionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `habit_completions` (`habitId`,`dateEpochDay`,`progressCount`,`completedAtEpochMillis`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HabitCompletionEntity entity) {
        statement.bindString(1, entity.getHabitId());
        statement.bindLong(2, entity.getDateEpochDay());
        statement.bindLong(3, entity.getProgressCount());
        statement.bindLong(4, entity.getCompletedAtEpochMillis());
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM habit_completions WHERE habitId = ? AND dateEpochDay = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final HabitCompletionEntity completion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHabitCompletionEntity.insert(completion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clear(final String habitId, final long epochDay,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, habitId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, epochDay);
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
          __preparedStmtOfClear.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object get(final String habitId, final long epochDay,
      final Continuation<? super HabitCompletionEntity> $completion) {
    final String _sql = "SELECT * FROM habit_completions WHERE habitId = ? AND dateEpochDay = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, habitId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, epochDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HabitCompletionEntity>() {
      @Override
      @Nullable
      public HabitCompletionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final HabitCompletionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _result = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Flow<HabitCompletionEntity> observe(final String habitId, final long epochDay) {
    final String _sql = "SELECT * FROM habit_completions WHERE habitId = ? AND dateEpochDay = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, habitId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"habit_completions"}, new Callable<HabitCompletionEntity>() {
      @Override
      @Nullable
      public HabitCompletionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final HabitCompletionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _result = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Flow<List<HabitCompletionEntity>> observeAllForHabit(final String habitId) {
    final String _sql = "SELECT * FROM habit_completions WHERE habitId = ? ORDER BY dateEpochDay ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, habitId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"habit_completions"}, new Callable<List<HabitCompletionEntity>>() {
      @Override
      @NonNull
      public List<HabitCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final List<HabitCompletionEntity> _result = new ArrayList<HabitCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitCompletionEntity _item;
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _item = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Object getForHabitInRange(final String habitId, final long startEpochDay,
      final long endEpochDay, final Continuation<? super List<HabitCompletionEntity>> $completion) {
    final String _sql = "SELECT * FROM habit_completions WHERE habitId = ? AND dateEpochDay BETWEEN ? AND ? ORDER BY dateEpochDay ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, habitId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startEpochDay);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endEpochDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HabitCompletionEntity>>() {
      @Override
      @NonNull
      public List<HabitCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final List<HabitCompletionEntity> _result = new ArrayList<HabitCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitCompletionEntity _item;
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _item = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Flow<List<HabitCompletionEntity>> observeAllForDay(final long epochDay) {
    final String _sql = "SELECT * FROM habit_completions WHERE dateEpochDay = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"habit_completions"}, new Callable<List<HabitCompletionEntity>>() {
      @Override
      @NonNull
      public List<HabitCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final List<HabitCompletionEntity> _result = new ArrayList<HabitCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitCompletionEntity _item;
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _item = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Object getAllInRange(final long startEpochDay, final long endEpochDay,
      final Continuation<? super List<HabitCompletionEntity>> $completion) {
    final String _sql = "SELECT * FROM habit_completions WHERE dateEpochDay BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startEpochDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endEpochDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HabitCompletionEntity>>() {
      @Override
      @NonNull
      public List<HabitCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final List<HabitCompletionEntity> _result = new ArrayList<HabitCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitCompletionEntity _item;
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _item = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
  public Object getAllForBackup(
      final Continuation<? super List<HabitCompletionEntity>> $completion) {
    final String _sql = "SELECT * FROM habit_completions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HabitCompletionEntity>>() {
      @Override
      @NonNull
      public List<HabitCompletionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfProgressCount = CursorUtil.getColumnIndexOrThrow(_cursor, "progressCount");
          final int _cursorIndexOfCompletedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtEpochMillis");
          final List<HabitCompletionEntity> _result = new ArrayList<HabitCompletionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HabitCompletionEntity _item;
            final String _tmpHabitId;
            _tmpHabitId = _cursor.getString(_cursorIndexOfHabitId);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpProgressCount;
            _tmpProgressCount = _cursor.getInt(_cursorIndexOfProgressCount);
            final long _tmpCompletedAtEpochMillis;
            _tmpCompletedAtEpochMillis = _cursor.getLong(_cursorIndexOfCompletedAtEpochMillis);
            _item = new HabitCompletionEntity(_tmpHabitId,_tmpDateEpochDay,_tmpProgressCount,_tmpCompletedAtEpochMillis);
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
