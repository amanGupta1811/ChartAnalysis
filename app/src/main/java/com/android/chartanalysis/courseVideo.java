package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class courseVideo extends AppCompatActivity {

    String url = "https://sdcsupermarket.com/fetch_video.php";
    String url1 = "https://sdcsupermarket.com/user_details.php";
    private ArrayList<videoData> data = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<videoData> arrayList = new ArrayList<>();
    private courseVidAdapter courseVidAdapter;
    ProgressBar progressBar;
    ImageButton back;
    ImageView invoiceT;
    String id, course, email, amount, date, nameF, emailF, contactF;
    Button invoice;
    boolean touch = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_video);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.backBtnV);
        invoiceT = findViewById(R.id.invoiceT);
        invoice = findViewById(R.id.invoice);

        id = getIntent().getStringExtra("id");
        course = getIntent().getStringExtra("check");
        amount = getIntent().getStringExtra("amt");
        email = getIntent().getStringExtra("email0");
        date = getIntent().getStringExtra("date");

        invoiceT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(touch){
                   invoice.setVisibility(View.VISIBLE);
                   touch = false;
               }
               else{
                   invoice.setVisibility(View.GONE);
                   touch = true;
               }
            }
        });

        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDetails();

            }
        });


        back.setOnClickListener((v)->finish());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2,LinearLayoutManager.VERTICAL, false));
        courseVidAdapter = new courseVidAdapter(arrayList, getApplicationContext());
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        fetchData();
        recyclerView.setAdapter(courseVidAdapter);

    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String fetchSuccess = jsonObject.getString("on_success");

                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    if (fetchSuccess.matches("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String title = object.getString("title");
                            String video = "https://sdcsupermarket.com/videos/" + object.getString("video");
                            String thumbnail = "https://sdcsupermarket.com/thumbnail/" + object.getString("thumbnail");
                            videoData videoData = new videoData(id, title, video, thumbnail);
                            data.add(videoData);
                        }
                        courseVidAdapter.upDate(data);


                    } else {
                        Toast.makeText(courseVideo.this, "Error", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(courseVideo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "Mozilla/5.0");
                return headers;
            }

            public Priority getPriority() {
                return Priority.IMMEDIATE;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void fetchDetails(){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String fetch = jsonObject.getString("on_success");

                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (fetch.matches("1")) {

                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject object = jsonArray.getJSONObject(j);

                            nameF = object.getString("name");
                            emailF = object.getString("email");
                            contactF = object.getString("contact");

                            if (emailF.equals(email)) {
                                Intent i = new Intent(courseVideo.this, invoice.class);
                                i.putExtra("id1", id);
                                i.putExtra("course1", course);
                                i.putExtra("amt1", amount);
                                i.putExtra("email1", email);
                                i.putExtra("date", date);
                                i.putExtra("name", nameF);
                                i.putExtra("mobile", contactF);
                                invoiceT.setVisibility(View.GONE);
                                startActivity(i);
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                invoiceT.setVisibility(View.GONE);
                Toast.makeText(courseVideo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "Mozilla/5.0");
                return headers;
            }

            public Priority getPriority() {
                return Priority.IMMEDIATE;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}