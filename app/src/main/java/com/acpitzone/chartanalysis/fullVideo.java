package com.acpitzone.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import com.android.chartanalysis.R;
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
//        title = findViewById(R.id.title2);

        String link = getIntent().getStringExtra("link");
        String titleStr = getIntent().getStringExtra("title2");
//        title.setText(titleStr);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(link));
        videoView.start();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Check if the device is in landscape orientation
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Set the activity to fullscreen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            // Hide the ActionBar if it's present
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            // Resize the VideoView to fill the screen
            FrameLayout.LayoutParams layoutParams =
                    (FrameLayout.LayoutParams) videoView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            videoView.setLayoutParams(layoutParams);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Set the activity to not fullscreen
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            // Show the ActionBar if it's hidden
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }

            // Resize the VideoView to its original size
            FrameLayout.LayoutParams layoutParams =
                    (FrameLayout.LayoutParams) videoView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = 800; // or whatever size you want
            videoView.setLayoutParams(layoutParams);
        }
    }
}