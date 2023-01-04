package com.android.chartanalysis;

import static com.android.chartanalysis.regex.isValidPassword;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {

    TextView loginTxt;
    ImageView menuBtn;
    EditText nameTxt,emailTxt,mobileTxt,passTxt;
    Button signUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginTxt = findViewById(R.id.registerbtn);
        nameTxt = findViewById(R.id.name);
        emailTxt = findViewById(R.id.emalIdSignIn);
        mobileTxt = findViewById(R.id.passWordSignIn);
        passTxt = findViewById(R.id.mobile);
        signUp = findViewById(R.id.buttonSignIn);

        loginTxt.setOnClickListener((v)->finish());
        signUp.setOnClickListener((v)->signUpToAcc());

    }

    void signUpToAcc(){
        String nameStr = nameTxt.getText().toString();
        String mobileStr = mobileTxt.getText().toString();
        String emailStr = emailTxt.getText().toString();
        String passStr = passTxt.getText().toString();

        boolean isValidate = validateData(nameStr,emailStr,mobileStr,passStr);

        if(!isValidate){
            return;
        }
        Toast.makeText(signUp.this, "SignUp successfully", Toast.LENGTH_LONG).show();
    }

    boolean validateData(String nameStr,String emailStr, String mobileStr, String passStr){

        if(nameStr.isEmpty()){
            nameTxt.setError("Enter Name");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        else if(mobileStr.length()!=10){
            mobileTxt.setError("Phone no. is invalid");
        }

        else if (!isValidPassword(passStr)){
            passTxt.setError("This password is invalid");
            return false;
        }
        return true;
    }

//    public static boolean isValidPassword(final String password) {
//
//        Pattern pattern;
//        Matcher matcher;
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//
//        return matcher.matches();
//    }

}