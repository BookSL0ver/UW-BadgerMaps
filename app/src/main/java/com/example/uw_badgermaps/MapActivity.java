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
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

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

    public void arrived(View view) {
        if (((latLng.latitude - 43.07145285968377 < 0.00035) & (latLng.latitude - 43.07145285968377 > -0.00035)) & ((latLng.longitude - -89.40668315134191 < 0.00035) & (latLng.longitude - -89.40668315134191 > -0.00035))) {
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
            getFloor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get image of correct floor
                    //checks if location is near valid building with maps added
                        try { //check for bad input
                            EditText roomText = dialog.findViewById(R.id.roomNumber);
                            String roomStr = roomText.getText().toString();
                            //Toast.makeText(MapActivity.this, roomStr, Toast.LENGTH_LONG).show();
                            int room = Integer.parseInt(roomStr);
                            if ((room > 100) & (room < 4500)) {
                                room = room / 1000;
                                if (room == 0) {
                                    //show basement
                                } else if (room == 1) {
                                    //show 1st floor
                                    Toast.makeText(MapActivity.this, "1st Floor", Toast.LENGTH_LONG).show();
                                } else if (room == 2) {
                                    //show 2nd floor
                                    Toast.makeText(MapActivity.this, "2nd Floor", Toast.LENGTH_LONG).show();
                                } else if (room == 3) {
                                    //show 3rd floor
                                } else if (room == 4) {
                                    //show 4th floor
                                } else {
                                    //SHOULD NOT BE HERE
                                    Toast.makeText(MapActivity.this, "SHOULD NOT BE HERE", Toast.LENGTH_LONG).show();
                                }
                            } else { //room number entered does not exist
                                Toast.makeText(MapActivity.this, "Room number entered does not exist", Toast.LENGTH_LONG).show();
                            }
                        } catch (NumberFormatException e) { //room number is null
                            Toast.makeText(MapActivity.this, "Please enter valid room number", Toast.LENGTH_LONG).show();
                        }

                }
            });
            dialog.show();
        }else { //location is not near building with uploaded maps
            Toast.makeText(MapActivity.this, "No building maps found for selection", Toast.LENGTH_LONG).show();
        }
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