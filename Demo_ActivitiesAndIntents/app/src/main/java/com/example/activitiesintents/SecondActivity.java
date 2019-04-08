package com.example.activitiesintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Android Studio: Demo_ActivitiesAndIntents
 * SecondActivity to be launched from MainActivity
 * @version 2019-04-08
 */
public class SecondActivity extends Activity {

    private static final String TAG = "ELIJAH SAYS...";

    public static final String RESULT = "my.response";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        Intent intent = getIntent();
        String message = "";

        if (intent.getAction() == Intent.ACTION_VIEW) {
            Uri data = intent.getData();
            message = data.toString();
        } else {

            intent.getStringExtra(MainActivity.MESSAGE_ID);
        }
        setContentView(R.layout.activity_second);

        TextView label = (TextView)findViewById(R.id.intent_message);
        label.setText(message);

        Log.i(TAG, "started");

    }

    protected void onClick(View view) {
        EditText textBox = (EditText)findViewById(R.id.response);
        String message = textBox.getText().toString();

        Intent responseIntent = new Intent();
        responseIntent.putExtra(SecondActivity.RESULT, message);

        setResult(Activity.RESULT_OK, responseIntent);
        finish();
    }


}
