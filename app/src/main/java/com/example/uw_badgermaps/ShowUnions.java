package com.example.uw_badgermaps;

import static java.sql.DriverManager.println;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ShowUnions extends FragmentActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LatLng mLocationLatLng;
    private GoogleMap mMap;
    //set to campus area for map zoom
    private final LatLng mDestinationLatLng = new LatLng(43.0720, -89.4076);
    SearchView searchView;


    private final LatLng MU = new LatLng(43.07625194232345, -89.40000380398523);
    private final LatLng US = new LatLng(43.071992041778906, -89.40765713282042);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            //zoom into campus
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDestinationLatLng,17));
            displayMyLocation();
            addUnionLocations();
            searchView = findViewById(R.id.svLocation);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;

                    if (location != null || !location.equals("")){
                        Geocoder geocoder = new Geocoder(ShowUnions.this);
                        try{
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
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
    }

    private void addUnionLocations() {
        mMap.addMarker(new MarkerOptions().position(MU).title("Memorial Union"));
        mMap.addMarker(new MarkerOptions().position(US).title("Union South"));
    }

    public void displayMyLocation() {
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

    public void filters(View view) {
        mMap.clear();
        Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
        startActivity(intent);
    }

}
