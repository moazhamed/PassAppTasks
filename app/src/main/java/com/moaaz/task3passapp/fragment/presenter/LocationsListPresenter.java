package com.moaaz.task3passapp.fragment.presenter;

import android.content.Context;

import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.fragment.view.LocationsListView;
import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;

public class LocationsListPresenter {

    private LocationsListView mLocationsListView;

    public void attachView(LocationsListView view) {
        this.mLocationsListView = view;
    }

    public void deAttachView() {
        mLocationsListView = null;
    }

    public List<LocationItem> getLocationsFromDataBase(Context context) {
        List<LocationItem> list = MyDataBase
                .getInstance(context)
                .LocationDao()
                .getAllLocations();
        if (list.size() != 0) {
            if (mLocationsListView != null) {
                mLocationsListView.hideEmptyView();
            }
        }

        return list;

    }

}
