package com.moaaz.task3passapp.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.adapter.LocationsAdapter;
import com.moaaz.task3passapp.fragment.presenter.LocationsListPresenter;
import com.moaaz.task3passapp.fragment.view.LocationsListView;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsListFragment extends Fragment implements LocationsListView {

    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private TextView add;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 1;
    private LocationsListPresenter presenter;

    public LocationsListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LocationsListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        addButton = view.findViewById(R.id.add_button);
        add = view.findViewById(R.id.click_add);
        setAdapterData();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationPermissionAllowed()) {

                    Navigation.findNavController(v).navigate(R.id.action_locationsListFragment_to_mapFragment);


                } else {


                    requestLocationPermission();
                }
            }

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    private void setAdapterData() {
        adapter = new LocationsAdapter(presenter.getLocationsFromDataBase(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideEmptyView() {
        if (adapter != null) {
            add.setVisibility(View.GONE);
        }
    }

    private Boolean isLocationPermissionAllowed() {

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.e("granted" , "permission granted");
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.mapFragment);

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
                }

            }


        }


    }


}



