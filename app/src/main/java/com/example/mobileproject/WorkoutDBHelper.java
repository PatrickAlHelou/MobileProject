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
        String sqlCreate = "Create Table Workouts (idWorkout INTEGER PRIMARY KEY AUTOINCREMENT , title TEXT , sets INTEGER , reps INTEGER , day TEXT , image TEXT , image2 TEXT)";
        String sqlinsert = "insert into Workouts (title, sets, reps, day, image,image2) Values ('Bench Press' , 4 , 8 , 'Monday' , 'https://i.ibb.co/jWGScYk/6.jpg' , 'https://i.ibb.co/QmzZx8L/barbell-bench-press.gif')";
        String sqlinsert2 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('Bench Press Close Grip' , 3 , 12 , 'Monday' , 'https://i.ibb.co/jDmnk2B/7.jpg', 'https://gymvisual.com/img/p/1/0/0/6/1/10061.gif')";
        String sqlinsert3 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('Decline Bench Press' , 4 , 10 , 'Monday' , 'https://i.ibb.co/FgthgVS/image-2023-05-17-125350307.png', 'https://gymvisual.com/img/p/4/7/6/4/4764.gif')";
        String sqlinsert4 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('Dumbell Bench Press' , 4 , 12 , 'Monday' , 'https://i.ibb.co/HHYvWJT/8.jpg', 'https://fitnessprogramer.com/wp-content/uploads/2021/02/Dumbbell-Press.gif')";
        String sqlinsert5 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('Incline dumbbell inner-biceps' , 3 , 8 , 'Monday' , 'https://i.ibb.co/7byrf7y/image-2023-05-16-211922996.png', 'https://gymvisual.com/img/p/5/0/4/8/5048.gif')";
        String sqlinsert6 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('EZ-Bar dumbbell biceps' , 4 , 8 , 'Monday' , 'https://i.ibb.co/sj9hzfd/4.png', 'https://newlife.com.cy/wp-content/uploads/2019/11/04021301-Dumbbell-Seated-Preacher-Curl_Upper-Arms_360.gif')";
        String sqlinsert7 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('EZ-Bar dumbbell biceps' , 4 , 12 , 'Wednesday' , 'https://i.ibb.co/DQqW5QM/image-2023-05-16-212138550.png', 'https://i.ibb.co/DQqW5QM/image-2023-05-16-212138550.png')";
        String sqlinsert8 = "insert into Workouts (title, sets, reps, day, image,image2) Values ('EZ-Bar dumbbell biceps' , 4 , 12 , 'Wednesday' , 'https://i.ibb.co/DQqW5QM/image-2023-05-16-212138550.png', 'https://i.ibb.co/DQqW5QM/image-2023-05-16-212138550.png')";

        db.execSQL(sqlCreate);
        db.execSQL(sqlinsert);
        db.execSQL(sqlinsert2);
        db.execSQL(sqlinsert3);
        db.execSQL(sqlinsert4);
        db.execSQL(sqlinsert5);
        db.execSQL(sqlinsert6);
        db.execSQL(sqlinsert7);
        db.execSQL(sqlinsert8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
