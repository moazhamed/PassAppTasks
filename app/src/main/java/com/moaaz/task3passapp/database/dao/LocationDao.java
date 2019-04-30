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
    void InsertLocation(LocationItem locationItem);

    @Delete
    void DeleteLocation(LocationItem locationitem);

    @Update
    void UpdateLocation(LocationItem locationitem);


    @Query("select * from LocationItem;")
    List<LocationItem> getAllLocations();

    @Insert
    void InsertListOfLocations(List<LocationItem> locationItemList);

}
