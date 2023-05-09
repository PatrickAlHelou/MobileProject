package com.example.mobileproject;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WorkoutDBHelper extends SQLiteOpenHelper {

    public WorkoutDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create Table Workouts (idWorkout INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , sets INTEGER , reps INTEGER , day TEXT , image TEXT)";
        String sqlinsert = "insert into Workouts (title, sets, reps, day, image) Values ('Abs' , 4 , 8 , 'Monday' , 'https://assets.publishing.service.gov.uk/government/uploads/system/uploads/image_data/file/176455/s300_Football_gov.uk.jpg')";
        String sqlinsert2 = "insert into Workouts (title, sets, reps, day, image) Values ('Arms' , 3 , 12 , 'Monday' , 'https://assets.publishing.service.gov.uk/government/uploads/system/uploads/image_data/file/176455/s300_Football_gov.uk.jpg')";
        String sqlinsert3 = "insert into Workouts (title, sets, reps, day, image) Values ('legs' , 4 , 10 , 'Tuesday' , 'https://assets.publishing.service.gov.uk/government/uploads/system/uploads/image_data/file/176455/s300_Football_gov.uk.jpg')";
        String sqlinsert4 = "insert into Workouts (title, sets, reps, day, image) Values ('Shoulders' , 4 , 12 , 'Tuesday' , 'https://thumbs.dreamstime.com/z/soccer-championship-concept-player-stadium-lights-fotball-match-ball-113596114.jpg')";
        db.execSQL(sqlCreate);
        db.execSQL(sqlinsert);
        db.execSQL(sqlinsert2);
        db.execSQL(sqlinsert3);
        db.execSQL(sqlinsert4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
