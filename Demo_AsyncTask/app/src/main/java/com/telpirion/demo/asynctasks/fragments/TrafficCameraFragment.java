package com.telpirion.demo.asynctasks.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.telpirion.demo.asynctasks.R;
import com.telpirion.demo.asynctasks.TrafficCameraInfo;
import com.telpirion.demo.asynctasks.TrafficCameraRecyclerAdapter;
import com.telpirion.demo.asynctasks.async.TrafficCameraLoader;

import java.util.List;

public class TrafficCameraFragment extends Fragment
    implements LoaderManager.LoaderCallbacks<List<TrafficCameraInfo>>,
        TrafficCameraRecyclerAdapter.TrafficCameraListClickListener{

    public final static String TRAFFIC_CAMERA_LATITUDE = "latitude";
    public final static String TRAFFIC_CAMERA_LONGITUDE = "longitude";
    public final static String TRAFFIC_CAMERA_NAME = "address_name";
    public final static String TRAFFIC_CAMERA_IMAGE = "image_url";

    private final static String TAG = "TRAFFIC_CAMERA_FRAGMENT";
    private TextView lblOutput;
    private RecyclerView recyclerView;
    private TrafficCameraRecyclerAdapter adapter;
    private TrafficCameraFragmentClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_traffic_cam, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lblOutput = (TextView)findViewById(R.id.lbl_traffic_output);

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            lblOutput.setText(R.string.working);
            getActivity().getSupportLoaderManager().restartLoader(0, null, this);
        } else {
            lblOutput.setText(R.string.error_no_connectivity);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TrafficCameraFragmentClickListener) {
            this.mListener = (TrafficCameraFragmentClickListener)context;
        }
    }

    private View findViewById(int id) {
        return this.getView().findViewById(id);
    }

    @NonNull
    @Override
    public Loader<List<TrafficCameraInfo>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new TrafficCameraLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<TrafficCameraInfo>> loader,
                               List<TrafficCameraInfo> trafficCameraInfos) {
        Log.d(TAG, Integer.toString(trafficCameraInfos.size()));

        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        TrafficCameraInfo[] cameras = new TrafficCameraInfo[trafficCameraInfos.size()];
        cameras= trafficCameraInfos.toArray(cameras);

        TrafficCameraRecyclerAdapter adapter = new TrafficCameraRecyclerAdapter(cameras);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<TrafficCameraInfo>> loader) {

    }

    @Override
    public void onTrafficCameraListClick(TrafficCameraInfo info) {
        Log.i(TAG, info.toString());
        Bundle bundle = new Bundle();
        bundle.putDouble(TRAFFIC_CAMERA_LATITUDE, info.getLatitude());
        bundle.putDouble(TRAFFIC_CAMERA_LONGITUDE, info.getLongitude());
        bundle.putString(TRAFFIC_CAMERA_NAME, info.getDescription());
        bundle.putString(TRAFFIC_CAMERA_IMAGE, info.getImageUrl());
        mListener.onTrafficCameraFragmentClick(bundle);
    }

    public interface TrafficCameraFragmentClickListener {
        void onTrafficCameraFragmentClick(Bundle bundle);
    }
}
