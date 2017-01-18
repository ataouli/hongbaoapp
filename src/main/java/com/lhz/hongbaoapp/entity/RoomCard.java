package com.lhz.hongbaoapp.entity;

/**
 * Created by LHZ on 2017/1/15.
 */
public class RoomCard {
    private int id;
    private String name;
    private float price;
    private int activeTime;
    public RoomCard(){

    }

    public RoomCard(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }
}
