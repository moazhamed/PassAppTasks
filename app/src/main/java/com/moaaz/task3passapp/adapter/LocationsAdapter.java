package com.moaaz.task3passapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.model.LocationItem;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> {
    List<LocationItem> locationDetailsList;

    public LocationsAdapter(List<LocationItem> locationDetailsList) {
        this.locationDetailsList = locationDetailsList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_location, viewGroup, false);
        return new LocationViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder viewHolder, int i) {
        LocationItem item = locationDetailsList.get(i);
        viewHolder.locationName.setText(item.getName());
        viewHolder.locationDescription.setText(item.getLocationDescription());
        viewHolder.locationLong.setText(String.valueOf(item.getLongitude()));
        viewHolder.locationLat.setText(String.valueOf(item.getLatitude()));
      //  Picasso.get().load(item.getPhotoUri()).fit().into(viewHolder.locationImage);
        Glide.with(viewHolder.itemView).load(item.getPhotoURI()).into(viewHolder.locationImage);
        // viewHolder.locationImage.setImageResource(item.getLocationImageUrl());
    }


    @Override
    public int getItemCount() {
        return locationDetailsList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        TextView locationDescription;
        TextView locationLong;
        TextView locationLat;
        ImageView locationImage;

        public LocationViewHolder(View view) {
            super(view);
            locationName = view.findViewById(R.id.location_name);
            locationDescription = view.findViewById(R.id.locaion_description);
            locationLong = view.findViewById(R.id.location_long);
            locationLat = view.findViewById(R.id.location_lat);
            locationImage = view.findViewById(R.id.location_image);

        }

    }


}
