package com.android.chartanalysis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class buyCourse extends AppCompatActivity {

   public TextView courseName, courseAmt, teche;
    ImageButton backBtn;
    Button payBtn;
    String url = "https://sdcsupermarket.com/purchage_details.php";
    ProgressBar progressBar;
//    menu menu;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_course);

        courseName = findViewById(R.id.courseName);
        courseAmt = findViewById(R.id.courseAmt);
        backBtn = findViewById(R.id.backBtn);
        payBtn = findViewById(R.id.payBtn);
        progressBar = findViewById(R.id.progress);

        Intent intent = getIntent();

        String course = intent.getStringExtra("courseName");
        String amt = intent.getStringExtra("courseAmt");
        String email = intent.getStringExtra("emailStr");
        String emai = intent.getStringExtra("email10");
        courseName.setText(course);
        courseAmt.setText(amt);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        backBtn.setOnClickListener((v) -> finish());
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buyDeatailsToDB(course,amt,email,date);


            }
        });
    }

    void buyDeatailsToDB(final String course,final String amt,final String email,final String date){

        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                if (response.toString().equals("Purchase Succesfull")) {
                    finish();

                } else {
                    finish();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                finish();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("course", course);
                map.put("amount", amt);
                map.put("email", email);
                map.put("orderDate",date);


                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}