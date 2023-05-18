package com.example.mobileproject.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileproject.HistoryActivity;
import com.example.mobileproject.Music;
import com.example.mobileproject.databinding.FragmentSleepBinding;
import com.example.mobileproject.ui.sleep.SleepViewModel;

public class HistoryFragment extends Fragment {

    private FragmentSleepBinding binding;

    public ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel slideshowViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentSleepBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);

        return root;
    }

}