package com.moaaz.task3passapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.model.LocationDetails;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> {
    List<LocationDetails> locationDetailsList;

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_location_details, viewGroup, false);
        return new LocationViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder viewHolder, int i) {
        LocationDetails item = locationDetailsList.get(i);
        viewHolder.locationDescription.setText(item.getLocationDescrition());
        // viewHolder.locationImage.setImageResource(item.getLocationImageUrl());
    }


    @Override
    public int getItemCount() {
        return locationDetailsList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        ImageView locationImage;
        TextView locationDescription;

        public LocationViewHolder(View view) {
            super(view);
            locationImage = view.findViewById(R.id.location_image);
            locationDescription = view.findViewById(R.id.location_description);

        }

    }


}
