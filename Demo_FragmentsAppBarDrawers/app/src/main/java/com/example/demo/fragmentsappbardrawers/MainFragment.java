package com.example.demo.fragmentsappbardrawers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;

public class MainFragment extends Fragment
    implements View.OnClickListener {

    OnNextClickListener listener;

    interface OnNextClickListener {
        void OnMainFragmentNextClick(MainFragment fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnNextClickListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnMainFragmentNextClick");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button nextBtn =
                (Button)this.getView().findViewById(R.id.fragment_main_next);
        nextBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (listener != null) {
            listener.OnMainFragmentNextClick(this);
        }
    }

}
