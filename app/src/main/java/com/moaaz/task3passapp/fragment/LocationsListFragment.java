package com.moaaz.task3passapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.moaaz.task3passapp.utlis.Data;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.adapter.LocationsAdapter;
import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsListFragment extends Fragment {

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
        adapter = new LocationsAdapter(Data.getData());

        //////////////storing data in data base/////////////////
        List<LocationItem> list = Data.getData();
        MyDataBase.getInstance(getContext())
                .LocationDao()
                .InsertListOfLocations(list);

        /////////////////retrieving form database//////////
        List<LocationItem> test = MyDataBase.getInstance(getContext()).LocationDao().getAllLocations();
        Log.i("Data", test.get(1).getName());
        Log.i("Data", test.get(2).getName());
        Log.i("Data", test.get(3).getName());
        Log.i("Data", test.get(4).getName());
        Log.i("Data", test.get(5).getName());
        Log.i("Data", test.get(6).getName());
        Log.i("Data", test.get(7).getName());
        Log.i("Data", test.get(8).getName());


        /////////setting the data to adapter///////////////////////
        recyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });


        return view;
    }

    public void insertLocations(){


    }




}
