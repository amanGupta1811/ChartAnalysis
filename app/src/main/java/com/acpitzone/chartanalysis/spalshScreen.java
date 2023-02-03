package com.acpitzone.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.android.chartanalysis.R;

public class spalshScreen extends AppCompatActivity {
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        progressBar = findViewById(R.id.prog_bar);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("Username", "");
                String password = sharedPreferences.getString("Password", "");

                Intent i;
                if(!username.isEmpty()&&!password.isEmpty()){
                    i = new Intent(spalshScreen.this, MainActivity.class);
                }
                else{
                    i = new Intent(spalshScreen.this, login.class);
                }
                startActivity(i);
                finish();
                progressBar.setVisibility(View.GONE);
            }
        },1000);


    }
}