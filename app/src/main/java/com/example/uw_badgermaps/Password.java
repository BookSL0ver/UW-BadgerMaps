package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    public void passConfirm(View view){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void passCancel(View view) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }
}