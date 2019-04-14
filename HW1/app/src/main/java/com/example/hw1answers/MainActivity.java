/**
 * MainActivity for the HW1 Answers app.
 *
 * Includes extra credit answers.
 *
 * @version 2019-04-07
 */
package com.example.hw1answers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Not using the res/ layout; we can comment this line out...
        //setContentView(R.layout.activity_main);

        // Note: You didn't need to delete all of the res/ files to have a
        // "clean" project to get the extra credit.

        TextView label1 = new TextView(this);
        label1.setText("This is a demonstration of the answers for HW1. " +
                "Your exact code may differ; that is okay.");

        TextView label2 = new TextView(this);
        label2.setText("\n\nI'm currently interested in machine learning " +
                "applications, particularly in the field of natural language");

        // You didn't need to use a layout to get the extra credit, but
        // this does make the output look cleaner.
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(label1);
        layout.addView(label2);

        setContentView(layout);

    }
}
