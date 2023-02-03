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

public class onlineteaching extends AppCompatActivity {

    Button buy;
    TextView amt;
    Context context= onlineteaching.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineteaching);

        String emailStr = getIntent().getStringExtra("email1");

        buy = findViewById(R.id.buy);
        amt = findViewById(R.id.amt);


        String amtStr = amt.getText().toString();
        String name = "Online technical analysis";

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyBtn(context,name,amtStr, emailStr);
                finish();
            }
        });
    }
}