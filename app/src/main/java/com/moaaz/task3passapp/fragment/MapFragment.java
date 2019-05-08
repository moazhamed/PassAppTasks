package com.moaaz.task3passapp.fragment;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.utli.MyLocationProvider;

import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    MapView locationMap;
    GoogleMap mGoogleMap;
    Button next;
    public static Location currentLocation;
    Marker currentLocationMarker;
    MyLocationProvider locationProvider;
    Bundle bundle = new Bundle();


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        locationMap = view.findViewById(R.id.location_map);
        locationMap.onCreate(savedInstanceState);

        next = view.findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment())
                        .navigate(R.id.locationDetailsFragment, bundle);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationMap.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        locationProvider = new MyLocationProvider(getContext(), this);
        currentLocation = locationProvider.getCurrentLocation();

        MapsInitializer.initialize(getContext());
        this.mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng mLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions().position(mLatLng).title("You're here");
        currentLocationMarker = mGoogleMap.addMarker(markerOptions);

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 12.0f));
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (currentLocationMarker != null) {
                    currentLocationMarker.remove();
                }
                currentLocationMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
              //  Toast.makeText(getContext(), latLng.latitude + "  " + latLng.longitude, Toast.LENGTH_LONG).show();
                bundle.putDouble("Longitude", latLng.longitude);
                bundle.putDouble("Latitude", latLng.latitude);


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        locationMap.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        locationMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationMap.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        locationMap.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        locationMap.onLowMemory();
    }


    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;

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


}
