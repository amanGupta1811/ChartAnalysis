package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageView logBtn,yt1,yt2;
    ImageView menuBTn;
    TextView seeAll;
    CardView firstCourse,secondCourse,comboCourse;
    FloatingActionButton call, email, telegram, youtube, contact;
    boolean touch = true;
    String url1 = "https://www.youtube.com/@chartanalysis123";
    String url2 = "https://www.youtube.com/watch?v=Ab67mKtfXok";
    String url3 = "https://www.youtube.com/watch?v=x7_deJR3f38";
    String url4 = "https://t.me/chartanalysis_umesh_sharma";


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logBtn = findViewById(R.id.loginAcc);
        menuBTn = findViewById(R.id.menu_btn);
        seeAll = findViewById(R.id.ytAll);
        yt1 = findViewById(R.id.yt1);
        yt2 = findViewById(R.id.yt2);
        firstCourse = findViewById(R.id.firstCourse);
        secondCourse = findViewById(R.id.secondCourse);
        comboCourse = findViewById(R.id.thirdCourse);
        call = findViewById(R.id.call);
        email = findViewById(R.id.email);
        telegram = findViewById(R.id.tele);
        youtube = findViewById(R.id.youtubeBtn);
        contact = findViewById(R.id.btn_contact);


        logBtn.setOnClickListener((v)->
                startActivity(new Intent(MainActivity.this, login.class))
        );

        //Menu_Bar
        menuBTn.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, menu.class)));

        //Open_Youtube
        seeAll.setOnClickListener((v)->GotoYt(url1));
        yt2.setOnClickListener((v)->GotoYt(url2));
        yt1.setOnClickListener((v)->GotoYt(url3));

        //Course_Details
        firstCourse.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, onlineteaching.class)));
        secondCourse.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, optionstrategy.class)));
        comboCourse.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, combo.class)));

        //Contact_Floating_Button
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (touch){
                    call.show();
                    email.show();
                    telegram.show();
                    youtube.show();
                    touch = false;
                }
                else{
                    call.hide();
                    email.hide();
                    telegram.hide();
                    youtube.hide();
                    touch = true;
                }
            }
        });

        //Contact_yt
        youtube.setOnClickListener((v)->GotoYt(url1));

        //Contact_Call
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phn = "tel:+919711501546";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phn));
                startActivity(callIntent);
            }
        });

        //Contact_Email
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "chartanalysis.us@gmail.com"});
                intent.setData(data);
                startActivity(intent);
            }
        });

        //Contact_Telegram
        telegram.setOnClickListener((v)->GotoYt(url4));
    }

    void GotoYt(String url){
        Uri uri = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}