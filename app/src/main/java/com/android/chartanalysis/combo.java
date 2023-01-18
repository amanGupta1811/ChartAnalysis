package com.android.chartanalysis;

import static com.android.chartanalysis.url.buyBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class combo extends AppCompatActivity {
    Button buy;
    TextView amt;
    Context context = combo.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        String emailStr = getIntent().getStringExtra("email3");

        buy = findViewById(R.id.buy);
        amt = findViewById(R.id.courseAmt);

        String amtStr = amt.getText().toString();
        String name = "Combo of both";

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyBtn(context,name,amtStr,emailStr);
                finish();
            }
        });    }
}