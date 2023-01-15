package com.android.chartanalysis;

import static com.android.chartanalysis.url.buyBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class onlineteaching extends AppCompatActivity {

    Button buy;
    TextView amt;
    Context context= onlineteaching.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineteaching);

        buy = findViewById(R.id.buy);
        amt = findViewById(R.id.amt);


        String amtStr = amt.getText().toString();
        String name = "Online technical analysis";

        buy.setOnClickListener((v)->buyBtn(context,name,amtStr));
    }
}