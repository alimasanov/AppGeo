package com.alimasanov.appgeo.view.mapsActivity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.alimasanov.appgeo.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle values = getIntent().getExtras();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        assert values != null;
        lat = values.getDouble("lat");
        lon = values.getDouble("lon");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(lat, lon);
        LatLng latLng1 = new LatLng(45, 34.2);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Your position"));
        googleMap.addMarker(new MarkerOptions().position(latLng1).title("TestMarker"));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng1);
        final LatLngBounds bounds = builder.build();

        final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 70);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.animateCamera(cameraUpdate);
            }
        });
    }
}
