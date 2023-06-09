package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class WorkoutDetails extends AppCompatActivity {

    public final static String SHARED_PREFS = "userPrefs";
    public final static String USER_NAME = "userName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        final TextView textView = findViewById(R.id.textView3);
        String title = getIntent().getStringExtra("title");
        textView.setText("title : " + title);

        final TextView textView1 = (TextView) findViewById(R.id.textView4);
        String day = getIntent().getStringExtra("day");
        textView1.setText("Day : " + day);

        final TextView textView2 = (TextView) findViewById(R.id.textView5);
        textView2.setText("Sets: "+ getIntent().getIntExtra("sets",0));


        final TextView textView3 = (TextView) findViewById(R.id.textView9);
        textView3.setText("Reps: "+  getIntent().getIntExtra("reps",0));

        final ImageView imageView = findViewById(R.id.videoView);
        String imageUrl = getIntent().getStringExtra("image2");

        Glide.with(this)
                .asGif()
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String name = sharedpreferences.getString("name", "");
                String title = getIntent().getStringExtra("title");
                int sets = getIntent().getIntExtra("sets", 0);

                // Update the sets count in Firebase Realtime Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference workoutsRef = database.getReference("workouts");

                Query query = workoutsRef.orderByChild("name").equalTo(name);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean isWorkoutFound = false;
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                String workoutTitle = childSnapshot.child("title").getValue(String.class);

                                if (workoutTitle.equals(title)) {
                                    // Increment the sets count
                                    int setsTest = childSnapshot.child("sets").getValue(Integer.class);
                                    setsTest++;
                                    childSnapshot.getRef().child("sets").setValue(setsTest);
                                    isWorkoutFound = true;
                                    // Exit the listener
                                    break;
                                }
                            }
                        }

                        if (!isWorkoutFound) {
                            // Create a new child with the incremented sets count
                            DatabaseReference newWorkoutRef = workoutsRef.push();
                            newWorkoutRef.child("name").setValue(name);
                            newWorkoutRef.child("title").setValue(title);
                            newWorkoutRef.child("sets").setValue(1);
                        }

                        query.removeEventListener(this); // Remove the listener

                        // Update the progress bar
                        if (progressBar.getProgress() == 100) {
                            Toast.makeText(WorkoutDetails.this, "Congratulations, you have completed this workout", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar.setProgress(progressBar.getProgress() + 100 / sets);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error
                    }
                });
            }
        });




    }


}