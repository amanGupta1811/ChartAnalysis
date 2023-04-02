package com.acpitzone.chartanalysis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.chartanalysis.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class otpverification extends AppCompatActivity {

    EditText otpTxt;
    Button verify;
    ProgressBar progressBar;
    Intent i;
    String url = "https://sdcsupermarket.com/otpVerification.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        i = getIntent();

        otpTxt = findViewById(R.id.otp);
        verify = findViewById(R.id.verify);
        progressBar = findViewById(R.id.progressBarO);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyOtp();
            }
        });
    }

    void verifyOtp(){
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        String mobile = i.getStringExtra("mobile");
        String password = i.getStringExtra("password");
        String otpStr = otpTxt.getText().toString();

        if(!otpStr.isEmpty() && !email.isEmpty()){
            verifyOtpDB(name, email, mobile, password, otpStr);
        }
        else{
            Toast.makeText(this, "Enter Otp", Toast.LENGTH_SHORT).show();
        }
    }

    void verifyOtpDB(String name, String email, String mobile, String password, String otpStr){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(otpverification.this, response, Toast.LENGTH_SHORT).show();

                if(response.equals("Otp verify succesfully")){
                    Intent i = new Intent(otpverification.this,login.class);
                    startActivity(i);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(otpverification.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", email);
                map.put("code", otpStr);
                map.put("name",name);
                map.put("mobile",mobile);
                map.put("password",password);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}