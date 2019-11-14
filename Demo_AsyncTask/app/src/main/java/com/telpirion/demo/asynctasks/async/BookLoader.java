package com.telpirion.demo.asynctasks.async;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.telpirion.demo.asynctasks.NetworkUtils;

public class BookLoader extends AsyncTaskLoader<String> {

    String queryString;

    public BookLoader(Context context, String queryString) {
        super(context);

        this.queryString = queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        String data = NetworkUtils.getBookInfo(queryString);

        return data;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
