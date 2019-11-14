package com.telpirion.demo.testinginclassdemo;

import android.content.Context;

public class House {

    private double price;
    private int rooms;
    private int bathrooms;

    public House(double price, int rooms, int bathrooms) {
        this.price = price;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
    }

    public House(Context context) {
        this.price = Double.parseDouble(context.getString(R.string.default_price));
        this.rooms = Integer.parseInt(context.getString(R.string.default_rooms));
        this.bathrooms = Integer.parseInt(context.getString(R.string.default_bathrooms));
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public int getRooms() {
        return rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void remodel(int newBathrooms, int newRooms) {

        if (newBathrooms <= 0) {
            this.bathrooms = 1;
        } else {
            this.bathrooms = newBathrooms;
        }

        if (newRooms <=0) {
            this.rooms = 1;
        } else {
            this.rooms = newRooms;
        }
    }

}
