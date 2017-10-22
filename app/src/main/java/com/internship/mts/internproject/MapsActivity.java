package com.internship.mts.internproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.IndiBuServiceApi;
import com.internship.mts.internproject.network.model.Discount;
import com.internship.mts.internproject.network.model.ErrorResponseModel;
import com.internship.mts.internproject.utils.DateFormatUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ProgressDialog progressDialog;

    private static LatLng discountLocation;

    private Marker locationMarker;

    private double locationLat;
    private double locationLong;

    private GoogleMap googleMap;

    public static Intent newIntent(Context context) {
        return new Intent(context, MapsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setLocation();
    }


    private void setLocation() {
        locationLat = getIntent().getDoubleExtra("locationLat", 0);
        locationLong = getIntent().getDoubleExtra("locationLong", 0);
    }

    /**
     * Called when the map is ready.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        discountLocation = new LatLng(locationLat, locationLong);
        moveToCurrentLocation(discountLocation);
        // Add some markers to the map, and add a data object to each marker.
        locationMarker = googleMap.addMarker(new MarkerOptions()
                .position(discountLocation));

    }

    private void moveToCurrentLocation(LatLng currentLocation) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

    }
}