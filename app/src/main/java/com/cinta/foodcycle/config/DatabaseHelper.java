package com.cinta.foodcycle.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nama Tabel
    public static final String TBL_LOGIN = "tbl_login";
    public static final String ID_LOGIN = "ID";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    static final String DB_NAME = "food cycle";
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_login (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_LOGIN);
        onCreate(db);
    }
}

