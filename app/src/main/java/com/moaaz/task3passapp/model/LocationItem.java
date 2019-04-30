package com.moaaz.task3passapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LocationItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    String locationDescription;

    @ColumnInfo
    String name;

    public LocationItem() {

    }

    @Ignore
    public LocationItem(String locationDescription, String name) {
        this.locationDescription = locationDescription;
        this.name = name;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
