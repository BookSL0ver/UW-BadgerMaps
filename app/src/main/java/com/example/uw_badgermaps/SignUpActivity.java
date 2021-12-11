package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void seePassword(View view) {
        //TODO
    }

    public void seePasswordConfirm(View view) {
        //TODO
    }

    public void loginClick(View view) {
        //TODO
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}