package com.moaaz.task3passapp.fragment;


import android.location.Location;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.base.BaseFragment;
import com.moaaz.task3passapp.utlis.MyLocationProvider;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    MapView locationMap;
    GoogleMap mGoogleMap;
    Button save;
    Location currentLocation;
    Marker currentLocationMarker;
    MyLocationProvider locationProvider;



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

        save = view.findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LocationDetailsFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
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

        locationProvider= new MyLocationProvider(activity , null);
        currentLocation = locationProvider.getCurrentLocation();
        MapsInitializer.initialize(getContext());
        this.mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You're here");
        currentLocationMarker = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 12.0f));


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
}
