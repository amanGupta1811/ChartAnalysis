package com.acpitzone.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.chartanalysis.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class StatusActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    String payment_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        circleImageView=findViewById(R.id.status_image);

        Intent mainIntent = getIntent();
        payment_status=mainIntent.getStringExtra("transStatus");


        Log.d("inMain",payment_status);


        if(payment_status.equals("Transaction Successful!")){

        }

        TextView tv4 = (TextView) findViewById(R.id.textView);
        tv4.setText(mainIntent.getStringExtra("transStatus"));
    }
}