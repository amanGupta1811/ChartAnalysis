package com.acpitzone.chartanalysis;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chartanalysis.R;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class invoice extends AppCompatActivity {


    TextView invoice,orderId, courseName, cusName, price, sgstP, cgstP, priceT, invoDate, orderD,mobile;
    String courseF, amtF, idF, emailF,dateF,nameF, mobileF;
    int amtI;
    Button download, back;



    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp;
    Bitmap scaledbmp;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;

    @SuppressLint("MissingInflatedId")
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
        download = findViewById(R.id.down);
        orderD = findViewById(R.id.orderT);
        cusName = findViewById(R.id.cus_name);
        mobile = findViewById(R.id.mobileNo);
        back = findViewById(R.id.back);

        idF = getIntent().getStringExtra("id1");
        courseF = getIntent().getStringExtra("course1");
        amtF = getIntent().getStringExtra("amt1");
        dateF = getIntent().getStringExtra("date");
        nameF = getIntent().getStringExtra("name");
        mobileF = getIntent().getStringExtra("mobile");

//        download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        back.setOnClickListener((v)->finish());

        if(amtF.equals("7999")){
            amtI = 7999;
        }
        else if(amtF.equals("10999")){
            amtI = 10999;
        }

        else{
            amtI = 16999;
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
        orderD.setText(dateF);
        cusName.setText(nameF);
        mobile.setText(mobileF);

    }




}