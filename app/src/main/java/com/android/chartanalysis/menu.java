//package com.android.chartanalysis;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class menu extends AppCompatActivity {
//
////    private final Context context;
//    ImageView btnBack;
//    TextView home,contact, about;
//    TextView tech, name, option, online, courses;
//    boolean touch = true;
//    String course,email;
//    String nameStr;
//    ProgressBar progressBar;
//    String url = "https://sdcsupermarket.com/check_purchage_user.php";
////    private ArrayList<String> cours = new ArrayList<>();
////    private ArrayList<String> ema = new ArrayList<>();
//
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        btnBack = findViewById(R.id.back_btn);
//        home = findViewById(R.id.categories);
//        contact = findViewById(R.id.wishlist);
//        about = findViewById(R.id.your_orders);
//        courses = findViewById(R.id.tech);
//        name = findViewById(R.id.name);
//        option = findViewById(R.id.option);
//        online = findViewById(R.id.online);
//        progressBar = findViewById(R.id.progressB);
//
//        String check = getIntent().getStringExtra("check");
//        nameStr = getIntent().getStringExtra("emai");
//        name.setText(nameStr);
////
////        if(check.equals("Online technical analysis")){
////            tech.setVisibility(View.VISIBLE);
////        }
////        else if(check.equals("Option Strategy")){
////
////            option.setVisibility(View.VISIBLE);
////
////        }
//
//
//
//
//        //Back_Button
//        btnBack.setOnClickListener((v) -> finish());
//
//        //Home_Button
//        home.setOnClickListener((v) -> finish());
//
//        //Contact_Us
//        contact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(menu.this, contactUs.class);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        //About_Us
//        about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(menu.this, aboutUs.class);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        //Courses
//        courses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(touch){
//                    online.setVisibility(View.VISIBLE);
//                    option.setVisibility(View.VISIBLE);
//                    touch = false;
//                }
//                else{
//                    online.setVisibility(View.GONE);
//                    option.setVisibility(View.GONE);
//                    touch = true;
//
//                }
//            }
//        });
//
//
//        //videos
//
//            online.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    online();
//
//                    }
//
//                });
//
//
//
//
//            option.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    option();
//
//
//                }
//            });
//        }
//
//        void online(){
//        progressBar.setVisibility(View.VISIBLE);
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    progressBar.setVisibility(View.GONE);
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String fetchSuccess = jsonObject.getString("on_success");
//
//                        JSONArray jsonArray = jsonObject.getJSONArray("response");
//
//                        if (fetchSuccess.matches("1")) {
//                            int a = 0;
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                JSONObject object = jsonArray.getJSONObject(i);
//
//                                course = object.getString("course");
//                                email = object.getString("email");
//
//                                if (course.equals("Online technical analysis") && email.equals(nameStr)) {
//                                    Intent intent = new Intent(menu.this, courseVideo.class);
//                                    intent.putExtra("check", course);
//                                    intent.putExtra("email0", nameStr);
//                                    startActivity(intent);
//                                    finish();
//                                    a = 1;
//                                    //  progressBar.setVisibility(View.GONE);
//                                    //break;
//                                }
//
//                            }
//
//                            if (a == 0) {
////                                Intent i = new Intent(menu.this, onlineteaching.class);
////                                // i.putExtra("check","aman");
////                                i.putExtra("email0", nameStr);
////                                startActivity(i);
//                                finish();
//                                // progressBar.setVisibility(View.GONE);
//                                Toast.makeText(menu.this, "Buy online technical analysis ", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } else {
////                            Intent i = new Intent(menu.this, onlineteaching.class);
////                            // i.putExtra("check","aman");
////                            i.putExtra("email0", nameStr);
////                            startActivity(i);
//                            // progressBar.setVisibility(View.GONE);
//                            finish();
//
//                            Toast.makeText(menu.this, "Buy online technical analysis ", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(menu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Nullable
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> map = new HashMap<String, String>();
//
//                    map.put("email", nameStr);
//
//                    return map;
//                }
//            };
//
//
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }
//
//        void option(){
//        progressBar.setVisibility(View.VISIBLE);
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    progressBar.setVisibility(View.GONE);
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String fetchSuccess = jsonObject.getString("on_success");
//
//                        JSONArray jsonArray = jsonObject.getJSONArray("response");
//
//                        if (fetchSuccess.matches("1")) {
//                            int a = 0;
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                JSONObject object = jsonArray.getJSONObject(i);
//
//                                course = object.getString("course");
//                                email = object.getString("email");
//
////                                    cours.add(course);
////                                    ema.add(email);
//
//                                if (course.equals("Option Strategy") && email.equals(nameStr)) {
//                                    Intent intent = new Intent(menu.this, courseVideo.class);
//                                    intent.putExtra("check", course);
//                                    intent.putExtra("email0", nameStr);
//                                    startActivity(intent);
//                                    finish();
//                                    //progressBar.setVisibility(View.GONE);
//                                    a = 1;
//                                }
//
//                            }
//
//                            if (a == 0) {
////                                Intent i = new Intent(menu.this, optionstrategy.class);
////                                // i.putExtra("check","aman");
////                                i.putExtra("email0", nameStr);
////                                startActivity(i);
//                                finish();
//                                // progressBar.setVisibility(View.GONE);
//                                Toast.makeText(menu.this, "Buy option strategy ", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } else {
////                            Intent i = new Intent(menu.this, optionstrategy.class);
////                            // i.putExtra("check","aman");
////                            i.putExtra("email0", nameStr);
////                            startActivity(i);
//                            finish();
//                            // progressBar.setVisibility(View.GONE);
//                            Toast.makeText(menu.this, "Buy option strategy", Toast.LENGTH_SHORT).show();
//
//
//                            // Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(menu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Nullable
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> map = new HashMap<String, String>();
//
//                    map.put("email", nameStr);
//
//                    return map;
//                }
//            };
//
//
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }
//}
//
//
//
//
