package com.stoneco.moviestonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakeAccount extends AppCompatActivity {

    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        signUp = findViewById(R.id.signUpLogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignUp();

            }
        });
    }

    public void startSignUp(){

        Intent i = new  Intent(this,SignUp.class);
        startActivity(i);

    }

}
