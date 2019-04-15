package com.example.viewsandviewgroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Demo_ViewsAndViewGroups
 *
 * Activity that demonstrates relative layouts
 * @version 2019-04-14
 */
public class RelativeLayoutActivity extends AppCompatActivity {

    final static String TAG = "RelativeActivity sez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_relative_layout);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.rel_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "RelativeLayout fab clicked");
                Intent intent = new Intent(getApplicationContext(),
                        ConstraintLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
