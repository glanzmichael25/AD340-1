package com.telpirion.demo.asynctasks;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class TrafficCameraRecyclerAdapter
        extends RecyclerView.Adapter<TrafficCameraRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    protected TrafficCameraInfo[] cameras;

    public TrafficCameraRecyclerAdapter(TrafficCameraInfo[] cameras) {
        this.cameras = cameras;
    }

    @Override
    public TrafficCameraRecyclerAdapter.ViewHolder  onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.layout;
        TextView description = (TextView)cardView.findViewById(R.id.card_description);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.card_image);

        TrafficCameraInfo cameraInfo = cameras[position];
        description.setText(cameraInfo.getDescription());

        /*Glide.with(cardView.getContext())
                .load(cameraInfo.getImageUrl())
                .into(imageView);*/

        Picasso.with(cardView.getContext())
                .load(cameraInfo.getImageUrl())
                .placeholder(R.drawable.kitten_small)
                .fit()
                .noFade()
                .into(imageView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onTrafficCameraListClick(cameras[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cameras.length;
    }

    private TrafficCameraListClickListener mListener;

    public void setListener(TrafficCameraListClickListener listener) {
        this.mListener = listener;
    }

    public interface TrafficCameraListClickListener {
        void onTrafficCameraListClick(TrafficCameraInfo info);
    }

}
