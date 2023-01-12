package com.android.chartanalysis;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class contactUs extends AppCompatActivity {

    ImageButton backBtn;
    EditText nameTxt, emailTxt, phnTxt, msgTxt;
    Button submit;
    boolean touch = true;
    FloatingActionButton call, email, telegram, youtube, contact;
    String url1 = "https://www.youtube.com/@chartanalysis123";
    String url4 = "https://t.me/chartanalysis_umesh_sharma";

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
        call = findViewById(R.id.call);
        email = findViewById(R.id.emailBtn);
        telegram = findViewById(R.id.tele);
        youtube = findViewById(R.id.youtubeBtn);
        contact = findViewById(R.id.btn_contact);

        //Back_Btn
        backBtn.setOnClickListener((v)->finish());

        //Submit_Btn
        submit.setOnClickListener((v)->composeEamil());

        //Contact_Floating_Button
        contact.setOnClickListener((v)->url.contactFloting(call,email,telegram,youtube));

        //Contact_yt
        youtube.setOnClickListener((v)->url.GotoYt(contactUs.this,url1));

        //Contact_Telegram
        telegram.setOnClickListener((v)->url.GotoYt(contactUs.this,url4));

        //Contact_Call
        call.setOnClickListener((v)->url.callFloting(contactUs.this));

        //Contact_Email
        email.setOnClickListener((v)->url.emailFloting(contactUs.this));
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
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        else if(phnStr.length()!=10){
            phnTxt.setError("Phone no. length is invalid");
            return false;
        }
        else if(msgStr.isEmpty()){
            msgTxt.setError("Enter message");
            return false;
        }
        return true;
    }
}