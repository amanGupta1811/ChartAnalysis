package com.android.chartanalysis;

import static com.android.chartanalysis.regex.isValidPassword;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    TextView registerTxt;
    ImageView menuBack;
    Button skipBtn,login;
    EditText emailTxt, passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        skipBtn = findViewById(R.id.skip);
        login = findViewById(R.id.buttonSignIn);
        emailTxt = findViewById(R.id.emalIdSignIn);
        passTxt = findViewById(R.id.passWordSignIn);
        registerTxt = findViewById(R.id.registerbtn);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                finish();}});

        login.setOnClickListener((v)->loginToAcc());
        registerTxt.setOnClickListener((v)->startActivity(new Intent(login.this,signUp.class)));

    }

    void loginToAcc(){
        String emailStr = emailTxt.getText().toString();
        String passStr = passTxt.getText().toString();

        boolean isValidate = validateData(emailStr,passStr);

        if(!isValidate){
            return;
        }

        Toast.makeText(login.this, "Login successfully", Toast.LENGTH_LONG).show();
    }

    boolean validateData(String emailStr, String passStr){

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        else if (!isValidPassword(passStr)){
            passTxt.setError("Password is invalid");
            return false;
        }
        return true;
    }



    }
