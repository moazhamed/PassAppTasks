package com.moaaz.task3passapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.MapView;
import com.moaaz.task3passapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    MapView locationMap;
    Button save;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_map, container, false);

         locationMap = view.findViewById(R.id.location_map);
         save = view.findViewById(R.id.save_button);
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment fragment = new LocationDetailsFragment();
                 getActivity().getSupportFragmentManager()
                         .beginTransaction()
                         .addToBackStack(null)
                         .replace(R.id.fragment_container, fragment)
                         .commit();
             }
         });



        return view;
    }

}
