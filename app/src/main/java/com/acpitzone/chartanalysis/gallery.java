package com.acpitzone.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.chartanalysis.R;

public class gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton back = (ImageButton)findViewById(R.id.back_btnG);

        back.setOnClickListener((v)->finish());

        WebView webView = findViewById(R.id.web);

        // loading http://www.google.com url in the WebView.

        webView.loadUrl("https://chartanalysis.co.in/gallery.php");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}