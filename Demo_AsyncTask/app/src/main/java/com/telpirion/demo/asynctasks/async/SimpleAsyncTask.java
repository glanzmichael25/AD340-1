package com.telpirion.demo.asynctasks.async;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Void, String> {

    WeakReference<TextView> mTextView;

    public SimpleAsyncTask(TextView view) {
        mTextView = new WeakReference<>(view);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        try {
            Thread.sleep(s);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + "ms!";
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
