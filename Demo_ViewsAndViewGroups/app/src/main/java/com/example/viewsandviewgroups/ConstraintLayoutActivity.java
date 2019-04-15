package com.example.viewsandviewgroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Demo_ViewsAndViewGroups
 *
 * Activity that demonstrates constraint layouts
 * @version 2019-04-14
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    final static String TAG = "Constraint Activity sez";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint_layout);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.cons_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ConstraintLayout fab clicked");
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
