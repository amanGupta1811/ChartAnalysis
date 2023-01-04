package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class contactUs extends AppCompatActivity {

    ImageButton backBtn;
    EditText nameTxt, emailTxt, phnTxt, msgTxt;
    Button submit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        backBtn = findViewById(R.id.back_btn);
        nameTxt = findViewById(R.id.name);
        emailTxt = findViewById(R.id.email);
        phnTxt = findViewById(R.id.phn);
        msgTxt = findViewById(R.id.msg);
        submit = findViewById(R.id.btn);

        //Back_Btn
        backBtn.setOnClickListener((v)->finish());

        //Submit_Btn
        submit.setOnClickListener((v)->composeEamil());
    }


    //Compose_Email
    void composeEamil(){

       String nameStr = nameTxt.getText().toString();
       String emailStr = emailTxt.getText().toString();
       String phnStr = phnTxt.getText().toString();
       String msgStr = msgTxt.getText().toString();

        boolean isValidate = validateData(nameStr,emailStr,phnStr,msgStr);

        if(!isValidate){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "chartanalysis.us@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,nameStr+"\n"+emailStr+"\n"+phnStr);
        intent.putExtra(Intent.EXTRA_TEXT,msgStr);
        intent.setData(data);
        startActivity(intent);
    }

    //Validate_Data
    boolean validateData(String nameStr,String emailStr, String phnStr, String msgStr){

        if(nameStr.isEmpty()){
            nameTxt.setError("Enter name");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        if(phnStr.length()!=10){
            phnTxt.setError("Phone no. length is invalid");
            return false;
        }
        if(msgStr.isEmpty()){
            msgTxt.setError("Enter message");
            return false;
        }
        return true;
    }
}