package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Hospital_Signup_Set_Loctaion_Activity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    Button hs_lg_loc_btn_next;
    ImageView hs_sp_loc_back;
    private GoogleMap mMap;
    Context context;
    LocationManager locationManager;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 1000000000;
    double latitude = 0, longitude = 0;
    String login_id, email_id, hname1, hownername1, hcategory1, htype1, htime1, hmo1, hbuilding_no2, hstreet2, hlandmark2, harea2,
            hpincode2, hcity2, hstate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup__set__loctaion_);

        intview();
        setListner();
    }


    private void intview() {
        context = Hospital_Signup_Set_Loctaion_Activity.this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        hs_lg_loc_btn_next = (Button) findViewById(R.id.hs_lg_loc_btn_next);
        hs_sp_loc_back = (ImageView) findViewById(R.id.hs_sp_loc_back);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname1 = getIntent().getStringExtra("name");
        hownername1 = getIntent().getStringExtra("owner_name");
        hcategory1 = getIntent().getStringExtra("category");
        htype1 = getIntent().getStringExtra("type");
        htime1 = getIntent().getStringExtra("time");
        hmo1 = getIntent().getStringExtra("mo");
        hbuilding_no2 = getIntent().getStringExtra("building_no");
        hstreet2 = getIntent().getStringExtra("street");
        hlandmark2 = getIntent().getStringExtra("landmark");
        harea2 = getIntent().getStringExtra("area");
        hpincode2 = getIntent().getStringExtra("pincode");
        hcity2 = getIntent().getStringExtra("city");
        hstate2 = getIntent().getStringExtra("state");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
        }
    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            LatLng cloc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(cloc).title("Current Location").draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(cloc));
        }

        // Add a marker in Sydney and move the camera


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(context, "Lng : " + marker.getPosition().longitude + "\nLat : " + marker.getPosition().latitude, Toast.LENGTH_SHORT).show();
                Log.d("-----lng-----", "" + marker.getPosition().longitude);
                Log.d("-----lat-----", "" + marker.getPosition().latitude);
                latitude = marker.getPosition().latitude;
                longitude = marker.getPosition().longitude;

            }
        });


    }

    private void setListner() {
        hs_lg_loc_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (latitude == 0 || longitude == 0) {
                    AppConstants.openErrorDialog(context, "please select location");
                } else {
                    String latitude1 = latitude + "";
                    String longitude1 = longitude + "";
                   /* Intent intent = new Intent(context, Hospital_Signup3_Activity.class);
                    intent.putExtra("login_id", login_id);
                    intent.putExtra("email_id", email_id);
                    intent.putExtra("name", hname1);
                    intent.putExtra("owner_name", hownername1);
                    intent.putExtra("category", hcategory1);
                    intent.putExtra("type", htype1);
                    intent.putExtra("time", htime1);
                    intent.putExtra("mo", hmo1);
                    intent.putExtra("building_no", hbuilding_no2);
                    intent.putExtra("street", hstreet2);
                    intent.putExtra("landmark", hlandmark2);
                    intent.putExtra("area", harea2);
                    intent.putExtra("pincode", hpincode2);
                    intent.putExtra("city", hcity2);
                    intent.putExtra("state", hstate2);*/
                    Intent returnintent = new Intent();
                    returnintent.putExtra("latitude", latitude1);

                    returnintent.putExtra("longitude", longitude1);
                    Log.e("latitude", latitude1);
                    Log.e("longitude", longitude1);
                    setResult(1, returnintent);
                    finish();
                }
            }
        });
        hs_sp_loc_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if (currentLocationmMarker != null) {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ", "" + latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }
}

