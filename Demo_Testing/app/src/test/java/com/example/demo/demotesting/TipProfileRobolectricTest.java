package com.example.demo.demotesting;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TipProfileRobolectricTest {

    @Test
    public void tipProfile_DefaultProfile_ReturnsCorrectValues() {
        Context roboContext = ApplicationProvider.getApplicationContext();

        TipProfile defaultProfile = new TipProfile(roboContext);

        double tipGood = defaultProfile.getTipAmount(10.00,
                TipProfile.SERVICE_GOOD);
        assertThat(tipGood).isEqualTo(2.50);
    }

}
