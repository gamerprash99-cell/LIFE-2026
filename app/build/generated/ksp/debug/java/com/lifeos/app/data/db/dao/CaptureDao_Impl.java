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
import com.lifeos.app.data.db.entities.CaptureEntity;
import com.lifeos.app.data.db.entities.CaptureType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class CaptureDao_Impl implements CaptureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CaptureEntity> __insertionAdapterOfCaptureEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public CaptureDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCaptureEntity = new EntityInsertionAdapter<CaptureEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `captures` (`id`,`type`,`filePath`,`thumbnailPath`,`caption`,`transcript`,`mood`,`tagsCsv`,`dateEpochDay`,`timeMinutes`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CaptureEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromCaptureType(entity.getType());
        statement.bindString(2, _tmp);
        if (entity.getFilePath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFilePath());
        }
        if (entity.getThumbnailPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getThumbnailPath());
        }
        if (entity.getCaption() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCaption());
        }
        if (entity.getTranscript() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getTranscript());
        }
        if (entity.getMood() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMood());
        }
        statement.bindString(8, entity.getTagsCsv());
        statement.bindLong(9, entity.getDateEpochDay());
        statement.bindLong(10, entity.getTimeMinutes());
        statement.bindLong(11, entity.getCreatedAt());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM captures WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final CaptureEntity capture, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCaptureEntity.insert(capture);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
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
  public Flow<CaptureEntity> observeLatest() {
    final String _sql = "SELECT * FROM captures ORDER BY createdAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"captures"}, new Callable<CaptureEntity>() {
      @Override
      @Nullable
      public CaptureEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final CaptureEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
  public Flow<List<CaptureEntity>> observeAll() {
    final String _sql = "SELECT * FROM captures ORDER BY dateEpochDay DESC, timeMinutes DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"captures"}, new Callable<List<CaptureEntity>>() {
      @Override
      @NonNull
      public List<CaptureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CaptureEntity> _result = new ArrayList<CaptureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CaptureEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
  public Flow<List<CaptureEntity>> observeForDay(final long epochDay) {
    final String _sql = "SELECT * FROM captures WHERE dateEpochDay = ? ORDER BY timeMinutes DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"captures"}, new Callable<List<CaptureEntity>>() {
      @Override
      @NonNull
      public List<CaptureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CaptureEntity> _result = new ArrayList<CaptureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CaptureEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
  public Object getById(final String id, final Continuation<? super CaptureEntity> $completion) {
    final String _sql = "SELECT * FROM captures WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CaptureEntity>() {
      @Override
      @Nullable
      public CaptureEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final CaptureEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
  public Object countInRange(final long startEpochDay, final long endEpochDay,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM captures WHERE dateEpochDay BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startEpochDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endEpochDay);
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
  public Object search(final String query,
      final Continuation<? super List<CaptureEntity>> $completion) {
    final String _sql = "SELECT * FROM captures WHERE caption LIKE '%' || ? || '%' OR transcript LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CaptureEntity>>() {
      @Override
      @NonNull
      public List<CaptureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CaptureEntity> _result = new ArrayList<CaptureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CaptureEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
  public Object getAllForBackup(final Continuation<? super List<CaptureEntity>> $completion) {
    final String _sql = "SELECT * FROM captures";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CaptureEntity>>() {
      @Override
      @NonNull
      public List<CaptureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfThumbnailPath = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailPath");
          final int _cursorIndexOfCaption = CursorUtil.getColumnIndexOrThrow(_cursor, "caption");
          final int _cursorIndexOfTranscript = CursorUtil.getColumnIndexOrThrow(_cursor, "transcript");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CaptureEntity> _result = new ArrayList<CaptureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CaptureEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final CaptureType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toCaptureType(_tmp);
            final String _tmpFilePath;
            if (_cursor.isNull(_cursorIndexOfFilePath)) {
              _tmpFilePath = null;
            } else {
              _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            }
            final String _tmpThumbnailPath;
            if (_cursor.isNull(_cursorIndexOfThumbnailPath)) {
              _tmpThumbnailPath = null;
            } else {
              _tmpThumbnailPath = _cursor.getString(_cursorIndexOfThumbnailPath);
            }
            final String _tmpCaption;
            if (_cursor.isNull(_cursorIndexOfCaption)) {
              _tmpCaption = null;
            } else {
              _tmpCaption = _cursor.getString(_cursorIndexOfCaption);
            }
            final String _tmpTranscript;
            if (_cursor.isNull(_cursorIndexOfTranscript)) {
              _tmpTranscript = null;
            } else {
              _tmpTranscript = _cursor.getString(_cursorIndexOfTranscript);
            }
            final String _tmpMood;
            if (_cursor.isNull(_cursorIndexOfMood)) {
              _tmpMood = null;
            } else {
              _tmpMood = _cursor.getString(_cursorIndexOfMood);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final int _tmpTimeMinutes;
            _tmpTimeMinutes = _cursor.getInt(_cursorIndexOfTimeMinutes);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new CaptureEntity(_tmpId,_tmpType,_tmpFilePath,_tmpThumbnailPath,_tmpCaption,_tmpTranscript,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpCreatedAt);
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
