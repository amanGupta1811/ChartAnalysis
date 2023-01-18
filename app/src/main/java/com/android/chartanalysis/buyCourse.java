package com.android.chartanalysis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;

public class buyCourse extends AppCompatActivity {

   public TextView courseName, courseAmt, teche;
    ImageButton backBtn;
    Button payBtn;
    String url = "https://sdcsupermarket.com/purchage_details.php";

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

        Intent intent = getIntent();

        String course = intent.getStringExtra("courseName");
        String amt = intent.getStringExtra("courseAmt");
        String email = intent.getStringExtra("emailStr");
        courseName.setText(course);
        courseAmt.setText(amt);

        backBtn.setOnClickListener((v) -> finish());
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buyDeatailsToDB(course,amt,email);


            }
        });
    }

    void buyDeatailsToDB(final String course,final String amt,final String email){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                if (response.toString().equals("Purchase Succesfull")) {
                    finish();
                } else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("course", course);
                map.put("amount", amt);
                map.put("email", email);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}