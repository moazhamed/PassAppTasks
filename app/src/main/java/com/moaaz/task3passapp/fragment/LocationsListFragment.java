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
import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsListFragment extends Fragment {

    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private TextView add;
    private Fragment fragment = new MapFragment();
    List<LocationItem> list;
    // NavController navControler;


    public static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 1;

    public LocationsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //////////////// Inflate the layout for this fragment/////////////////////////
        View view = inflater.inflate(R.layout.fragment_locations_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        addButton = view.findViewById(R.id.add_button);
        add = view.findViewById(R.id.click_add);
        getLocationsFromDataBase();
        setAdapterData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //replace the code in onCreateView here
        super.onViewCreated(view, savedInstanceState);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationPermissionAllowed()) {
//
//                    NavHostFragment.findNavController(getParentFragment())
//                            .navigate(R.id.mapFragment);

                } else {
                    //request location permission
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setMessage("you've to enable location permission");
//                    builder.setCancelable(true);
//                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//
                    requestLocationPermission();
                }

            }

        });


    }

    private void setAdapterData() {
        adapter = new LocationsAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void getLocationsFromDataBase() {
        list = MyDataBase
                .getInstance(getContext())
                .LocationDao()
                .getAllLocations();
        if (list.size()!=0){
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
                    Log.e("denied" , "permission denied");
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }


    }


}
