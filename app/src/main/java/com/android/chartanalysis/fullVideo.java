package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class fullVideo extends AppCompatActivity {

    VideoView videoView;
    TextView title;
    MediaController mediaController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_video);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        videoView = findViewById(R.id.videoView2);
        title = findViewById(R.id.title2);

        String link = getIntent().getStringExtra("link");
        String titleStr = getIntent().getStringExtra("title2");
        title.setText(titleStr);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(link));
        videoView.start();

    }
}