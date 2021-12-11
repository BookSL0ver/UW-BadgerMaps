package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    public void favoritesClick (View view) {
        Intent intent = new Intent(this, Favorites.class);
        startActivity(intent);
    }

    public void importClick (View view) {
        //TODO if time
    }

    public void passChange (View view) {
        Intent intent = new Intent(this, Password.class);
        startActivity(intent);
    }

    public void logout (View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}