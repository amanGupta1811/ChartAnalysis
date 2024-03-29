package com.acpitzone.chartanalysis;

import static com.acpitzone.chartanalysis.url.buyBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.chartanalysis.R;


public class optionstrategy extends AppCompatActivity {

    Button buy;
    TextView amt;
    Context context= optionstrategy.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionstrategy);

        String emailStr = getIntent().getStringExtra("email2");

        buy = findViewById(R.id.buy);
        amt = findViewById(R.id.courseAmt);

        String amtStr = amt.getText().toString();
        String name = "Option Strategy";

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyBtn(context,name,amtStr, emailStr);
                finish();
            }
        });    }
}