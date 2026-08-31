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
import com.lifeos.app.data.db.entities.NoteEntity;
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
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NoteEntity> __insertionAdapterOfNoteEntity;

  private final EntityDeletionOrUpdateAdapter<NoteEntity> __updateAdapterOfNoteEntity;

  private final SharedSQLiteStatement __preparedStmtOfSetPinned;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  private final SharedSQLiteStatement __preparedStmtOfSetArchived;

  private final SharedSQLiteStatement __preparedStmtOfSetDeleted;

  private final SharedSQLiteStatement __preparedStmtOfHardDelete;

  public NoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNoteEntity = new EntityInsertionAdapter<NoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notes` (`id`,`title`,`contentJson`,`plainTextForSearch`,`folder`,`tagsCsv`,`isPinned`,`isFavorite`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`attachmentsJson`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NoteEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getContentJson());
        statement.bindString(4, entity.getPlainTextForSearch());
        if (entity.getFolder() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFolder());
        }
        statement.bindString(6, entity.getTagsCsv());
        final int _tmp = entity.isPinned() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.isArchived() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        final int _tmp_3 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(10, _tmp_3);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindString(13, entity.getAttachmentsJson());
      }
    };
    this.__updateAdapterOfNoteEntity = new EntityDeletionOrUpdateAdapter<NoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `notes` SET `id` = ?,`title` = ?,`contentJson` = ?,`plainTextForSearch` = ?,`folder` = ?,`tagsCsv` = ?,`isPinned` = ?,`isFavorite` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`attachmentsJson` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NoteEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getContentJson());
        statement.bindString(4, entity.getPlainTextForSearch());
        if (entity.getFolder() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFolder());
        }
        statement.bindString(6, entity.getTagsCsv());
        final int _tmp = entity.isPinned() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.isArchived() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        final int _tmp_3 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(10, _tmp_3);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindString(13, entity.getAttachmentsJson());
        statement.bindString(14, entity.getId());
      }
    };
    this.__preparedStmtOfSetPinned = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE notes SET isPinned = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE notes SET isFavorite = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetArchived = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE notes SET isArchived = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetDeleted = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE notes SET isDeleted = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfHardDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM notes WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final NoteEntity note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNoteEntity.insert(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final NoteEntity note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNoteEntity.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setPinned(final String id, final boolean pinned, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetPinned.acquire();
        int _argIndex = 1;
        final int _tmp = pinned ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfSetPinned.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setFavorite(final String id, final boolean favorite, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
        int _argIndex = 1;
        final int _tmp = favorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfSetFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setArchived(final String id, final boolean archived, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetArchived.acquire();
        int _argIndex = 1;
        final int _tmp = archived ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfSetArchived.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setDeleted(final String id, final boolean deleted, final long now,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetDeleted.acquire();
        int _argIndex = 1;
        final int _tmp = deleted ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfSetDeleted.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object hardDelete(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfHardDelete.acquire();
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
          __preparedStmtOfHardDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<NoteEntity>> observeAll() {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND isArchived = 0 ORDER BY isPinned DESC, updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Object getById(final String id, final Continuation<? super NoteEntity> $completion) {
    final String _sql = "SELECT * FROM notes WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<NoteEntity>() {
      @Override
      @Nullable
      public NoteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final NoteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _result = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<NoteEntity> observeById(final String id) {
    final String _sql = "SELECT * FROM notes WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<NoteEntity>() {
      @Override
      @Nullable
      public NoteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final NoteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _result = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<NoteEntity>> observePinned() {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND isPinned = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<NoteEntity>> observeFavorites() {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND isFavorite = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<NoteEntity>> observeArchived() {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND isArchived = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<NoteEntity>> observeTrash() {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<NoteEntity>> observeByFolder(final String folder) {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND folder = ? ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, folder);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<List<String>> observeFolders() {
    final String _sql = "SELECT DISTINCT folder FROM notes WHERE folder IS NOT NULL AND isDeleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
  public Object search(final String query,
      final Continuation<? super List<NoteEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM notes\n"
            + "        WHERE isDeleted = 0 AND (title LIKE '%' || ? || '%' OR plainTextForSearch LIKE '%' || ? || '%')\n"
            + "        ORDER BY updatedAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Flow<Integer> observeCount() {
    final String _sql = "SELECT COUNT(*) FROM notes WHERE isDeleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"notes"}, new Callable<Integer>() {
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
      final Continuation<? super List<NoteEntity>> $completion) {
    final String _sql = "SELECT * FROM notes WHERE isDeleted = 0 AND createdAt BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startMillis);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endMillis);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
  public Object getAllForBackup(final Continuation<? super List<NoteEntity>> $completion) {
    final String _sql = "SELECT * FROM notes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NoteEntity>>() {
      @Override
      @NonNull
      public List<NoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContentJson = CursorUtil.getColumnIndexOrThrow(_cursor, "contentJson");
          final int _cursorIndexOfPlainTextForSearch = CursorUtil.getColumnIndexOrThrow(_cursor, "plainTextForSearch");
          final int _cursorIndexOfFolder = CursorUtil.getColumnIndexOrThrow(_cursor, "folder");
          final int _cursorIndexOfTagsCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsCsv");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfAttachmentsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentsJson");
          final List<NoteEntity> _result = new ArrayList<NoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NoteEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpContentJson;
            _tmpContentJson = _cursor.getString(_cursorIndexOfContentJson);
            final String _tmpPlainTextForSearch;
            _tmpPlainTextForSearch = _cursor.getString(_cursorIndexOfPlainTextForSearch);
            final String _tmpFolder;
            if (_cursor.isNull(_cursorIndexOfFolder)) {
              _tmpFolder = null;
            } else {
              _tmpFolder = _cursor.getString(_cursorIndexOfFolder);
            }
            final String _tmpTagsCsv;
            _tmpTagsCsv = _cursor.getString(_cursorIndexOfTagsCsv);
            final boolean _tmpIsPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final boolean _tmpIsDeleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDeleted);
            _tmpIsDeleted = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpAttachmentsJson;
            _tmpAttachmentsJson = _cursor.getString(_cursorIndexOfAttachmentsJson);
            _item = new NoteEntity(_tmpId,_tmpTitle,_tmpContentJson,_tmpPlainTextForSearch,_tmpFolder,_tmpTagsCsv,_tmpIsPinned,_tmpIsFavorite,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpAttachmentsJson);
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
