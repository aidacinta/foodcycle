package com.cinta.foodcycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cinta.foodcycle.config.DatabaseHelper;
import com.cinta.foodcycle.config.DatabaseHelper;

public class UserModel {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public UserModel(Context c) {
        context = c;
    }
    public UserModel open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public void addUser(String user, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.USERNAME, user);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        database.insert(DatabaseHelper.TBL_LOGIN, null, contentValue);
    }
    public boolean checkuser(String username, String password) {
        String[] columns = { DatabaseHelper.ID_LOGIN } ;
        String selection = DatabaseHelper.USERNAME + "=?" + " and " +
                DatabaseHelper.PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = database.query(DatabaseHelper.TBL_LOGIN, columns,
                selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        if (count>0)
            return true;
        else
            return false;
    }
}
