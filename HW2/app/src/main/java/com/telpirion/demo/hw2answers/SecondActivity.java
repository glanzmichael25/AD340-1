package com.telpirion.demo.hw2answers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity logs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_ID);

        TextView textView = (TextView)findViewById(R.id.lbl_result);
        textView.setText(message);

        Log.i(TAG, "onCreate");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }
}
