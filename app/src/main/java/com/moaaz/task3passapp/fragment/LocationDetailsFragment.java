package com.moaaz.task3passapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moaaz.task3passapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailsFragment extends Fragment {


    public LocationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_location_details, container, false);





        return view;
    }

}
