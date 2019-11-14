package com.telpirion.demo.tasks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class LOCAsyncTaskLoader extends AsyncTaskLoader<String> {

    private String queryString;

    public LOCAsyncTaskLoader(Context context, String queryString) {
        super(context);

        this.queryString = queryString;
    }


    @Nullable
    @Override
    public String loadInBackground() {

        String baseURL = "http://loc.gov/pictures/search/";

        return NetworkConnection.getData(baseURL,
                "q", queryString, "fo", "json");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
