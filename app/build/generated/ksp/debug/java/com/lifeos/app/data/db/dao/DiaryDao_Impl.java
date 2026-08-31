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
import com.lifeos.app.data.db.entities.DiaryEntity;
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
public final class DiaryDao_Impl implements DiaryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DiaryEntity> __insertionAdapterOfDiaryEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfMarkReviewed;

  public DiaryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDiaryEntity = new EntityInsertionAdapter<DiaryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `diary_entries` (`id`,`title`,`content`,`mood`,`tagsCsv`,`dateEpochDay`,`timeMinutes`,`aiGenerated`,`isReviewed`,`attachmentsJson`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DiaryEntity entity) {
        statement.bindString(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        statement.bindString(3, entity.getContent());
        if (entity.getMood() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMood());
        }
        statement.bindString(5, entity.getTagsCsv());
        statement.bindLong(6, entity.getDateEpochDay());
        statement.bindLong(7, entity.getTimeMinutes());
        final int _tmp = entity.getAiGenerated() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isReviewed() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindString(10, entity.getAttachmentsJson());
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM diary_entries WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkReviewed = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE diary_entries SET isReviewed = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final DiaryEntity entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDiaryEntity.insert(entry);
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
  public Object markReviewed(final String id, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkReviewed.acquire();
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
          __preparedStmtOfMarkReviewed.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DiaryEntity>> observeAll() {
    final String _sql = "SELECT * FROM diary_entries ORDER BY dateEpochDay DESC, timeMinutes DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diary_entries"}, new Callable<List<DiaryEntity>>() {
      @Override
      @NonNull
      public List<DiaryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DiaryEntity> _result = new ArrayList<DiaryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DiaryEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<DiaryEntity>> observeForDay(final long epochDay) {
    final String _sql = "SELECT * FROM diary_entries WHERE dateEpochDay = ? ORDER BY timeMinutes DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, epochDay);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diary_entries"}, new Callable<List<DiaryEntity>>() {
      @Override
      @NonNull
      public List<DiaryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DiaryEntity> _result = new ArrayList<DiaryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DiaryEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getById(final String id, final Continuation<? super DiaryEntity> $completion) {
    final String _sql = "SELECT * FROM diary_entries WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DiaryEntity>() {
      @Override
      @Nullable
      public DiaryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final DiaryEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<DiaryEntity>> observeUnreviewedAiDrafts() {
    final String _sql = "SELECT * FROM diary_entries WHERE aiGenerated = 1 AND isReviewed = 0 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diary_entries"}, new Callable<List<DiaryEntity>>() {
      @Override
      @NonNull
      public List<DiaryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DiaryEntity> _result = new ArrayList<DiaryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DiaryEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object countInRange(final long startEpochDay, final long endEpochDay,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM diary_entries WHERE dateEpochDay BETWEEN ? AND ?";
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
      final Continuation<? super List<DiaryEntity>> $completion) {
    final String _sql = "SELECT * FROM diary_entries WHERE content LIKE '%' || ? || '%' OR title LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DiaryEntity>>() {
      @Override
      @NonNull
      public List<DiaryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DiaryEntity> _result = new ArrayList<DiaryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DiaryEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getLastEntryEpochDay(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(dateEpochDay) FROM diary_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
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
  public Object getAllForBackup(final Continuation<? super List<DiaryEntity>> $completion) {
    final String _sql = "SELECT * FROM diary_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DiaryEntity>>() {
      @Override
      @NonNull
      public List<DiaryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfTimeMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeMinutes");
          final int _cursorIndexOfAiGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "aiGenerated");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DiaryEntity> _result = new ArrayList<DiaryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DiaryEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
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
            final boolean _tmpAiGenerated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAiGenerated);
            _tmpAiGenerated = _tmp != 0;
            final boolean _tmpIsReviewed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp_1 != 0;
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DiaryEntity(_tmpId,_tmpTitle,_tmpContent,_tmpMood,_tmpTagsCsv,_tmpDateEpochDay,_tmpTimeMinutes,_tmpAiGenerated,_tmpIsReviewed,_tmpAttachmentsJson,_tmpCreatedAt,_tmpUpdatedAt);
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
