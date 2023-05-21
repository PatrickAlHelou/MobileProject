package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);

        TextView textView = findViewById(R.id.my_text_view);
        textView.setText("HistoryActivity Class");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Workout");

        Workout a = new Workout(1, "Bench Press" , 4 , 8 , "Monday" , "https://i.ibb.co/jWGScYk/6.jpg" , "https://i.ibb.co/QmzZx8L/barbell-bench-press.gif");
        Workout b = new Workout(2,"Bench Press Close Grip" , 3 , 12 , "Monday" , "https://i.ibb.co/jDmnk2B/7.jpg", "https://gymvisual.com/img/p/1/0/0/6/1/10061.gif");

        myRef.push().setValue(a);
        myRef.push().setValue(b);

    }
}