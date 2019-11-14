package com.telpirion.demo.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private TextView results;
    private final static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_get_data);
        button.setOnClickListener(this);

        results = findViewById(R.id.lbl_results);
    }

    @Override
    public void onClick(View view) {

        ConnectivityManager manager =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {

            results.setText(getResources().getString(R.string.waiting));

            Bundle bundle = new Bundle();
            bundle.putString("queryString", "gettysburg");
            getSupportLoaderManager().restartLoader(0,
                    bundle, this);
        } else {
            results.setText(getResources().getString(R.string.no_connection));
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new LOCAsyncTaskLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        String fullImageUrl = "";
        try {

            JSONObject rootObject = new JSONObject(s);
            JSONArray results = rootObject.getJSONArray("results");
            JSONObject firstResult = results.getJSONObject(0);
            JSONObject image = firstResult.getJSONObject("image");
            fullImageUrl = image.getString("full");

        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        results.setText(fullImageUrl);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }

}
