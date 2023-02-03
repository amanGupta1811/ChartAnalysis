package com.acpitzone.chartanalysis;

import static com.acpitzone.chartanalysis.url.GotoYt;
import static com.acpitzone.chartanalysis.url.callFloting;
import static com.acpitzone.chartanalysis.url.contactFloting;
import static com.acpitzone.chartanalysis.url.emailFloting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.chartanalysis.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class aboutUs extends AppCompatActivity {

        VideoView videoView;
        MediaController mediaController;
        ImageButton back;
        FloatingActionButton call, email, telegram, youtube, contact;
        String url1 = "https://www.youtube.com/@chartanalysis123";
        String url4 = "https://t.me/chartanalysis_umesh_sharma";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        videoView = findViewById(R.id.videoView);
        back = findViewById(R.id.back_btn);
        call = findViewById(R.id.call);
        email = findViewById(R.id.emailBtn);
        telegram = findViewById(R.id.tele);
        youtube = findViewById(R.id.youtubeBtn);
        contact = findViewById(R.id.btn_contact);

        //Back_BTN
        back.setOnClickListener((v)->finish());

        //Video
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.caintro));
        videoView.start();

        //Contact_Floating_Button
        contact.setOnClickListener((v)->contactFloting(call, email, telegram, youtube));

        //Contact_yt
        youtube.setOnClickListener((v)->GotoYt(aboutUs.this,url1));

        //Contact_Call
        call.setOnClickListener((v)->callFloting(aboutUs.this));

        //Contact_Email
        email.setOnClickListener((v)->emailFloting(aboutUs.this));

        //Contact_Telegram
        telegram.setOnClickListener((v)->GotoYt(aboutUs.this,url4));

    }
}