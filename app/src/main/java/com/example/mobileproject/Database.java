package com.example.mobileproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(id integer PRIMARY KEY AUTOINCREMENT ,username text,email text , password text )";
        String qry2 = "create table history(id integer PRIMARY KEY AUTOINCREMENT ,username text,song text)";
        db.execSQL(qry1);
        db.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", "id", cv);
        db.close();
    }

    public void addHistory(String username, String song)
    {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("song", song);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("history", "id", cv);
        db.close();

    }

    public Cursor login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"username", "email","password"};
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("users", columns, "username=? AND password=?", selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor;
        }
        return null;
    }





}
