package com.example.mobileproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<AdpViewHolder> {

    private List<Workout> mWourkouts;

    public WorkoutAdapter(List<Workout> mWourkouts) {
        this.mWourkouts = mWourkouts;
    }

    @NonNull
    @Override
    public AdpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View workoutView = inflater.inflate(R.layout.workout_item, parent, false);

        AdpViewHolder viewHolder = new AdpViewHolder(workoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdpViewHolder holder, int position) {
        Workout workout = mWourkouts.get(position);

        TextView textView = holder.title;
        textView.setText(workout.getTitle());

        TextView textView2 = holder.day;
        textView2.setText(workout.getDay());

        TextView textView3 = holder.sets;
        textView3.setText(String.valueOf(workout.getSets()));

        TextView textView4 = holder.reps;
        textView4.setText(String.valueOf(workout.getReps()));

        ImageView imageView = holder.image;
        Glide.with(imageView.getContext())
                .load(workout.getImage()) // load image from URL
                .into(imageView); // set the loaded image to the ImageView

        Log.d("WorkoutAdapter", "Image URL: " + workout.getImage());

    }

    @Override
    public int getItemCount() {
        return mWourkouts.size();
    }
}
