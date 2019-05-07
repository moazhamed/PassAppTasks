package com.moaaz.task3passapp.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.NonNull;

@Entity
public class LocationItem {
    @ColumnInfo
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private Double longitude;

    @ColumnInfo
    private Double latitude;

    @ColumnInfo
    private String photoURI;

    @ColumnInfo
    private String locationDescription;

    @ColumnInfo
    private String name;

    public LocationItem() {

    }

    @Ignore
    public LocationItem(Double longitude, Double latitude, String photoURI, String locationDescription, String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.photoURI = photoURI;
        this.locationDescription = locationDescription;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPhotoURI() {
        return photoURI;
    }

//    public Uri getPhotoUri() {
//        return Uri.parse(photoURI);
//    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public int getId() {
        return id;
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
