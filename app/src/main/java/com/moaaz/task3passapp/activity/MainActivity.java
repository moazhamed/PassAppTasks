package com.moaaz.task3passapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.fragment.LocationsListFragment;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);


        //  pushListLocationFragment();




    }


    void pushListLocationFragment() {
        Fragment fragment = new LocationsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }



}
