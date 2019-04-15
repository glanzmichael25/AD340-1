package com.example.viewsandviewgroups;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Demo_ViewsAndViewGroups
 *
 * Activity that demonstrates RecyclerView
 * @version 2019-04-14
 */
public class RecyclerViewActivity extends AppCompatActivity {

    final static String TAG = "RecyclerView sez";

    private LegoKit[] kits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView =
                (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.kits = LegoKit.initKits();
        LegoKitRecyclerAdapter adapter = new LegoKitRecyclerAdapter(kits);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new LegoKitRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Log.i(TAG, "Clicked " + kits[position].getTitle());
            }
        });
    }
}
