package com.lhz.hongbaoapp.entity;

/**
 * Created by LHZ on 2017/1/17.
 */
public class UserRoomCard {
    private int id;
    private int userId;
    private int roomCardId;

    public UserRoomCard() {
    }

    public UserRoomCard(int userId, int roomCardId) {
        this.userId = userId;
        this.roomCardId = roomCardId;
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

    public int getRoomCardId() {
        return roomCardId;
    }

    public void setRoomCardId(int roomCardId) {
        this.roomCardId = roomCardId;
    }
}
