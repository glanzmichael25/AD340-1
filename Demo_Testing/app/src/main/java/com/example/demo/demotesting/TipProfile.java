package com.example.demo.demotesting;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TipProfile {

    public final static double SERVICE_GOOD = 1.0;
    public final static double SERVICE_MEH = 0.75;
    public final static double SERVICE_BAD = 0.50;

    private String name;
    private double baseRate;

    public TipProfile(String name, double baseRate) {
        this.name = name;

        if (baseRate > 1.0) {
            this.baseRate = 0.2;
        } else {
            this.baseRate = baseRate;
        }
    }

    public TipProfile(Context ctx) {
        this.name = ctx.getString(R.string.default_tip_profile);

        /*
        // This only works for Robolectric
        TypedValue defaultRate = new TypedValue();
        ctx.getResources().getValue(R.dimen.default_tip_amt, defaultRate, true);
        this.baseRate = defaultRate.getFloat();
        */

        /*
        // This only works with Mockito
        this.baseRate =
                (double)ctx.getResources().getDimension(R.dimen.default_tip_amt);
        */

        // This works with both
        String defaultTipAmt = ctx.getString(R.string.default_tip_amt_str);
        this.baseRate = Double.parseDouble(defaultTipAmt);
    }

    public String getName() {
        return this.name;
    }

    public double getTipAmount(double cost,
            double experience) {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        double rawResult = cost * (baseRate * experience);

        return Double.parseDouble(df.format(rawResult));

    }
}
