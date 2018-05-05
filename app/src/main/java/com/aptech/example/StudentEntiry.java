package com.aptech.example;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diep.Tran on 5/5/18.
 */

public class StudentEntiry {
    public static void insert(Student student) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Student.ID_FULL_NAME, student.getFullname());
        contentValues.put(Student.ID_EMAIL, student.getEmail());

        sqLiteDatabase.insert(Student.STUDENT_DB, null, contentValues);
    }

    public static Student findLast() {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        String query = "SELECT * FROM STUDENT WHERE 1 ORDER BY _id DESC LIMIT 0,1";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor != null) {
            cursor.moveToFirst();

            Student student = new Student();
            student.setFullname(cursor.getString(cursor.getColumnIndex(Student.ID_FULL_NAME)));
            student.setEmail(cursor.getString(cursor.getColumnIndex(Student.ID_EMAIL)));

            cursor.close();
            return student;
        }
        return null;
    }



    public static List<Student> getStudentList() {
        List<Student> students = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        String query = "SELECT * FROM STUDENT WHERE 1 ORDER BY _id DESC LIMIT 0,1";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor != null) {
            cursor.moveToFirst();

            do {
                Student student = new Student();
                student.setFullname(cursor.getString(cursor.getColumnIndex(Student.ID_FULL_NAME)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(Student.ID_EMAIL)));

                students.add(student);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return students;
    }
}
