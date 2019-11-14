package com.telpirion.demo.asynctasks.async;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.telpirion.demo.asynctasks.NetworkUtils;
import com.telpirion.demo.asynctasks.TrafficCameraInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrafficCameraLoader
        extends AsyncTaskLoader<List<TrafficCameraInfo>> {

    private static final String TAG = "TRAFFIC_LOADER";

    public TrafficCameraLoader(Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<TrafficCameraInfo> loadInBackground() {
        String data = NetworkUtils.getTrafficCameraInfo();
        return parseTrafficJSONData(data);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    private List<TrafficCameraInfo> parseTrafficJSONData(String json) {
        ArrayList<TrafficCameraInfo> list = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray itemsArray = jsonObject.getJSONArray("Features");

            int i = 0;
            while (i < itemsArray.length()) {

                JSONObject point = itemsArray.getJSONObject(i);
                JSONArray cameras = point.getJSONArray("Cameras");

                JSONArray points = point.getJSONArray("PointCoordinate");
                double latitude = points.getDouble(0);
                double longitude = points.getDouble(1);

                int j = 0;
                while (j < cameras.length()) {
                    try {
                        JSONObject camera = cameras.getJSONObject(j);

                        TrafficCameraInfo info = new TrafficCameraInfo.Builder()
                                .id(camera.getString("Id"))
                                .description(camera.getString("Description"))
                                .imageUrl(camera.getString("ImageUrl"))
                                .type(camera.getString("Type"))
                                .latLng(latitude, longitude)
                                .build();

                        list.add(info);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    j++;
                }

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
