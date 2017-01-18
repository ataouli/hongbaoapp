package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.UserRoomCard;

import java.util.List;

/**
 * Created by LHZ on 2017/1/17.
 */
public interface UserRoomCardService {
    //购买房卡
    boolean buyUserRoomCard(UserRoomCard userRoomCard);
    //查询用户的房卡
    List<UserRoomCard> getUserRoomCard(int userid);
    //消费房卡
    boolean expenseUsrRoomCard(int userid,int id);
}
