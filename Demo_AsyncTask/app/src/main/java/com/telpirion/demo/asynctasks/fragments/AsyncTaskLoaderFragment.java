package com.telpirion.demo.asynctasks.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.telpirion.demo.asynctasks.R;
import com.telpirion.demo.asynctasks.async.BookLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskLoaderFragment extends Fragment
    implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private final static String QUERY_STRING = "queryString";

    private TextView lblResults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_async_loader, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = (Button)findViewById(R.id.btn_loader);
        button.setOnClickListener(this);

        lblResults = (TextView)findViewById(R.id.lbl_output);
    }

    @Override
    public void onClick(View view) {

        lblResults.setText(R.string.working);

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", "Snow Crash");
        LoaderManager manager = getActivity().getSupportLoaderManager();
        manager.restartLoader(0, queryBundle, this);
    }

    private View findViewById(int id) {
        return this.getView().findViewById(id);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new BookLoader(this.getContext(), queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // Initialize iterator and results fields.
            int i = 0;
            String title = null;
            String authors = null;

            // Look for results in the items array, exiting when both the
            // title and author are found or when all items have been checked.
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // If both are found, display the result.
            if (title != null && authors != null) {
                String results = String.format("%s\n%s",
                        title, authors);

                lblResults.setText(results);
            } else {
                // If none are found, update the UI to show failed results.
                lblResults.setText(R.string.no_results);
            }

        } catch (Exception e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            lblResults.setText(R.string.no_results);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
