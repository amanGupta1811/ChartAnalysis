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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView logBtn,yt1,yt2;
    ImageView menuBTn;
    TextView seeAll;
    CardView firstCourse,secondCourse,comboCourse;
    FloatingActionButton call, email, telegram, youtube, contact;
    String registerEmail;
    boolean touch = true;
    String url1 = "https://www.youtube.com/@chartanalysis123";
    String url2 = "https://www.youtube.com/watch?v=Ab67mKtfXok";
    String url3 = "https://www.youtube.com/watch?v=x7_deJR3f38";
    String url4 = "https://t.me/chartanalysis_umesh_sharma";
    String urli = "https://sdcsupermarket.com/menu.php";


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

        registerEmail = getIntent().getStringExtra("email");


        logBtn.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, login.class)));

        //Menu_Bar
       // menuBTn.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, menu.class)));

        //Open_Youtube
        seeAll.setOnClickListener((v)->url.GotoYt(MainActivity.this,url1));
        yt2.setOnClickListener((v)->url.GotoYt(MainActivity.this,url2));
        yt1.setOnClickListener((v)->url.GotoYt(MainActivity.this,url3));

        //Course_Details
        firstCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,onlineteaching.class);
                i.putExtra("email1",registerEmail);
                startActivity(i);
            }
        });
        secondCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,optionstrategy.class);
                i.putExtra("email2",registerEmail);
                startActivity(i);
            }
        });
        comboCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,combo.class);
                i.putExtra("email3",registerEmail);
                startActivity(i);
            }
        });
        //Contact_Floating_Button
        contact.setOnClickListener((v)->url.contactFloting(call, email, telegram, youtube));

        //Contact_yt
        youtube.setOnClickListener((v)->url.GotoYt(MainActivity.this,url1));

        //Contact_Call
        call.setOnClickListener((v)->url.callFloting(MainActivity.this));

        //Contact_Email
        email.setOnClickListener((v)->url.emailFloting(MainActivity.this));

        //Contact_Telegram
        telegram.setOnClickListener((v)->url.GotoYt(MainActivity.this,url4));

        menuBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,menu.class);
                i.putExtra("emai", registerEmail);
                startActivity(i);
            }
        });




    }



    }
