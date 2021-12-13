package com.example.uw_badgermaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Resources extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }

    public void safeWalkClick(View view) {
        SafeWalkPopUp safeWalkPopUp = new SafeWalkPopUp();
        safeWalkPopUp.show(getSupportFragmentManager(), "Safe Walk");
    }

    public void uhsClick(View view) {
        UHSPopUp uhsPopUp = new UHSPopUp();
        uhsPopUp.show(getSupportFragmentManager(), "UHS");
    }

    public void librariesClick(View view) {
        LibrariesPopUp librariesPopUp = new LibrariesPopUp();
        librariesPopUp.show(getSupportFragmentManager(), "Libraries");
    }

    public void techHelpClick(View view) {
        TechHelpPopUp techHelpPopUp = new TechHelpPopUp();
        techHelpPopUp.show(getSupportFragmentManager(), "Tech Help");
    }

    public void employmentClick(View view) {
        EmploymentPopUp employmentPopUp = new EmploymentPopUp();
        employmentPopUp.show(getSupportFragmentManager(), "Employment");
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