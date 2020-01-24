package com.stoneco.moviestonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class MakeAccount extends AppCompatActivity {

    private Button signUp;
    private EditText username;
    private EditText password;
    private EditText confPassword;
    private EditText email;
    private EditText phoneNumber;
    private static final String STATUS_TAG = "status_tag_makeAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        signUp = findViewById(R.id.signUpLogin);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.passwordMake);
        confPassword = findViewById(R.id.passwordConf);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount(username.getText().toString(), email.getText().toString(),password.getText().toString(), confPassword.getText().toString(),phoneNumber.getText().toString());

            }
        });
    }

    public void startSignUp(){

        Intent i = new  Intent(this,SignUp.class);
        startActivity(i);

    }

    public void createAccount(String name, String email, String password, String passwordConf, String number){

        final boolean isValidAccount;
        final boolean isValidPassword;

        ParseUser user = new ParseUser();

        isValidAccount = checkInputLength(name,email,password, passwordConf, number);
        isValidPassword = uniquePassword(password,passwordConf);


        if(!isValidAccount){

            Toast.makeText(getApplicationContext(),"Please make sure to fill all fields!",Toast.LENGTH_SHORT).show();
            Log.d(STATUS_TAG,"User failed to fill in all fields!");
            return;

        }else if(isValidPassword) {


            user.setUsername(name);
            user.setEmail(email);
            user.setPassword(password);
            user.put("MobileNumber", number);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        Log.d(STATUS_TAG, "Created a new account successfully");
                        startSignUp();
                        finish();

                    } else {

                        Log.d(STATUS_TAG, e.toString());
                        Toast.makeText(getApplicationContext(), e.toString().substring(e.toString().indexOf(':') + 1, e.toString().length()), Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

    }

    public boolean uniquePassword(String password, String passwordConf){

        if(password.length() < 8){

            Toast.makeText(getApplicationContext(), "Password is too short. Must be 8 characters long!", Toast.LENGTH_SHORT).show();
            Log.d(STATUS_TAG, "Passwords too short.");
            return false;

        }else if(!password.equals(passwordConf)){

            Toast.makeText(getApplicationContext(), "The two passwords don't match!", Toast.LENGTH_SHORT).show();
            Log.d(STATUS_TAG, "Passwords Aren't matching");
            return false;

        }

        Log.d(STATUS_TAG, "Passed uniquePasswordTest!");

        return true;

    }

    private boolean checkInputLength(String name, String email, String password, String passwordConf, String number) {

        if(name.length() == 0){

            return false;

        }

        if(email.length() == 0){

            return false;

        }

        if(password.length() == 0){

            return false;

        }

        if(passwordConf.length() == 0){

            return false;

        }

        if(number.length() == 0){

            return false;

        }

        return true;

    }


}
