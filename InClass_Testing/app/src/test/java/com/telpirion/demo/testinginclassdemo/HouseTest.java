package com.telpirion.demo.testinginclassdemo;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class HouseTest {

    House ourHouse;

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        ourHouse = new House(300000, 2, 1);

        MockitoAnnotations.initMocks(this);

        when(mockContext.getString(R.string.default_bathrooms)).thenReturn("1");
        when(mockContext.getString(R.string.default_rooms)).thenReturn("2");
        when(mockContext.getString(R.string.default_price)).thenReturn("400000");
    }

    @Test
    public void houseChangesRoomsAndBathroomsOnRemodel() {
        ourHouse.remodel(3, 5);
        assertThat(ourHouse.getBathrooms()).isEqualTo(3);

    }

    @Test
    public void houseHasDefaultValuesOnBadRemodelInputs() {
        ourHouse.remodel(-1, 0);
        assertThat(ourHouse.getBathrooms()).isEqualTo(1);
    }

    @Test
    public void defaultHouseLoadsDefaultValues() {
        House defaultHouse = new House(mockContext);

        assertThat(defaultHouse.getPrice()).isEqualTo(400000.0);
    }

}
