package com.example.mobileproject;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdpViewHolder extends RecyclerView.ViewHolder {


    public TextView title;
    public TextView day;
    public TextView sets;
    public TextView reps;
    public TextView image;


    public AdpViewHolder(@NonNull View itemView) {
        super(itemView);

        title=(TextView)itemView.findViewById(R.id.workout_title);
        day=(TextView)itemView.findViewById(R.id.workout_day);
        sets=(TextView)itemView.findViewById(R.id.workout_sets);
        reps=(TextView)itemView.findViewById(R.id.workout_reps);
        image=(TextView)itemView.findViewById(R.id.workout_image);
    }
}
