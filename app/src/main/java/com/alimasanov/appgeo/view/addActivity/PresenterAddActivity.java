package com.alimasanov.appgeo.view.addActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alimasanov.appgeo.db.Entity;
import com.alimasanov.appgeo.db.MyApp;
import com.alimasanov.appgeo.db.MyDatabase;

class PresenterAddActivity {

    private LocationManager locationManager;
    private Context context;
    private MyDatabase db;
    private Location loc;

    private Entity entity;
    private double d1;
    private double d2;

    private AddActivity addActivity;

    PresenterAddActivity(AddActivity addActivity,
                         LocationManager locationManager,
                         Context context) {
        this.addActivity = addActivity;
        this.locationManager = locationManager;
        this.context = context;
        db = MyApp.getInstance().getDb();
    }

    void onResume() {
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(context, locationPermission) != PackageManager.PERMISSION_GRANTED) {
            int MY_PERMISSION_CONST = 1;
            ActivityCompat.requestPermissions(addActivity, new String[] {locationPermission}, MY_PERMISSION_CONST);
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener);

            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0,
                    0,
                    locationListener);
        }
    }

    //запись данных в бд
    void setData(String fio, String city, String birthday, String location) {
        entity = new Entity(fio, city, birthday, location, d1, d2);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                db.getDao().insert(entity);
            }
        };
        Thread t = new Thread(run);
        t.start();
    }

    @SuppressLint("DefaultLocale")
    private void setCoordinates(Location location) {
        d1 = location.getLatitude();
        d2 = location.getLongitude();
    }

    double getLongitude() {
        return loc.getLongitude();
    }

    double getLatitude() {
        return loc.getLatitude();
    }

    @SuppressLint("DefaultLocale")
    private String formatLocation(Location location) {
        if (location == null) {
            return "";
        }
        return String.format(
                "Coordinates: lat = %.4f%n, lon = %2$.4f",
                location.getLatitude(), location.getLongitude());
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            addActivity.showLocation(formatLocation(location));
            setCoordinates(location);
            loc = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
