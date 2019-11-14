package com.telpirion.demo.tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class GitHubAsyncTask extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTextView;

    public GitHubAsyncTask(TextView textView) {
        this.mTextView = new WeakReference<>(textView);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkConnection.getData(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
