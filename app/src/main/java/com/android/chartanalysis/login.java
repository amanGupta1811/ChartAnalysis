package com.android.chartanalysis;

import static com.android.chartanalysis.regex.isValidPassword;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    TextView registerTxt;
    ImageView menuBack;
    Button skipBtn,login;
    EditText emailTxt, passTxt;
    ProgressBar progressBar;
    String emailStr;
    String url = "https://sdcsupermarket.com/login.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        skipBtn = findViewById(R.id.skip);
        login = findViewById(R.id.buttonSignIn);
        emailTxt = findViewById(R.id.emalIdSignIn);
        passTxt = findViewById(R.id.passWordSignIn);
        registerTxt = findViewById(R.id.registerbtn);
        progressBar = findViewById(R.id.progressBar);

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
        emailStr = emailTxt.getText().toString();
        String passStr = passTxt.getText().toString();

        boolean isValidate = validateData(emailStr,passStr);

        if(!isValidate){
            return;
        }

        loginUserToDatabase(emailStr, passStr);
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

    void loginUserToDatabase(final String email, final String password){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

                if(response.toString().equals("Login Succesfully")){
                    Intent i = new Intent(login.this, MainActivity.class);
                    i.putExtra("email", emailStr);
                    startActivity(i);
                    finish();
                }

                else{
                    emailTxt.setText("");
                    passTxt.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}