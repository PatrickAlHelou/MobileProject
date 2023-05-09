package com.example.mobileproject.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.MainActivity;
import com.example.mobileproject.Workout;
import com.example.mobileproject.WorkoutAdapter;
import com.example.mobileproject.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up RecyclerView
        RecyclerView recyclerView = binding.rvWorkouts;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        // Set up WorkoutAdapter
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(Workout.createWorkoutsList(getContext()));
        recyclerView.setAdapter(workoutAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}