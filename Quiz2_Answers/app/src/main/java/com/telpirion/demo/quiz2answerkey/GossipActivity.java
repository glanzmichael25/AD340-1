package com.telpirion.demo.quiz2answerkey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GossipActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gossip);
    }

    public void onClick(View view) {
        Bundle queryBundle = new Bundle();
        LoaderManager manager = getSupportLoaderManager();
        manager.restartLoader(0, queryBundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new GossipTaskLoader(this, "some rumor");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        TextView textView = findViewById(R.id.lbl_hot_gos);
        textView.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
