package com.lhz.hongbaoapp.entity;

/**
 * Created by LHZ on 2017/1/12.
 */
public class HongBao {
    private int id;
    private String from;
    private String roomName;
    private String roomID;
    private String roomPassword;
    private int amount;
    private float money;
    private int punishRanking;
    private float rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getPunishRanking() {
        return punishRanking;
    }

    public void setPunishRanking(int punishRanking) {
        this.punishRanking = punishRanking;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean verify(){
        if(money>0&&punishRanking>0&&punishRanking<=amount&&rate>0){
            return true;
        }else{
            return false;
        }
    }
}
