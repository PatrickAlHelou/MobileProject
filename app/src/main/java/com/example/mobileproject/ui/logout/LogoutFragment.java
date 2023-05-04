package com.example.mobileproject.ui.logout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mobileproject.LoginActivity;
import com.example.mobileproject.R;


public class LogoutFragment extends Fragment {

    private Button mLogoutButton;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        mLogoutButton = root.findViewById(R.id.logout_button);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog(view);
            }
        });

        return root;
    }

    private void showLogoutConfirmationDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Loging out the account. Continue?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Clear user session data
                        // TODO: Implement clearUserSessionData() method
                        clearUserSessionData();

                        // Navigate to login screen
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss the dialog and do nothing
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void clearUserSessionData() {
        // TODO: Implement method to clear user session data
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLogoutButton = null;
    }
}