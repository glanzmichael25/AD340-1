package com.example.viewsandviewgroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Demo_ViewsAndViewGroups
 *
 * Activity that demonstrates how to create a ListView
 * @version 2019-04-14
 */
public class ListViewActivity extends AppCompatActivity {

    final static String TAG = "ListView sez";

    private LegoKit[] kits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        // We can create our adapter from a resource array, like we did
        // with the Spinner control. This time, however, we're going to create
        // it a bit more dynamically.

        // First, we're going to create our datasource.
        this.kits = LegoKit.initKits();
        ArrayAdapter<LegoKit> adapter =
                new LegoKitAdapter(this, this.kits);

        // Then we connect it to our ListView.
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // Finally we add a event handler to the ListView.
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                LegoKit selectedKit = kits[position];
                Log.i(TAG, "You selected: " + selectedKit.getTitle());
            }
        });

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.listview_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
