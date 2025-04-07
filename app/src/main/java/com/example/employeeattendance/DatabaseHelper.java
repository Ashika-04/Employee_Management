package com.example.employeeattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AttendanceDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_EMPLOYEES = "employees";
    private static final String TABLE_ATTENDANCE = "attendance";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_EMPLOYEES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, position TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_ATTENDANCE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, emp_id INTEGER, date TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username=? AND password=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean addEmployee(String name, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("position", position);
        long result = db.insert(TABLE_EMPLOYEES, null, values);
        return result != -1;
    }

    public boolean updateEmployee(int id, String name, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("position", position);
        int rowsAffected = db.update(TABLE_EMPLOYEES, values, "id=?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }

    public void deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, "id=?", new String[]{String.valueOf(id)});
    }

    public boolean markAttendance(int empId, String date, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("emp_id", empId);
        values.put("date", date);
        values.put("status", status);
        long result = db.insert(TABLE_ATTENDANCE, null, values);
        return result != -1;
    }

    public Cursor getAttendanceReport() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT emp_id, date, status FROM " + TABLE_ATTENDANCE, null);
    }
}
