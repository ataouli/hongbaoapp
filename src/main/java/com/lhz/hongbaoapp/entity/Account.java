package com.lhz.hongbaoapp.entity;

/**
 * Created by LHZ on 2017/1/18.
 */
public class Account {
    private int id;
    private int userId;
    private float money;

    public Account(int userId, float money) {
        this.userId = userId;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
