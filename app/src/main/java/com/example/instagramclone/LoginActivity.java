package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG ="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private ProgressBar progressBar;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Take instance of Action Bar
        // using getSupportActionBar and
        // if it is not Null
        // then call hide function
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if(ParseUser.getCurrentUser() != null){
            Toast.makeText(LoginActivity.this, "Logged In as "+ParseUser.getCurrentUser().getUsername(),
                    Toast.LENGTH_LONG).show();
            goMainActivity();
        }

        progressBar = findViewById(R.id.pbLoading);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick Login Button");
                progressBar.setVisibility(ProgressBar.VISIBLE);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Log.i(TAG,"empty values");
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Required value missing",
                            Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(username,password);
                }

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick Sign up Button");
                progressBar.setVisibility(ProgressBar.VISIBLE);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Log.i(TAG,"empty values");
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Required value missing",
                            Toast.LENGTH_SHORT).show();
                }else {
                    signUpUser(username, password);
                }
            }
        });
    }

    private void signUpUser(final String username, String password) {
        Log.i(TAG, "Attempting to Sign up user "+ username);
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with Sign up",e);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Issue with Sign Up! ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //if sign up successful then navigate to main activity
                Log.e(TAG, "Sign up Successful",e);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success Logged in as "+username,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loginUser(final String username, String password) {
        Log.i(TAG, "Attempting to Login user "+username);
        ParseUser.logInInBackground(username, password,new LogInCallback(){

            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with Login "+e,e);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Issue with Login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if login successful then navigate to main activity
                Log.e(TAG, "Login Successful",e);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success Logged In as "+username,
                  Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}