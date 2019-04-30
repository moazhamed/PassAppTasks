package com.moaaz.task3passapp.utlis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;

public class MyLocationProvider {
    LocationManager locationManager;
    Location location;
    boolean canGetlocation;
    Context context;
    LocationListener  locationListener;
    public final int MIN_DISTANCE_BETWEEN_UPDATES = 10;
    //10 meters between every update
    public final int MIN_TIME_BETWEEN_UPDATES = 5 * 1000;
    // 5 seconds between every update

    public MyLocationProvider(Context context, LocationListener locationListener) {
        this.context = context;
        this.locationListener = locationListener;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        location = null;
    }


    @SuppressLint("MissingPermission")
    public Location getCurrentLocation() {
        String provider = null;
        ////////////////////////////looking for a provider to get a location from it/////////////////
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        if (provider == null) {
            canGetlocation = false;
            return null;
        }
        canGetlocation = true;

        //we cannot call this method ella lama na5od el permission
        //so we added annotaion enaha bt miss el permission

        location = locationManager.getLastKnownLocation(provider);


        if (location == null) {
            location = getBestLastKnownLocation();
        }
        //To get location updates
        if (locationListener != null) {
            locationManager.requestLocationUpdates(provider,
                    MIN_TIME_BETWEEN_UPDATES,
                    MIN_DISTANCE_BETWEEN_UPDATES,
                    locationListener);
        }
        return location;
    }


    @SuppressLint("MissingPermission")
    public Location getBestLastKnownLocation() {
        Location bestLocation = null;
        List<String> providers =
                locationManager.getProviders(true);
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (bestLocation == null && l != null) {
                bestLocation = l;
                continue;
            } else if (l != null && bestLocation != null &&
                    bestLocation.getAccuracy() < l.getAccuracy()) {
                bestLocation = l;
            }

        }
        return bestLocation;
    }
}
