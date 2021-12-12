package com.example.uw_badgermaps;

import static java.sql.DriverManager.println;

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

public class ShowLibraries extends FragmentActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LatLng mLocationLatLng;
    private GoogleMap mMap;
    private LatLng latLng;
    //set to campus area for map zoom
    private final LatLng mDestinationLatLng = new LatLng(43.0720, -89.4076);
    SearchView searchView;
    private Dialog dialog;

    private final LatLng AMP = new LatLng(43.07385791628417, -89.4054720306079);
    private final LatLng ARL = new LatLng(43.03963075279178, -89.44357490398728);
    private final LatLng AL = new LatLng(43.073873364830476, -89.39935283282031);
    private final LatLng BL = new LatLng(43.073022753638014, -89.40186300398541);
    private final LatLng CCBC = new LatLng(43.07132842899472, -89.40358607515053);
    private final LatLng CLSL = new LatLng(43.07553498991857, -89.40522463282022);
    private final LatLng CL = new LatLng(43.07676277915192, -89.40135283282012);
    private final LatLng DISC = new LatLng(43.07650057915193, -89.40530833282017);
    private final LatLng EL = new LatLng(43.077342389225, -89.43014543282011);
    private final LatLng GSCCL = new LatLng(43.076279389632944, -89.39845430398519);
    private final LatLng GWSLO = new LatLng(43.07460805323248, -89.3993528328203);
    private final LatLng GGL = new LatLng(43.07038217915225, -89.40628527515055);
    private final LatLng HSL = new LatLng(43.075402589969364, -89.40007283282024);
    private final LatLng ICL = new LatLng(43.05763621836267, -89.47701885981147);
    private final LatLng JRR = new LatLng(43.07270731643495, -89.39988146165534);
    private final LatLng LawL = new LatLng(43.07458525323837, -89.40224383282029);
    private final LatLng LL = new LatLng(43.077233989266674, -89.40278797515016);
    private final LatLng MKIGAS = new LatLng(43.0745933902799, -89.39919857515032);
    private final LatLng MemL = new LatLng(43.07536040437969, -89.39785079592568);
    private final LatLng ML = new LatLng(43.07128924168909, -89.4035968039855);
    private final LatLng RML = new LatLng(43.07609118508951, -89.40097129233483);
    private final LatLng SL = new LatLng(43.070729441617495, -89.40691956165546);
    private final LatLng SWL = new LatLng(43.07439001621963, -89.40818747515036);
    private final LatLng SteenL = new LatLng(43.076113015999226, -89.41339386165515);
    private final LatLng WWL = new LatLng(43.078796542648774, -89.41867480398508);
    private final LatLng ZMRL = new LatLng(43.07154085401708, -89.40452413282044);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            latLng = new LatLng(0, 0);
            //zoom into campus
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDestinationLatLng,17));
            displayMyLocation();
            addLibraryLocations();
            searchView = findViewById(R.id.svLocation);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;

                    if (location != null || !location.equals("")){
                        Geocoder geocoder = new Geocoder(ShowLibraries.this);
                        try{
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
    }

    private void addLibraryLocations() {
        mMap.addMarker(new MarkerOptions().position(AMP).title("AMP Library"));
        mMap.addMarker(new MarkerOptions().position(ARL).title("Arboretum Research Library"));
        mMap.addMarker(new MarkerOptions().position(AL).title("Art Library"));
        mMap.addMarker(new MarkerOptions().position(BL).title("Business Library"));
        mMap.addMarker(new MarkerOptions().position(CLSL).title("Chican@ and Latin@ Studies Library"));
        mMap.addMarker(new MarkerOptions().position(CL).title("College Library"));
        mMap.addMarker(new MarkerOptions().position(DISC).title("Data and Information Services Center"));
        mMap.addMarker(new MarkerOptions().position(EL).title("Ebling Library"));
        mMap.addMarker(new MarkerOptions().position(GSCCL).title("Gender and Sexuality Campus Center Library"));
        mMap.addMarker(new MarkerOptions().position(GGL).title("Geology and Geophysics Library"));
        mMap.addMarker(new MarkerOptions().position(HSL).title("Historical Society Library"));
        mMap.addMarker(new MarkerOptions().position(ICL).title("Innovation Center Library"));
        mMap.addMarker(new MarkerOptions().position(JRR).title("Journalism Reading Room"));
        mMap.addMarker(new MarkerOptions().position(LawL).title("Law Library"));
        mMap.addMarker(new MarkerOptions().position(LL).title("Limnology Library"));
        mMap.addMarker(new MarkerOptions().position(MKIGAS).title("Max Kade Institute for German-American Studies"));
        mMap.addMarker(new MarkerOptions().position(MemL).title("Memorial Library"));
        mMap.addMarker(new MarkerOptions().position(ML).title("MERIT Library"));
        mMap.addMarker(new MarkerOptions().position(RML).title("Robinson Map Library"));
        mMap.addMarker(new MarkerOptions().position(SL).title("Schwerdtfeger Library"));
        mMap.addMarker(new MarkerOptions().position(SWL).title("Social Work Library"));
        mMap.addMarker(new MarkerOptions().position(SteenL).title("Steenbock Library"));
        mMap.addMarker(new MarkerOptions().position(WWL).title("Wisconsin Water Library"));
        mMap.addMarker(new MarkerOptions().position(ZMRL).title("Zoology Museum Research Library"));


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
                                Toast.makeText(ShowLibraries.this, "1st Floor", Toast.LENGTH_LONG).show();
                            } else if (room == 2) {
                                //show 2nd floor
                                Toast.makeText(ShowLibraries.this, "2nd Floor", Toast.LENGTH_LONG).show();
                            } else if (room == 3) {
                                //show 3rd floor
                            } else if (room == 4) {
                                //show 4th floor
                            } else {
                                //SHOULD NOT BE HERE
                                Toast.makeText(ShowLibraries.this, "SHOULD NOT BE HERE", Toast.LENGTH_LONG).show();
                            }
                        } else { //room number entered does not exist
                            Toast.makeText(ShowLibraries.this, "Room number entered does not exist", Toast.LENGTH_LONG).show();
                        }
                    } catch (NumberFormatException e) { //room number is null
                        Toast.makeText(ShowLibraries.this, "Please enter valid room number", Toast.LENGTH_LONG).show();
                    }

                }
            });
            dialog.show();
        }else { //location is not near building with uploaded maps
            Toast.makeText(ShowLibraries.this, "No building maps found for selection", Toast.LENGTH_LONG).show();
        }
    }



}
