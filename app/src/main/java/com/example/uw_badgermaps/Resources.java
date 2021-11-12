package com.example.uw_badgermaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Resources extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }

    public void safeWalkClick() {
        SafeWalkPopUp safeWalkPopUp = new SafeWalkPopUp();
        safeWalkPopUp.show(getSupportFragmentManager(), "Safe Walk");
    }

    public void uhsClick() {
        UHSPopUp uhsPopUp = new UHSPopUp();
        uhsPopUp.show(getSupportFragmentManager(), "UHS");
    }

    public void librariesClick() {
        LibrariesPopUp librariesPopUp = new LibrariesPopUp();
        librariesPopUp.show(getSupportFragmentManager(), "Libraries");
    }

    public void techHelpClick() {
        TechHelpPopUp techHelpPopUp = new TechHelpPopUp();
        techHelpPopUp.show(getSupportFragmentManager(), "Tech Help");
    }

    public void employmentClick() {
        EmploymentPopUp employmentPopUp = new EmploymentPopUp();
        employmentPopUp.show(getSupportFragmentManager(), "Employment");
    }
}