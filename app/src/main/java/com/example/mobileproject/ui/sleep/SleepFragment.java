package com.example.mobileproject.ui.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileproject.Artist;
import com.example.mobileproject.Music;
import com.example.mobileproject.R;
import com.example.mobileproject.RegisterActivity;
import com.example.mobileproject.databinding.FragmentSleepBinding;
import com.example.mobileproject.databinding.FragmentSlideshowBinding;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SleepFragment extends Fragment {

    private FragmentSleepBinding binding;

    public ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SleepViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SleepViewModel.class);

        binding = FragmentSleepBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = new Intent(getActivity(), Music.class);
        startActivity(intent);

        return root;
    }

}