package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.RoomCard;

import java.util.List;

/**
 * Created by LHZ on 2017/1/17.
 */
public interface RoomCardService {
    //新增房卡类型
    boolean saveRoomCard(RoomCard r);
    //获取所有房卡信息
    List<RoomCard> getAllRoomCard();
    //获取所有房卡信息
    RoomCard getRoomCardById(int id);

}
