package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
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
    private ArrayList<videoData> data = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<videoData> arrayList = new ArrayList<>();
    private courseVidAdapter courseVidAdapter;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_video);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        courseVidAdapter = new courseVidAdapter(arrayList, getApplicationContext());
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
}