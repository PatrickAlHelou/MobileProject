package com.example.mobileproject.ui.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.LoginActivity;

public class LogoutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LogoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Logout fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }

}