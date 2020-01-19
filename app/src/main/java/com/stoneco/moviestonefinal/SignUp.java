package com.stoneco.moviestonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private Button guestBtn;
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private final static int GUEST = 0;
    private final static int USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        guestBtn = findViewById(R.id.guestBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startCurrentMovies(view);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startCurrentMovies(view);

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

}
