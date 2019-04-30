package com.moaaz.task3passapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // viewHolder.locationImage.setImageResource(item.getLocationImageUrl());
    }


    @Override
    public int getItemCount() {
        return locationDetailsList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        TextView locationDescription;

        public LocationViewHolder(View view) {
            super(view);
            locationName = view.findViewById(R.id.location_name);
            locationDescription = view.findViewById(R.id.location_description);

        }

    }


}
