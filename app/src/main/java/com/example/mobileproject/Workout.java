package com.example.mobileproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Workout {
    private int id;
    private String title;
    private int sets;
    private int reps;
    private String day;
    private String image;
    private String image2;


    public Workout(int id, String title, int sets, int reps, String day, String image, String image2) {
        this.id = id;
        this.title = title;
        this.sets = sets;
        this.reps = reps;
        this.day = day;
        this.image = image;
        this.image2 = image2;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


 /*   public static ArrayList<Workout> getWorkoutsByDay(Context context, String day) {
        ArrayList<Workout> workouts = new ArrayList<>();

        WorkoutDBHelper myDbHelper = new WorkoutDBHelper(context, "dbWorkouts", null, 1);
        SQLiteDatabase mydb = myDbHelper.getReadableDatabase();

        Cursor c=mydb.rawQuery("select * from Workouts", null);
        while(c.moveToNext()){
            Integer id = c.getInt(0);
            String title = c.getString(1);
            Integer sets  = c.getInt(2);
            Integer reps = c.getInt(3);
            String workoutDay = c.getString(4);
            String image = c.getString(5);
            String image2 = c.getString(6);
            workouts.add(new Workout(id , title, sets, reps, workoutDay , image , image2));
        }

        c.close();
        mydb.close();

        return workouts;
    }*/

    public static ArrayList<Workout> createWorkoutsList(Context context) {
        ArrayList<Workout> workouts = new ArrayList<>();

        WorkoutDBHelper myDbHelper = new WorkoutDBHelper(context, "dbWorkouts", null, 1);
        SQLiteDatabase mydb = myDbHelper.getReadableDatabase();


        Cursor c=mydb.rawQuery("select * from Workouts",null);
        while(c.moveToNext()){
            Integer id = c.getInt(0);
            String title = c.getString(1);
            Integer sets  = c.getInt(2);
            Integer reps = c.getInt(3);
            String day = c.getString(4);
            String image = c.getString(5);
            String image2 = c.getString(6);
            workouts.add(new Workout(id , title, sets, reps, day , image , image2));
        }


        c.close();
        mydb.close();

        return workouts;
    }
}