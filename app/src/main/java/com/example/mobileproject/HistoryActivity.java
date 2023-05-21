package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("workouts");

        FirebaseDatabase database0 = FirebaseDatabase.getInstance();
        DatabaseReference myRef0 = database.getReference("music");

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("name", "");

        fetchWorkoutsByName(name, myRef);
        fetchSongsByName(name, myRef0);

    }

    private void fetchWorkoutsByName(String name, DatabaseReference myRef) {
        Query query = myRef.orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder workoutInfoBuilder = new StringBuilder();

                for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {

                    WorkoutHistory workout = workoutSnapshot.getValue(WorkoutHistory.class);


                    int workoutSets = workout.getSets();
                    String workoutTitle = workout.getTitle();


                    workoutInfoBuilder.append("Name: ").append(workoutTitle).append("\n")
                            .append("Sets: ").append(workoutSets).append("\n").append("\n");
                }


                TextView workoutTextView = findViewById(R.id.workout_text_view);
                workoutTextView.setText(workoutInfoBuilder.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void fetchSongsByName(String name, DatabaseReference myRef0) {
        Query query = myRef0.orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder SongsInfoBuilder = new StringBuilder();

                for (DataSnapshot songsSnapshot : dataSnapshot.getChildren()) {

                    MusicHistory song = songsSnapshot.getValue(MusicHistory.class);


                    String title = song.getTitle();
                    int time = song.getTime();


                    SongsInfoBuilder.append("Name: ").append(title).append("\n")
                            .append("Times: ").append(time).append("\n").append("\n");
                }


                TextView songsTextView = findViewById(R.id.songs_text_view);
                songsTextView.setText(SongsInfoBuilder.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }














}