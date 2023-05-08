package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvworkouts=(RecyclerView)(findViewById(R.id.rvWorkouts));

        WorkoutAdapter workoutAdapter=new WorkoutAdapter(Workout.createWorkoutsList(this.getApplicationContext()));

        rvworkouts.setAdapter(workoutAdapter);

        rvworkouts.setLayoutManager(new LinearLayoutManager(this));
        rvworkouts.setLayoutManager(new GridLayoutManager(this,2));

    }
}