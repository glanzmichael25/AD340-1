package com.telpirion.demo.quiz2answerkey;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;


public class GossipTaskLoader extends AsyncTaskLoader<String> {

    private String mRumor;

    /**
     +1 points for correct constructor including super() call.

     Deductions:
     -0.5: Constructor needs to call to super()
     */
    public GossipTaskLoader(@NonNull Context context, String rumor) {
        super(context);

        /*
        +1 points for storing string passed in as argument.

        Deductions:
        -1: Need to store string passed in as argument in field.
         */
        this.mRumor = rumor;
    }

    /*
     +1 points for including onStartLoading()

    Deductions:
    -1: Must override onStartLoading()

     */
    @Override
    protected void onStartLoading() {
        /*
        +1 points for including call to forceLoad()

        Deductions:
        -1: Must call forceLoad() inside of onStartLoading()
         */
        forceLoad();
    }

    /*
    +1 points for including loadInBackground() with call to getLatest()

    Deductions:
    -1: Must override loadInBackground with call to GossipNetwork.getLatest()
    -0.5: Must be able to provide rumor string to GossipNetwork.getLatest()
     */
    @Nullable
    @Override
    public String loadInBackground() {
        return GossipNetwork.getLatest(mRumor);
    }

    /*
    Misc. deductions:
    -1.5: Need to implement loadInBackground(), onStartLoading; comments indicate correct design.
     */
}
