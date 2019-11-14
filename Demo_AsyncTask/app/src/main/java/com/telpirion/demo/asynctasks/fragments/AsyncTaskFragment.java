package com.telpirion.demo.asynctasks.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.telpirion.demo.asynctasks.R;
import com.telpirion.demo.asynctasks.async.SimpleAsyncTask;

public class AsyncTaskFragment extends Fragment
        implements View.OnClickListener{

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_async_task, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button nextBtn = (Button)findViewById(R.id.button);
        nextBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        mTextView = (TextView)findViewById(R.id.lbl_fragment_task);
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView).execute();
    }

    private View findViewById(int id) {
        return this.getView().findViewById(id);
    }
}
