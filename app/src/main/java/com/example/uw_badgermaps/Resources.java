package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
}