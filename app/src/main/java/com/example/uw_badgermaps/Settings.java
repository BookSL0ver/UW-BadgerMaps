package com.example.uw_badgermaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {
    ToggleButton darkMode;
    public int dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spinner = (Spinner) findViewById(R.id.gpsPrecSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gps_precision, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        darkMode = findViewById(R.id.darkModeSwitch);

    }


    public void toggle(View view) {
        if(darkMode.isChecked()) { // Dark mode is on
            dark = 1;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{ // Dark mode is off
            dark = 0;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
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
                intentH.putExtra("isDark", dark);
                startActivity(intentH);
                return true;
            case R.id.resources:
                Intent intentR = new Intent(this, Resources.class);
                intentR.putExtra("isDark", dark);
                startActivity(intentR);
                return true;
            case R.id.settings:
                Intent intentS = new Intent(this, Settings.class);
                intentS.putExtra("isDark", dark);
                startActivity(intentS);
                return true;

        }
        return true;
    }

}