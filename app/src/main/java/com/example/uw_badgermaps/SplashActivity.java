package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //removes the title bar from activity
        try{
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        //delays splash page and moves on to the Home Activity
        h.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, Resources.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}