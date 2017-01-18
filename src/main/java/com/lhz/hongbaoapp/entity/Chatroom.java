package com.lhz.hongbaoapp.entity;

import java.sql.Timestamp;

/**
 * Created by LHZ on 2017/1/18.
 */
public class Chatroom {
    private long id;
    private String name;
    private String description;
    private String owner;
    private Timestamp createTime;
    private int activeTime;
    private String chatroomId;

    public Chatroom(String name, String description, String owner,int activeTime) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.activeTime = activeTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }
}
