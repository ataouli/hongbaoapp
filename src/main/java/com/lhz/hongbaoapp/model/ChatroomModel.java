package com.lhz.hongbaoapp.model;

/**
 * Created by LHZ on 2017/1/18.
 */
public class ChatroomModel {
    private String name;
    private String description;
    private long maxusers;
    private String owner;
    private String[] members;
    private int roomCardId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMaxusers() {
        return maxusers;
    }

    public void setMaxusers(long maxusers) {
        this.maxusers = maxusers;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public int getRoomCardId() {
        return roomCardId;
    }

    public void setRoomCardId(int roomCardId) {
        this.roomCardId = roomCardId;
    }
}
