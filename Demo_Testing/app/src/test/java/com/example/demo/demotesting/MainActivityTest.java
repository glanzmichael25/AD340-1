package com.example.demo.demotesting;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testNotEnoughDataProvided() {
        MainActivity activity =
                Robolectric.setupActivity(MainActivity.class);

        activity.findViewById(R.id.button).performClick();
        TextView myText = activity.findViewById(R.id.tip_type);

        assertEquals("Check your inputs",
                ShadowToast.getTextOfLatestToast());
        assertThat(myText.getText().toString()).contains("Tip type:");

    }
}