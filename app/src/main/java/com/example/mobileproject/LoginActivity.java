package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;

    public final static String SHARED_PREFS = "userPrefs";
    public final static String USER_NAME = "userName";
    public final static String EMAIL = "email";

    public final static String PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setContentView(R.layout.nav_header_main);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextTextRegPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "fitness", null, 1);
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(username, password) != null) {
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                            Cursor cursor = db.login(username, password);
                            // move the cursor to the first row (there should only be one row)
                            cursor.moveToFirst();
                            // retrieve the name and email from the cursor
                            String name = cursor.getString(0);
                            String email = cursor.getString(1);
                            String pass = cursor.getString(2);
                            // do something with the name and email (e.g., display them in a TextView)
                            cursor.close();
                        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("name", name);
                        editor.putString(EMAIL,email);
                        editor.putString(PASSWORD,pass);
                        editor.commit();



                        Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        startActivity(intent);






//                        startActivity(intent);



                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}