package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class invoice extends AppCompatActivity {


    TextView invoice,orderId, courseName, cusName, price, sgstP, cgstP, priceT, invoDate;
    String courseF, amtF, idF, emailF;
    int amtI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        orderId = findViewById(R.id.order_id);
        invoice = findViewById(R.id.invoT);
        courseName = findViewById(R.id.course);
        price = findViewById(R.id.price);
        sgstP = findViewById(R.id.sgst_price);
        cgstP = findViewById(R.id.cgst_price);
        priceT = findViewById(R.id.pri_total);
        invoDate = findViewById(R.id.invo_dateT);

        idF = getIntent().getStringExtra("id1");
        courseF = getIntent().getStringExtra("course1");
        amtF = getIntent().getStringExtra("amt1");

        if(amtF.equals("\u20b9 7,999")){
            amtI = 7999;
        }
        else if(amtF.equals("\u20b910,999")){
            amtI = 10999;
        }

        int priceI = (int) Math.round((amtI/1.18));

        int sgstI = (int) Math.round((priceI)*0.09);

        int cgstI = (int) Math.round((priceI)*0.09);

        int priceIT = (int) Math.round(priceI + sgstI + cgstI);

        String priceS = Integer.toString(priceI);

        String sgstS = Integer.toString(sgstI);

        String cgstS = Integer.toString(cgstI);

        String priceST = Integer.toString(priceIT);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());




        orderId.setText(idF);
        invoice.setText(idF);
        courseName.setText(courseF);
        price.setText(priceS);
        sgstP.setText(sgstS);
        cgstP.setText(cgstS);
        priceT.setText(priceST);
        invoDate.setText(date);

    }
}