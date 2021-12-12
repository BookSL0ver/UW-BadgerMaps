package com.example.uw_badgermaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends FragmentActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LatLng mLocationLatLng;
    private GoogleMap mMap;
    //set to campus area for map zoom
    private final LatLng mDestinationLatLng = new LatLng(43.0720, -89.4076);
    SearchView searchView;
    private LatLng latLng;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            //zoom into campus
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDestinationLatLng, 17));
            displayMyLocation();
            searchView = findViewById(R.id.svLocation);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;

                    if (location != null || !location.equals("")) {
                        Geocoder geocoder = new Geocoder(MapActivity.this);
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                        latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        });
        delayLoop();
    }

    //delay loop to update position
     public void delayLoop(){
        try {
            Thread.sleep(2000);
            displayMyLocation();
        } catch (InterruptedException ex) {
            return;
        } catch (NullPointerException e) {
            return;
        }

    }

    public void arrived(View view){

       dialog = new Dialog(this);
       dialog.setContentView(R.layout.popup_dialog);

        Button close = dialog.findViewById(R.id.button2);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button getFloor = dialog.findViewById(R.id.button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get image of correct floor
            }
        });
       dialog.show();
    }


    public void closePopup(){
        dialog.dismiss();
    }

    /**
    public boolean checkGoal(LatLng curLocation, LatLng destination){
        if(((curLocation.latitude - destination.latitude < 0.00035) || (curLocation.latitude - destination.latitude > -0.00035)) & ((curLocation.longitude - destination.longitude < 0.00035) || (curLocation.longitude - destination.longitude > -0.00035))){

        }
        return true;
    }
     */

    private void displayMyLocation() {
        int permission = ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        else {
            mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, task -> {
                Location mLastKnownLocation = task.getResult();
                if (task.isSuccessful() && mLastKnownLocation != null) {
                    mLocationLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(mLocationLatLng).title("Current Location"));
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantRestults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantRestults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantRestults.length > 0 && grantRestults[0] == PackageManager.PERMISSION_GRANTED) {
                displayMyLocation();
            }
        }
    }

    public void filters(View view) {
        mMap.clear();
        Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        //TODO
    }
}