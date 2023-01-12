package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class menu extends AppCompatActivity {

    ImageView btnBack;
    TextView home,contact, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnBack = findViewById(R.id.back_btn);
        home = findViewById(R.id.categories);
        contact = findViewById(R.id.wishlist);
        about = findViewById(R.id.your_orders);

        //Back_Button
        btnBack.setOnClickListener((v) -> finish());

        //Home_Button
        home.setOnClickListener((v) -> finish());

        //Contact_Us
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu.this, contactUs.class);
                startActivity(i);
                finish();
            }
        });

        //About_Us
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menu.this, aboutUs.class);
                startActivity(i);
                finish();
            }
        });

    }
}