package com.android.chartanalysis;

import static com.android.chartanalysis.url.buyBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class optionstrategy extends AppCompatActivity {

    Button buy;
    TextView amt;
    Context context= optionstrategy.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionstrategy);

        buy = findViewById(R.id.buy);
        amt = findViewById(R.id.courseAmt);

        String amtStr = amt.getText().toString();
        String name = "Option Strategy";

        buy.setOnClickListener((v)->buyBtn(context,name,amtStr));
    }
}