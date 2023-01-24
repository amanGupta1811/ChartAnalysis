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

public class signUp extends AppCompatActivity {

    TextView loginTxt;
    ImageView menuBtn;
    EditText nameTxt,emailTxt,mobileTxt,passTxt;
    Button signUp;
    ProgressBar progressBar;
    String url ="https://sdcsupermarket.com/register.php";

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
        progressBar = findViewById(R.id.progressBar);

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

        registerUserToDatabase(nameStr,emailStr,mobileStr,passStr);


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
            return false;
        }

        else if (!isValidPassword(passStr)){
            passTxt.setError("This password is invalid");
            return false;
        }
        return true;
    }

    void registerUserToDatabase(final String name, final String email, final String mobile, final String password){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                if(response.toString().equals("SignUp Succesfully")){
                    Intent i = new Intent(signUp.this, login.class);
                    startActivity(i);
                    finish();
                }
                else{
                    nameTxt.setText("");
                    emailTxt.setText("");
                    mobileTxt.setText("");
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
                map.put("name",name);
                map.put("email",email);
                map.put("contact",mobile);
                map.put("password",password);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}