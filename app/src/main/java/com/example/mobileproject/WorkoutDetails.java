package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
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

//        final ImageView imageView = findViewById(R.id.videoView);
//        String image2 = getIntent().getStringExtra("image2");
//        String imageUrl = image2;
        //Picasso.get().load(imageUrl).priority(Picasso.Priority.HIGH).into(imageView);

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
                if(progressBar.getProgress() == 100)
                {
                    Toast.makeText(WorkoutDetails.this, "Congratulations, you have completed this workout", Toast.LENGTH_LONG).show();

                }
                else
                {
                    progressBar.setProgress(progressBar.getProgress()+ 100/getIntent().getIntExtra("sets",0));
                }
            }});
    }


}