package com.moaaz.task3passapp.fragment.presenter;

import android.content.Context;

import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.fragment.view.LocationDetailsView;
import com.moaaz.task3passapp.model.LocationItem;

public class LocationDetailsPresenter {


    LocationDetailsView mLocationDetailsView;

    public void attachView(LocationDetailsView mLocationDetailsView) {
        this.mLocationDetailsView = mLocationDetailsView;
    }


    public void detachView() {
        this.mLocationDetailsView = null;
    }

    public void saveLocationDetailsToDataBase(Context context, Float longitude, Float latitude,
                                              String photo, String desc, String name) {
        long result = MyDataBase
                .getInstance(context)
                .LocationDao()
                .insertLocation(new LocationItem(longitude, latitude, photo, desc, name));
        if (result > 0) {
            mLocationDetailsView.OnInsertionCompleted();
        }
    }


}
