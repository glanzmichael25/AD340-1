package com.example.demo.demotesting;

import android.content.Context;

import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


public class TipProfileTest {

    TipProfile foodServiceProfile;

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        this.foodServiceProfile =
                new TipProfile("Food server", 0.20);


        MockitoAnnotations.initMocks(this);

        when(mockContext.getString(R.string.default_tip_profile))
                .thenReturn("Default profile");
        when(mockContext.getString(R.string.default_tip_amt_str))
                .thenReturn("0.25");
    }


    @Test
    public void tipProfile_FoodServer_ReturnsCorrectValues() {
        double tipAmountGood = foodServiceProfile.getTipAmount(10.00,
                TipProfile.SERVICE_GOOD);
        assertThat(tipAmountGood).isEqualTo(2.00);

        double tipMediocre = foodServiceProfile.getTipAmount(10.00,
                TipProfile.SERVICE_MEH);
        assertThat(tipMediocre).isEqualTo(1.50);

        double tipBad = foodServiceProfile.getTipAmount(10.00,
                TipProfile.SERVICE_BAD);
        assertThat(tipBad).isEqualTo(1.00);
    }


    @Test
    public void tipProfile_Default_ReturnsCorrectValues() {
        TipProfile defaultProfile = new TipProfile(mockContext);

        double tipGood = defaultProfile.getTipAmount(10.00,
                TipProfile.SERVICE_GOOD);
        assertThat(tipGood).isEqualTo(2.50);
    }

    @After
    public void tearDown() {

    }

}
