package com.telpirion.demo.asynctasks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrafficCameraInfo {

    private final static String TYPE_SDOT = "sdot";
    private final static String TYPE_WSDOT = "wsdot";
    private final static String URL_WSDOT = "http://images.wsdot.wa.gov/nw/";
    private final static String URL_SDOT = "http://www.seattle.gov/trafficcams/images/";

    private String id;
    private String description;
    private String imageUrl;
    private String type;
    private double latitude;
    private double longitude;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public TrafficCameraInfo(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public static TrafficCameraInfo[] fromJSONResponse(JSONObject jsonObject) {
        ArrayList<TrafficCameraInfo> list = new ArrayList<>();

        try {

            JSONArray itemsArray = jsonObject.getJSONArray("Features");

            int i = 0;
            while (i < itemsArray.length()) {

                JSONObject point = itemsArray.getJSONObject(i);
                JSONArray cameras = point.getJSONArray("Cameras");

                int j = 0;
                while (j < cameras.length()) {
                    try {
                        JSONObject camera = cameras.getJSONObject(j);

                        TrafficCameraInfo info = new TrafficCameraInfo.Builder()
                                .id(camera.getString("Id"))
                                .description(camera.getString("Description"))
                                .imageUrl(camera.getString("ImageUrl"))
                                .type(camera.getString("Type"))
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

        TrafficCameraInfo[] cameras = new TrafficCameraInfo[list.size()];
        return list.toArray(cameras);
    }

    private TrafficCameraInfo() {}

    @Override
    public String toString() {
        return "TrafficCameraInfo{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static class Builder {

        TrafficCameraInfo cameraInfo;
        String fullImageUrl;

        public Builder() {
            cameraInfo = new TrafficCameraInfo();
            this.fullImageUrl = "";
        }

        public Builder id(String id) {
            cameraInfo.id = id;
            return this;
        }

        public Builder description(String description) {
            cameraInfo.description = description;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            cameraInfo.imageUrl = imageUrl;
            return this;
        }

        public Builder fullImageUrl(String imageUrl) {
            this.fullImageUrl = imageUrl;
            return this;
        }

        public Builder type(String type) {
            cameraInfo.type = type;
            return this;
        }

        public Builder latLng(double latitude, double longitude) {
            cameraInfo.latitude = latitude;
            cameraInfo.longitude = longitude;
            return this;
        }

        public TrafficCameraInfo build() {
            if (this.fullImageUrl.isEmpty()) {
                if (cameraInfo.type.contains(TYPE_SDOT)) {
                    cameraInfo.imageUrl = URL_SDOT + cameraInfo.imageUrl;
                } else if (cameraInfo.type.contains(TYPE_WSDOT)) {
                    cameraInfo.imageUrl = URL_WSDOT + cameraInfo.imageUrl;
                }
            } else {
                cameraInfo.imageUrl = this.fullImageUrl;
            }
            return cameraInfo;
        }
    }
}
