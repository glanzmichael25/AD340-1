package com.telpirion.demo.testinginclassdemo;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HouseRobolectricTest {

    @Test
    public void defaultHouseWithRobolectric() {

        Context roboContext = ApplicationProvider.getApplicationContext();

        House defaultHouse = new House(roboContext);

        assertThat(defaultHouse.getPrice()).isEqualTo(400000.0);
    }
}
