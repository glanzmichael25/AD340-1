package com.telpirion.demo.asynctasks.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.squareup.picasso.Picasso;
import com.telpirion.demo.asynctasks.R;
import com.telpirion.demo.asynctasks.TrafficCameraInfo;

public class MapHolderFragment extends Fragment implements OnMapReadyCallback {

    private final static String TAG = "MapHolderFragment";

    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap mMap;
    private TrafficCameraInfo mCameraInfo;
    private Marker mMarker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_holder,
                container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();

        String cameraName = args.getString(TrafficCameraFragment.TRAFFIC_CAMERA_NAME);
        String cameraUrl = args.getString(TrafficCameraFragment.TRAFFIC_CAMERA_IMAGE);
        double cameraLat = args.getDouble(TrafficCameraFragment.TRAFFIC_CAMERA_LATITUDE);
        double cameraLong = args.getDouble(TrafficCameraFragment.TRAFFIC_CAMERA_LONGITUDE);

        this.mCameraInfo = new TrafficCameraInfo.Builder()
                .description(cameraName)
                .fullImageUrl(cameraUrl)
                .latLng(cameraLat, cameraLong)
                .build();

        Log.i(TAG, this.mCameraInfo.toString());

        SupportMapFragment mapFragment = (SupportMapFragment)(getChildFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(getActivity());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Create a custom info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Display the camera's location on the map.
        LatLng trafficCamLocation = new LatLng(mCameraInfo.getLatitude(),
                mCameraInfo.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(trafficCamLocation)
                .title(mCameraInfo.getDescription()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trafficCamLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {

            View infoWindow = getLayoutInflater()
                    .inflate(R.layout.custom_map_info, null);

            render(marker, infoWindow);

            return infoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View infoWindow = getLayoutInflater()
                    .inflate(R.layout.custom_map_info, null);

            render(marker, infoWindow);

            return infoWindow;
        }

        private void render(Marker marker, View view) {
            TextView description = (TextView)view
                    .findViewById(R.id.description);
            description.setText(mCameraInfo.getDescription());

            ImageView imageView = (ImageView)view
                    .findViewById(R.id.map_image);

            Picasso.with(view.getContext())
                    .load(mCameraInfo.getImageUrl())
                    .placeholder(R.drawable.kitten_small)
                    .into(imageView);
        }
    }
}
