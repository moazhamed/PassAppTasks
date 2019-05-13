package com.moaaz.task3passapp.database.dao;



import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;


@Dao
public interface LocationDao {

    @Insert
    long insertLocation(LocationItem locationItem);

    @Delete
    int deleteLocation(LocationItem locationItem);

    @Update
    int updateLocation(LocationItem locationItem);


    @Query("select * from LocationItem;")
    List<LocationItem> getAllLocations();

    @Insert
    void insertListOfLocations(List<LocationItem> locationItemList);

}
