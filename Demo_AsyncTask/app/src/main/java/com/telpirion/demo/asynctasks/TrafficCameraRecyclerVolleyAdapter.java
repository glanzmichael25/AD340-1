package com.telpirion.demo.asynctasks;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class TrafficCameraRecyclerVolleyAdapter extends TrafficCameraRecyclerAdapter {
    public TrafficCameraRecyclerVolleyAdapter(TrafficCameraInfo[] cameras) {
        super(cameras);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.layout;
        TextView description = (TextView)cardView.findViewById(R.id.card_description);
        final ImageView imageView = (ImageView)cardView.findViewById(R.id.card_image);

        TrafficCameraInfo cameraInfo = cameras[position];
        description.setText(cameraInfo.getDescription());

        RequestQueue queue = Volley.newRequestQueue(cardView.getContext());
        ImageRequest imageRequest = new ImageRequest(cameraInfo.getImageUrl(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 1000, 1000, ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", "Recycler adapter error");
                    }
                });
        queue.add(imageRequest);

    }
}
