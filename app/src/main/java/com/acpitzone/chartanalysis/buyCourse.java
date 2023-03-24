package com.acpitzone.chartanalysis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acpitzone.chartanalysis.Utility.AvenuesParams;
import com.acpitzone.chartanalysis.Utility.Constants;
import com.acpitzone.chartanalysis.Utility.ServiceUtility;
import com.android.chartanalysis.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
    String order_ID;
//    String status = "ok";
    boolean status = true;
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
                buyDetailsToDB(course,amt,email,date);

               // if(status) {

               // }
            }
        });
    }

    void buyDetailsToDB(final String course,final String amt,final String email,final String date){

        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                if (response.toString().equals("Purchase Succesfull")) {
                    pay(String.valueOf(1));
                    finish();

                } else {
//                    status = false;
                    finish();
//                    status = "buy";
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             Toast.makeText(buyCourse.this, "Please login to your account or Check your connection",Toast.LENGTH_LONG).show();
             progressBar.setVisibility(View.GONE);
//                status = false;
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

    public void pay(String amount){
        String vAccessCode = ServiceUtility.chkNull(Constants.access_code).toString().trim();
        Log.d("Access_code",vAccessCode);
        String vMerchantId = ServiceUtility.chkNull(Constants.merchantId).toString().trim();
        String vCurrency = ServiceUtility.chkNull(Constants.currency).toString().trim();
        String vAmount = ServiceUtility.chkNull(amount).toString().trim();

        if(!vAccessCode.equals("") && !vMerchantId.equals("") && !vCurrency.equals("") && !vAmount.equals("")){
            Intent intent = new Intent(buyCourse.this,WebViewActivity.class);
            intent.putExtra(AvenuesParams.ACCESS_CODE, ServiceUtility.chkNull(Constants.access_code).toString().trim());
            intent.putExtra(AvenuesParams.MERCHANT_ID, ServiceUtility.chkNull(Constants.merchantId).toString().trim());
            intent.putExtra(AvenuesParams.ORDER_ID, ServiceUtility.chkNull(order_ID).toString().trim());
            intent.putExtra(AvenuesParams.CURRENCY, ServiceUtility.chkNull(Constants.currency).toString().trim());
            intent.putExtra(AvenuesParams.AMOUNT, ServiceUtility.chkNull(amount).toString().trim());

            intent.putExtra(AvenuesParams.REDIRECT_URL, ServiceUtility.chkNull(Constants.redirectUrl).toString().trim());
            intent.putExtra(AvenuesParams.CANCEL_URL, ServiceUtility.chkNull(Constants.cancelUrl).toString().trim());
            intent.putExtra(AvenuesParams.RSA_KEY_URL, ServiceUtility.chkNull(Constants.rsaKeyUrl).toString().trim());

            startActivity(intent);
        }else{
            showToast("All parameters are mandatory.");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        //generating new order number for every transaction
        Integer randomNum = ServiceUtility.randInt(0, 9999999);
        order_ID=randomNum.toString();
    }

    public void showToast(String msg) {
        Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
    }

}