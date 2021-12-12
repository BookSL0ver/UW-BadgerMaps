package com.example.uw_badgermaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }

    public void showLibraries(View view){
        Intent intent = new Intent(getApplicationContext(), ShowLibraries.class);
        startActivity(intent);
    }

    public void showUnions(View view){
        Intent intent = new Intent(getApplicationContext(), ShowUnions.class);
        startActivity(intent);
    }

    public void showGyms(View view){
        Intent intent = new Intent(getApplicationContext(), ShowGyms.class);
        startActivity(intent);
    }

    public void showDiningHalls(View view){
        Intent intent = new Intent(getApplicationContext(), ShowDiningHalls.class);
        startActivity(intent);
    }

}
