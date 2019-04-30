package com.moaaz.task3passapp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.moaaz.task3passapp.base.BaseFragment;
import com.moaaz.task3passapp.utlis.Data;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.adapter.LocationsAdapter;
import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsListFragment extends BaseFragment {

    ImageButton addButton;
    RecyclerView recyclerView;
    LocationsAdapter adapter;
    Fragment fragment = new MapFragment();

    public LocationsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //////////////// Inflate the layout for this fragment/////////////////////////
        View view = inflater.inflate(R.layout.fragment_locations_list, container, false);
        addButton = view.findViewById(R.id.add_button);
        recyclerView = view.findViewById(R.id.recycler_view);
        getLocationsFromDataBase();
        setAdapterData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //replace the code in onCreateView here
        super.onViewCreated(view, savedInstanceState);
    }

    public void setAdapterData() {
        adapter = new LocationsAdapter(Data.getData());
        recyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

    }

    public void getLocationsFromDataBase() {
  /*
        TODO
        retrieve the data from the database and then fill the adapter with this data

        */
    }


}
