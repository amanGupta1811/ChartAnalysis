package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class buyCourse extends AppCompatActivity {

    TextView courseName, courseAmt;
    ImageButton backBtn;
    Button payBtn;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_course);

        courseName= findViewById(R.id.courseName);
        courseAmt= findViewById(R.id.courseAmt);
        backBtn= findViewById(R.id.backBtn);
        payBtn = findViewById(R.id.payBtn);

        Intent intent = getIntent();

        String name = intent.getStringExtra("courseName");
        String amt = intent.getStringExtra("courseAmt");

        courseName.setText(name);
        courseAmt.setText(amt);

        backBtn.setOnClickListener((v)->finish());
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buyCourse.this,courseVideo.class);
                startActivity(intent);
                finish();
            }
        });
    }
}