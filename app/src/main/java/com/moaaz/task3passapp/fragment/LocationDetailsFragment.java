package com.moaaz.task3passapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moaaz.task3passapp.R;
import com.moaaz.task3passapp.database.MyDataBase;
import com.moaaz.task3passapp.model.LocationItem;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.navigation.fragment.NavHostFragment;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailsFragment extends Fragment {
    ImageView locationImage;
    Button galleryImage, cameraImage, saveButton;
    TextView locationInfo;
    String desc, name;
    EditText locationName, locationDescription;
    Double longitude, latitude;
    String currentPhotoPath;
    Uri photoURI;
    String photo;


    private static final int MY_PERMISSIONS_REQUEST_CAMERA_IMAGE_CODE = 2;
    private static final int MY_PERMISSIONS_REQUEST_GALLERY_IMAGE_CODE = 3;
    private static final String LONGITUDE = "Longitude";
    private static final String LATITUDE = "Latitude";


    public LocationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_details, container, false);
        locationImage = view.findViewById(R.id.image);
        cameraImage = view.findViewById(R.id.camera);
        galleryImage = view.findViewById(R.id.gallery);
        saveButton = view.findViewById(R.id.button_save);
        locationInfo = view.findViewById(R.id.info);
        locationName = view.findViewById(R.id.name);
        locationDescription = view.findViewById(R.id.description);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        longitude = bundle.getDouble(LONGITUDE);
        latitude = bundle.getDouble(LATITUDE);

        locationInfo.setText(R.string.picked_location_longitude + longitude + " " +
                " " + R.string.picked_location_latitude + latitude);


        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        galleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myGallery = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(myGallery, MY_PERMISSIONS_REQUEST_GALLERY_IMAGE_CODE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationDescription.getText().length() == 0
                        || locationName.getText().length() == 0) {
                    locationDescription.setError("please enter location description !");
                    locationName.setError("please enter location name !");

                } else {
                    saveLocationDetailsToDataBase();
                    NavHostFragment.findNavController(getParentFragment()).
                            popBackStack(R.id.locationsListFragment, true);

                }
            }
        });

    }

    public void saveLocationDetailsToDataBase() {
        desc = locationDescription.getText().toString();
        name = locationName.getText().toString();
        MyDataBase
                .getInstance(getContext())
                .LocationDao()
                .insertLocation(new LocationItem(longitude, latitude, photo, desc, name));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA_IMAGE_CODE) {
                photoURI = Uri.fromFile(new File(currentPhotoPath));
                Glide.with(getContext()).load(photoURI).into(locationImage);
                //Picasso.get().load(photoURI).into(locationImage);
                photo = photoURI.toString();

            } else if (requestCode == MY_PERMISSIONS_REQUEST_GALLERY_IMAGE_CODE) {
                photoURI = data.getData();
                Glide.with(getContext()).load(photoURI).into(locationImage);
              // Picasso.get().load(photoURI).into(locationImage);
                photo = photoURI.toString();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //storageDir.mkdirs();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent myCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (myCameraIntent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("error", ex.getMessage(), ex);
                // Error occurred while creating the File
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.moaaz.task3passapp.fileprovider",
                        photoFile);
                myCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(myCameraIntent, MY_PERMISSIONS_REQUEST_CAMERA_IMAGE_CODE);
            }
        }
    }

}
