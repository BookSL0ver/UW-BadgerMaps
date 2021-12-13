package com.example.uw_badgermaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intentH = new Intent(this, MapActivity.class);
                startActivity(intentH);
                return true;
            case R.id.resources:
                Intent intentR = new Intent(this, Resources.class);
                startActivity(intentR);
                return true;
            case R.id.settings:
                Intent intentS = new Intent(this, Settings.class);
                startActivity(intentS);
                return true;

        }
        return true;
    }

}