package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(final SupportSQLiteQuery query) {
        String _tmpId;
        WorkInfo.State _tmpState;
        byte[] _tmp_1;
        Data _tmpOutput;
        int _tmpRunAttemptCount;
        int _tmpGeneration;
        ArrayList<String> _tmpTagsCollection_1;
        ArrayList<Data> _tmpProgressCollection_1;
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, query, true, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndex(_cursor, Constant.VISIT_ID);
            int _cursorIndexOfState = CursorUtil.getColumnIndex(_cursor, "state");
            int _cursorIndexOfOutput = CursorUtil.getColumnIndex(_cursor, "output");
            int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndex(_cursor, "run_attempt_count");
            int _cursorIndexOfGeneration = CursorUtil.getColumnIndex(_cursor, "generation");
            ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
            ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
            while (_cursor.moveToNext()) {
                String _tmpKey = _cursor.getString(_cursorIndexOfId);
                ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                if (_tmpTagsCollection == null) {
                    ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                    _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                }
                String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                if (_tmpProgressCollection == null) {
                    ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                    _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
            __fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
            List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                if (_cursorIndexOfId == -1) {
                    _tmpId = null;
                } else if (_cursor.isNull(_cursorIndexOfId)) {
                    _tmpId = null;
                } else {
                    _tmpId = _cursor.getString(_cursorIndexOfId);
                }
                if (_cursorIndexOfState == -1) {
                    _tmpState = null;
                } else {
                    int _tmp = _cursor.getInt(_cursorIndexOfState);
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    _tmpState = WorkTypeConverters.intToState(_tmp);
                }
                if (_cursorIndexOfOutput == -1) {
                    _tmpOutput = null;
                } else {
                    if (_cursor.isNull(_cursorIndexOfOutput)) {
                        _tmp_1 = null;
                    } else {
                        _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                    }
                    _tmpOutput = Data.fromByteArray(_tmp_1);
                }
                if (_cursorIndexOfRunAttemptCount == -1) {
                    _tmpRunAttemptCount = 0;
                } else {
                    int _tmpRunAttemptCount2 = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                    _tmpRunAttemptCount = _tmpRunAttemptCount2;
                }
                if (_cursorIndexOfGeneration == -1) {
                    _tmpGeneration = 0;
                } else {
                    int _tmpGeneration2 = _cursor.getInt(_cursorIndexOfGeneration);
                    _tmpGeneration = _tmpGeneration2;
                }
                String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                ArrayList<String> _tmpTagsCollection_12 = _collectionTags.get(_tmpKey_2);
                if (_tmpTagsCollection_12 != null) {
                    _tmpTagsCollection_1 = _tmpTagsCollection_12;
                } else {
                    _tmpTagsCollection_1 = new ArrayList<>();
                }
                String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                ArrayList<Data> _tmpProgressCollection_12 = _collectionProgress.get(_tmpKey_3);
                if (_tmpProgressCollection_12 != null) {
                    _tmpProgressCollection_1 = _tmpProgressCollection_12;
                } else {
                    _tmpProgressCollection_1 = new ArrayList<>();
                }
                String _tmpKey_32 = _tmpId;
                WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo(_tmpKey_32, _tmpState, _tmpOutput, _tmpRunAttemptCount, _tmpGeneration, _tmpTagsCollection_1, _tmpProgressCollection_1);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                String _tmpId;
                WorkInfo.State _tmpState;
                byte[] _tmp_1;
                Data _tmpOutput;
                int _tmpRunAttemptCount;
                int _tmpGeneration;
                ArrayList<String> _tmpTagsCollection_1;
                ArrayList<Data> _tmpProgressCollection_1;
                Cursor _cursor = DBUtil.query(RawWorkInfoDao_Impl.this.__db, query, true, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndex(_cursor, Constant.VISIT_ID);
                    int _cursorIndexOfState = CursorUtil.getColumnIndex(_cursor, "state");
                    int _cursorIndexOfOutput = CursorUtil.getColumnIndex(_cursor, "output");
                    int _cursorIndexOfRunAttemptCount = CursorUtil.getColumnIndex(_cursor, "run_attempt_count");
                    int _cursorIndexOfGeneration = CursorUtil.getColumnIndex(_cursor, "generation");
                    ArrayMap<String, ArrayList<String>> _collectionTags = new ArrayMap<>();
                    ArrayMap<String, ArrayList<Data>> _collectionProgress = new ArrayMap<>();
                    while (_cursor.moveToNext()) {
                        String _tmpKey = _cursor.getString(_cursorIndexOfId);
                        ArrayList<String> _tmpTagsCollection = _collectionTags.get(_tmpKey);
                        if (_tmpTagsCollection == null) {
                            ArrayList<String> _tmpTagsCollection2 = new ArrayList<>();
                            _collectionTags.put(_tmpKey, _tmpTagsCollection2);
                        }
                        String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<Data> _tmpProgressCollection = _collectionProgress.get(_tmpKey_1);
                        if (_tmpProgressCollection == null) {
                            ArrayList<Data> _tmpProgressCollection2 = new ArrayList<>();
                            _collectionProgress.put(_tmpKey_1, _tmpProgressCollection2);
                        }
                    }
                    _cursor.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(_collectionTags);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(_collectionProgress);
                    List<WorkSpec.WorkInfoPojo> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        if (_cursorIndexOfId == -1) {
                            _tmpId = null;
                        } else if (_cursor.isNull(_cursorIndexOfId)) {
                            _tmpId = null;
                        } else {
                            _tmpId = _cursor.getString(_cursorIndexOfId);
                        }
                        if (_cursorIndexOfState == -1) {
                            _tmpState = null;
                        } else {
                            int _tmp = _cursor.getInt(_cursorIndexOfState);
                            WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                            _tmpState = WorkTypeConverters.intToState(_tmp);
                        }
                        if (_cursorIndexOfOutput == -1) {
                            _tmpOutput = null;
                        } else {
                            if (_cursor.isNull(_cursorIndexOfOutput)) {
                                _tmp_1 = null;
                            } else {
                                _tmp_1 = _cursor.getBlob(_cursorIndexOfOutput);
                            }
                            _tmpOutput = Data.fromByteArray(_tmp_1);
                        }
                        if (_cursorIndexOfRunAttemptCount == -1) {
                            _tmpRunAttemptCount = 0;
                        } else {
                            int _tmpRunAttemptCount2 = _cursor.getInt(_cursorIndexOfRunAttemptCount);
                            _tmpRunAttemptCount = _tmpRunAttemptCount2;
                        }
                        if (_cursorIndexOfGeneration == -1) {
                            _tmpGeneration = 0;
                        } else {
                            int _tmpGeneration2 = _cursor.getInt(_cursorIndexOfGeneration);
                            _tmpGeneration = _tmpGeneration2;
                        }
                        String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<String> _tmpTagsCollection_12 = _collectionTags.get(_tmpKey_2);
                        if (_tmpTagsCollection_12 != null) {
                            _tmpTagsCollection_1 = _tmpTagsCollection_12;
                        } else {
                            _tmpTagsCollection_1 = new ArrayList<>();
                        }
                        String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                        ArrayList<Data> _tmpProgressCollection_12 = _collectionProgress.get(_tmpKey_3);
                        if (_tmpProgressCollection_12 != null) {
                            _tmpProgressCollection_1 = _tmpProgressCollection_12;
                        } else {
                            _tmpProgressCollection_1 = new ArrayList<>();
                        }
                        WorkSpec.WorkInfoPojo _item = new WorkSpec.WorkInfoPojo(_tmpId, _tmpState, _tmpOutput, _tmpRunAttemptCount, _tmpGeneration, _tmpTagsCollection_1, _tmpProgressCollection_1);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        String _item_1;
        Set<String> __mapKeySet = _map.keySet();
        if (__mapKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<String>> _tmpInnerMap = new ArrayMap<>(999);
            int _tmpIndex = 0;
            int _mapIndex = 0;
            int _limit = _map.getSize();
            while (_mapIndex < _limit) {
                _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
                _mapIndex++;
                _tmpIndex++;
                if (_tmpIndex == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(_tmpInnerMap);
                    _tmpInnerMap = new ArrayMap<>(999);
                    _tmpIndex = 0;
                }
            }
            if (_tmpIndex > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(_tmpInnerMap);
                return;
            }
            return;
        }
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int _inputSize = __mapKeySet.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : __mapKeySet) {
            if (_item == null) {
                _stmt.bindNull(_argIndex);
            } else {
                _stmt.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        Cursor _cursor = DBUtil.query(this.__db, _stmt, false, null);
        try {
            int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "work_spec_id");
            if (_itemKeyIndex == -1) {
                return;
            }
            while (_cursor.moveToNext()) {
                String _tmpKey = _cursor.getString(_itemKeyIndex);
                ArrayList<String> _tmpRelation = _map.get(_tmpKey);
                if (_tmpRelation != null) {
                    if (_cursor.isNull(0)) {
                        _item_1 = null;
                    } else {
                        _item_1 = _cursor.getString(0);
                    }
                    _tmpRelation.add(_item_1);
                }
            }
        } finally {
            _cursor.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        byte[] _tmp;
        Set<String> __mapKeySet = _map.keySet();
        if (__mapKeySet.isEmpty()) {
            return;
        }
        if (_map.getSize() > 999) {
            ArrayMap<String, ArrayList<Data>> _tmpInnerMap = new ArrayMap<>(999);
            int _tmpIndex = 0;
            int _mapIndex = 0;
            int _limit = _map.getSize();
            while (_mapIndex < _limit) {
                _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
                _mapIndex++;
                _tmpIndex++;
                if (_tmpIndex == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(_tmpInnerMap);
                    _tmpInnerMap = new ArrayMap<>(999);
                    _tmpIndex = 0;
                }
            }
            if (_tmpIndex > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(_tmpInnerMap);
                return;
            }
            return;
        }
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int _inputSize = __mapKeySet.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        String _sql = _stringBuilder.toString();
        int _argCount = _inputSize + 0;
        RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
        int _argIndex = 1;
        for (String _item : __mapKeySet) {
            if (_item == null) {
                _stmt.bindNull(_argIndex);
            } else {
                _stmt.bindString(_argIndex, _item);
            }
            _argIndex++;
        }
        Cursor _cursor = DBUtil.query(this.__db, _stmt, false, null);
        try {
            int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "work_spec_id");
            if (_itemKeyIndex == -1) {
                return;
            }
            while (_cursor.moveToNext()) {
                String _tmpKey = _cursor.getString(_itemKeyIndex);
                ArrayList<Data> _tmpRelation = _map.get(_tmpKey);
                if (_tmpRelation != null) {
                    if (_cursor.isNull(0)) {
                        _tmp = null;
                    } else {
                        _tmp = _cursor.getBlob(0);
                    }
                    Data _item_1 = Data.fromByteArray(_tmp);
                    _tmpRelation.add(_item_1);
                }
            }
        } finally {
            _cursor.close();
        }
    }
}
