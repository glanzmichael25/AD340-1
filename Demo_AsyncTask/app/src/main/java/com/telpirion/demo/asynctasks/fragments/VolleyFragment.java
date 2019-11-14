package com.telpirion.demo.asynctasks.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telpirion.demo.asynctasks.R;
import com.telpirion.demo.asynctasks.TrafficCameraInfo;
import com.telpirion.demo.asynctasks.TrafficCameraRecyclerVolleyAdapter;

import org.json.JSONObject;

public class VolleyFragment extends Fragment
        implements View.OnClickListener,
            Response.ErrorListener, Response.Listener<JSONObject> {

    private TextView result;
    private final static String TAG = "VOLLEY_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volley, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = this.getView().findViewById(R.id.btn_volley);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        // Request a string response from the provided URL.
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, this, this);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);*/

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,this, this);
        queue.add(jsonRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        result.setText(getContext().getString(R.string.error_message));
    }


    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, response.toString());

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_volley);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        TrafficCameraInfo[] cameras = TrafficCameraInfo.fromJSONResponse(response);
        TrafficCameraRecyclerVolleyAdapter adapter =
                new TrafficCameraRecyclerVolleyAdapter(cameras);
        recyclerView.setAdapter(adapter);
    }
}
