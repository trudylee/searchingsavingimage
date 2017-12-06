package com.trudytyped.searchingsavingimage.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "image.db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "gallery";

    public static final String KEY_ROW_ID = "_id";

    public static final String KEY_URL = "url";

    public static final String[] TABLE_COLUMN = new String[]{KEY_ROW_ID, KEY_URL};

    private static final String DATABASE_CREATE =
            "create table gallery (_id integer primary key autoincrement, url text not null);";

    public static DatabaseManager databaseManager = null;

    private static SQLiteDatabase db;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(context);
            db = databaseManager.getWritableDatabase();
        }

        return databaseManager;
    }

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) throws SQLException {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void insertImageUrl(String url) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query
                (TABLE_NAME, TABLE_COLUMN, null, null, null, null, KEY_ROW_ID + " desc");

        if (cursor != null || !cursor.isFirst()) {
            cursor.moveToFirst();
        }

        int columnIndexForID = cursor.getColumnIndex(KEY_ROW_ID);
        int columnIndexForUrl = cursor.getColumnIndex(KEY_URL);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String insertedUrl = cursor.getString(columnIndexForUrl);
                if (insertedUrl.equals(url)) {
                    int rowID = cursor.getInt(columnIndexForID);
                    deleteDuplicate(rowID);
                }
            }
        }

        ContentValues values = new ContentValues();
        values.put("url", url);

        db.insert(TABLE_NAME, null, values);
    }

    private void deleteDuplicate(long rowID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ROW_ID + "=" + rowID, null);
    }

    public List<String> selectImageUrl() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> urls = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " order by " + KEY_ROW_ID + " desc", null);
        while (cursor.moveToNext()) {
            urls.add(cursor.getString(1));
        }
        return urls;
    }
}
