package com.android.chartanalysis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class url {

    static boolean touch = true;

    public static void GotoYt(Context context, String url){
        Uri uri = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        context.startActivity(intent);
    }

    public static void contactFloting(FloatingActionButton call, FloatingActionButton email, FloatingActionButton telegram, FloatingActionButton youtube){
        if (touch){
            call.show();
            email.show();
            telegram.show();
            youtube.show();
            touch=false;
        }
        else{
            call.hide();
            email.hide();
            telegram.hide();
            youtube.hide();
            touch = true;
        }
    }

    public static void callFloting(Context context){
                String phn = "tel:+919711501546";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phn));
                context.startActivity(callIntent);
            }

     public static void emailFloting(Context context){
         Intent intent = new Intent(Intent.ACTION_VIEW);
         Uri data = Uri.parse("mailto:");
         intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"chartanalysis.us@gmail.com"});
         intent.setData(data);
         context.startActivity(intent);
     }
}