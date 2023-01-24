package com.android.chartanalysis;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView logBtn,yt1,yt2;
    ImageView menuBTn;
    TextView seeAll, r_email, home, courseA, about, contactA, online, option;
    CardView firstCourse,secondCourse,comboCourse;
    FloatingActionButton call, email, telegram, youtube, contact;
    String registerEmail,course;
    boolean touch = true;
    ConstraintLayout menu;
    ImageButton back;
    String courseB, emailB, idB, rateB;
    ProgressBar progressBar;
    String url5 = "https://sdcsupermarket.com/check_purchage_user.php";
    String url1 = "https://www.youtube.com/@chartanalysis123";
    String url2 = "https://www.youtube.com/watch?v=Ab67mKtfXok";
    String url3 = "https://www.youtube.com/watch?v=x7_deJR3f38";
    String url4 = "https://t.me/chartanalysis_umesh_sharma";



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menuBTn = findViewById(R.id.menu_btn);
        seeAll = findViewById(R.id.ytAll);
        yt1 = findViewById(R.id.yt1);
        yt2 = findViewById(R.id.yt2);
        firstCourse = findViewById(R.id.firstCourse);
        secondCourse = findViewById(R.id.secondCourse);
        comboCourse = findViewById(R.id.thirdCourse);
        call = findViewById(R.id.call);
        email = findViewById(R.id.email);
        telegram = findViewById(R.id.tele);
        youtube = findViewById(R.id.youtubeBtn);
        contact = findViewById(R.id.btn_contact);
        menu = findViewById(R.id.men);
        r_email = findViewById(R.id.nameA);
        back = findViewById(R.id.back_btnA);
        home = findViewById(R.id.categoriesA);
        contactA = findViewById(R.id.wishlistA);
        about = findViewById(R.id.your_ordersA);
        courseA = findViewById(R.id.techA);
        option = findViewById(R.id.optionA);
        online = findViewById(R.id.onlineA);
        progressBar = findViewById(R.id.progressB);

        registerEmail = getIntent().getStringExtra("email");

        r_email.setText(registerEmail);

       // logBtn.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, invoice.class)));

        //Menu_Bar
        // menuBTn.setOnClickListener((v)->startActivity(new Intent(MainActivity.this, menu.class)));

        //Open_Youtube
        seeAll.setOnClickListener((v)->url.GotoYt(MainActivity.this,url1));
        yt2.setOnClickListener((v)->url.GotoYt(MainActivity.this,url2));
        yt1.setOnClickListener((v)->url.GotoYt(MainActivity.this,url3));

        //Course_Details
        firstCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,onlineteaching.class);
                i.putExtra("email1",registerEmail);
                startActivity(i);
            }
        });
        secondCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,optionstrategy.class);
                i.putExtra("email2",registerEmail);
                startActivity(i);
            }
        });
        comboCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,combo.class);
                i.putExtra("email3",registerEmail);
                startActivity(i);
            }
        });
        //Contact_Floating_Button
        contact.setOnClickListener((v)->url.contactFloting(call, email, telegram, youtube));

        //Contact_yt
        youtube.setOnClickListener((v)->url.GotoYt(MainActivity.this,url1));

        //Contact_Call
        call.setOnClickListener((v)->url.callFloting(MainActivity.this));

        //Contact_Email
        email.setOnClickListener((v)->url.emailFloting(MainActivity.this));

        //Contact_Telegram
        telegram.setOnClickListener((v)->url.GotoYt(MainActivity.this,url4));

        menuBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,menu.class);
//                i.putExtra("emai",registerEmail);
//                startActivity(i);
                menu.setVisibility(View.VISIBLE);
            }
        });

      //  back.setOnClickListener((v)-> menu.setVisibility(View.GONE));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                online.setVisibility(View.GONE);
                option.setVisibility(View.GONE);
                menu.setVisibility(View.GONE);

            }
        });

        home.setOnClickListener((v)-> menu.setVisibility(View.GONE));

        contactA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, contactUs.class);
                startActivity(i);
                menu.setVisibility(View.GONE);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, aboutUs.class);
                startActivity(i);
                menu.setVisibility(View.GONE);
            }
        });

        courseA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(touch){
                    online.setVisibility(View.VISIBLE);
                    option.setVisibility(View.VISIBLE);
                    touch = false;
                }
                else{
                    online.setVisibility(View.GONE);
                    option.setVisibility(View.GONE);
                    touch = true;

                }
            }
        });

                //videos

            online.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    online();

                    }

                });




            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    option();


                }
            });
        }

        void online(){
        progressBar.setVisibility(View.VISIBLE);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url5, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String fetchSuccess = jsonObject.getString("on_success");

                        JSONArray jsonArray = jsonObject.getJSONArray("response");

                        if (fetchSuccess.matches("1")) {
                            int a = 0;

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                courseB = object.getString("course");
                                emailB = object.getString("email");
                                idB = object.getString("id");
                                rateB = object.getString("amount");


                                if (courseB.equals("Online technical analysis") && emailB.equals(registerEmail)) {
                                    Intent intent = new Intent(MainActivity.this, courseVideo.class);
                                    intent.putExtra("check", courseB);
                                    intent.putExtra("email0", registerEmail);
                                    intent.putExtra("id", idB);
                                    intent.putExtra("amt",rateB);
                                    startActivity(intent);
                                    menu.setVisibility(View.GONE);
                                    a = 1;
                                    //  progressBar.setVisibility(View.GONE);
                                    //break;
                                }

                            }

                            if (a == 0) {
//                                Intent i = new Intent(menu.this, onlineteaching.class);
//                                // i.putExtra("check","aman");
//                                i.putExtra("email0", nameStr);
//                                startActivity(i);
                                menu.setVisibility(View.GONE);
                                // progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Buy online technical analysis ", Toast.LENGTH_SHORT).show();
                            }


                        } else {
//                            Intent i = new Intent(menu.this, onlineteaching.class);
//                            // i.putExtra("check","aman");
//                            i.putExtra("email0", nameStr);
//                            startActivity(i);
                            // progressBar.setVisibility(View.GONE);
                            menu.setVisibility(View.GONE);


                            Toast.makeText(MainActivity.this, "Buy online technical analysis ", Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();

                    map.put("email", registerEmail);

                    return map;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

        void option(){
        progressBar.setVisibility(View.VISIBLE);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url5, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String fetchSuccess = jsonObject.getString("on_success");

                        JSONArray jsonArray = jsonObject.getJSONArray("response");

                        if (fetchSuccess.matches("1")) {
                            int a = 0;

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                courseB = object.getString("course");
                                emailB = object.getString("email");
                                idB = object.getString("id");
                                rateB = object.getString("amount");

//                                    cours.add(course);
//                                    ema.add(email);

                                if (courseB.equals("Option Strategy") && emailB.equals(registerEmail)) {
                                    Intent intent = new Intent(MainActivity.this, courseVideo.class);
                                    intent.putExtra("check", courseB);
                                    intent.putExtra("email0", registerEmail);
                                    intent.putExtra("id", idB);
                                    intent.putExtra("amt",rateB);
                                    startActivity(intent);
                                    menu.setVisibility(View.GONE);

                                    //progressBar.setVisibility(View.GONE);
                                    a = 1;
                                }

                            }

                            if (a == 0) {
//                                Intent i = new Intent(menu.this, optionstrategy.class);
//                                // i.putExtra("check","aman");
//                                i.putExtra("email0", nameStr);
//                                startActivity(i);
                                menu.setVisibility(View.GONE);

                                // progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Buy option strategy ", Toast.LENGTH_SHORT).show();
                            }


                        } else {
//                            Intent i = new Intent(menu.this, optionstrategy.class);
//                            // i.putExtra("check","aman");
//                            i.putExtra("email0", nameStr);
//                            startActivity(i);
                            menu.setVisibility(View.GONE);

                            // progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Buy option strategy", Toast.LENGTH_SHORT).show();


                            // Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();

                    map.put("email", registerEmail);

                    return map;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

        @Override
        public void onBackPressed(){

           final AlertDialog.Builder newAlertDialog = new AlertDialog.Builder(this);

            newAlertDialog.setTitle("ChartAnalysis").setMessage("Are you sure you want to close this app?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Toast.makeText(MainActivity.this, "ChartAnalysis closed",Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("No", null).show();

        }

}
