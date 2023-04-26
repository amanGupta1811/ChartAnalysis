package com.acpitzone.chartanalysis;

import static com.acpitzone.chartanalysis.url.callFloting;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.chartanalysis.R;
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
import java.util.HashMap;
import java.util.Map;
public class login extends AppCompatActivity {
    TextView registerTxt;
    ImageView menuBack;
    Button skipBtn,login,helpline;
    EditText emailTxt, passTxt;
    ProgressBar progressBar;
    String emailStr, nameL, emailL, contactL;
    String url = "https://sdcsupermarket.com/login.php";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        skipBtn = findViewById(R.id.skip);
        login = findViewById(R.id.buttonSignIn);
        emailTxt = findViewById(R.id.emalIdSignIn);
        passTxt = findViewById(R.id.passWordSignIn);
        registerTxt = findViewById(R.id.registerbtn);
        progressBar = findViewById(R.id.progressBar);
        helpline = findViewById(R.id.helpline);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                finish();}});
        helpline.setOnClickListener((v)->callFloting(login.this));
        login.setOnClickListener((v)->loginToAcc());
        registerTxt.setOnClickListener((v)->startActivity(new Intent(login.this,signUp.class)));
    }
    void loginToAcc(){
        emailStr = emailTxt.getText().toString();
        String passStr = passTxt.getText().toString();

        boolean isValidate = validateData(emailStr,passStr);

        if(!isValidate){
            return;
        }

        loginUserToDatabase(emailStr, passStr);

    }

    boolean validateData(String emailStr, String passStr){

        if(emailStr.trim().length()==0){
            emailTxt.setError("Please enter Email Id");
            return false;
        }
        else if (passStr.trim().length()==0){
            passTxt.setError("Please enter Password");
            return false;
        }
        return true;
    }

    void loginUserToDatabase(final String email, final String password){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);


//                JSONObject jO = null;
                try {
                    JSONObject jO = new JSONObject(response);



                    String log = jO.getString("Login");

                    Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
                    if (log.matches("1")) {

                        JSONObject jsonObject = null;

                        jsonObject = new JSONObject(response);

                        String fetch = jsonObject.getString("on_success");

                        JSONArray jsonArray = jsonObject.getJSONArray("raw");
                        if (fetch.matches("1")) {

                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject object = jsonArray.getJSONObject(j);

                                nameL = object.getString("name");
                                emailL = object.getString("email");
                                contactL = object.getString("contact");
                            }
                        }


                        Intent i = new Intent(login.this, MainActivity.class);
                        i.putExtra("email", emailStr);
                        startActivity(i);
                        SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username", email);
                        editor.putString("Password", password);
                        editor.putString("nameL", nameL);
                        editor.putString("contactL", contactL);
                        editor.apply();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                        emailTxt.setText("");
                        passTxt.setText("");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Connection Error", Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("email",email);
                map.put("password",password);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}