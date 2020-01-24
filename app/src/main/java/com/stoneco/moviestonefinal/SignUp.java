package com.stoneco.moviestonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private Button guestBtn;
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private Button createBtn;
    private final static int GUEST = 0;
    private final static int USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        guestBtn = findViewById(R.id.guestBtn);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordMake);
        loginBtn = findViewById(R.id.signUpLogin);
        createBtn = findViewById(R.id.createBtn);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordMake);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startMakeAccount();

            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startCurrentMovies(view);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(username.getText().toString(), password.getText().toString(), view);

            }
        });

    }

    public void startCurrentMovies(View v){

        Intent i = new Intent(this,CurrentMovies.class);

        if(v.getId() == R.id.guestBtn){

            i.putExtra("LoginValue", GUEST);


        }else{

            i.putExtra("LoginValue", USER);

        }

        startActivity(i);

    }

    public void startMakeAccount(){

        Intent i = new Intent(this,MakeAccount.class);
        startActivity(i);

    }

    public void login(String username, String password, final View v){

        ParseUser newUser = new ParseUser();

        Log.d("LoginInfo", "username is: " + username +  " password is: " + password);

        newUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if( e != null){

                    Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                    return;

                }

                startCurrentMovies(v);
                finish();

            }
        });

    }



}
