package com.aptech.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diep.Tran on 5/5/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "example";
    public static final int DB_VERSION = 2;

    private static DBHelper instance = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //ham nay chi dc goi duy nhat 1 lan : khi chung ta cai app
        //create database
        sqLiteDatabase.execSQL(Student.CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //goi bao day - khi co thay doi version

    }
}
