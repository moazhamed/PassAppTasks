package com.moaaz.task3passapp.model;

public class LocationDetails {

    String locationDescrition;
    String locationImageUrl ;


    public LocationDetails(String locationDescrition, String locationImageUrl) {
        this.locationDescrition = locationDescrition;
        this.locationImageUrl = locationImageUrl;
    }

    public String getLocationDescrition() {
        return locationDescrition;
    }

    public void setLocationDescrition(String locationDescrition) {
        this.locationDescrition = locationDescrition;
    }

    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }
}
