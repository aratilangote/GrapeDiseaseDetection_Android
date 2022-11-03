package com.capstoneProject.grapebhumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Thread background = new Thread(){
            public void run(){
                try{
                    sleep(4*1000);
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                    finish();
                }
                catch (Exception e){}
            }
        };
        background.start();
    }
}