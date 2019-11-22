package com.elliott.searchactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// SQLite 를 사용하여 Mobile Database를 구축한 클래스이다.
// Add, getALL Item 두 가지 쿼리를 구현.
public class DatabaseHelper extends SQLiteOpenHelper {
    // 데이터베이스 버전 : 의미 없음 (신경 안써도 됨)
    private static final int DATABASE_VERSION = 1;
    // 데이터베이스 이름
    private static final String DATABASE_NAME = "SearchRecord";
    // 데이터베이스 테이블명
    private static final String TABLE_SEARCH = "Search";

    // 데이터베이스 테이블의 각 컬럼명
    private static final String KEY_ID = "id";
    private static final String KEY_KEYWORD = "keyword";
    private static final String KEY_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스 테이블을 만드는 부분이다.
        String CREATE_TABLE_SEARCH =
                "CREATE TABLE " + TABLE_SEARCH+ "(" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_KEYWORD + " TEXT NOT NULL, " +
                        KEY_TIME + " TEXT NOT NULL" +
                        ");";
        db.execSQL(CREATE_TABLE_SEARCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스가 업그레이드 되거나 변경이 될 경우 (버전 등) 테이블을 지우고 새로 생성한다.
        // DROP TABLE - SQL QUERY
        String DROP_TABLE_DRINK =
                "DROP TABLE IF EXISTS " + TABLE_SEARCH;
        db.execSQL(DROP_TABLE_DRINK);

        onCreate(db);
    }

    // 검색 시 검색어 등록하는 메소드
    // 검색 시간이 있으면 더 좋을 것 같아 추가로 더 구현.
    public void add(String keyword, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 검색어와 검색 시간을 설정한다.
        ContentValues values = new ContentValues();
        values.put(KEY_KEYWORD, keyword);
        values.put(KEY_TIME, time);

        // 데이터베이스 테이블에 추가한다.
        db.insert(TABLE_SEARCH, null, values);
        db.close();
    }

    // 모든 검색 기록을 불러오는 메소드
    public List<Record> getAll() {
        List<Record> recordList = new ArrayList<>();

        // SQL 쿼리문 : 모든 아이템을 가져온다.
        String SELECT_ALL = "SELECT * FROM " + TABLE_SEARCH + " ORDER BY " + KEY_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        // 커서라는 친구가 하나씩 돌면서 튜플 정보를 가져온다.
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                // 데이터를 가져오면 리스트에 하나씩 추가하게 된다.
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setKeyword(cursor.getString(1));
                record.setTime(cursor.getString(2));
                recordList.add(record);
            } while (cursor.moveToNext());
        }

        // 최종적으로 가져온 리스트를 반환한다.
        return recordList;
    }
}