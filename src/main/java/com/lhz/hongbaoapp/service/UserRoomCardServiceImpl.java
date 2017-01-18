package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.RoomCard;
import com.lhz.hongbaoapp.entity.UserRoomCard;
import com.lhz.hongbaoapp.mapper.UserRoomCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LHZ on 2017/1/17.
 */
public class UserRoomCardServiceImpl implements UserRoomCardService{
    @Autowired
    UserRoomCardMapper userRoomCardMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    RoomCardService roomCardService;
    @Transactional
    @Override
    public boolean buyUserRoomCard(UserRoomCard userRoomCard) {
        int roomCardId=userRoomCard.getRoomCardId();
        RoomCard roomCard=roomCardService.getRoomCardById(roomCardId);
        float price=roomCard.getPrice();
        int userId=userRoomCard.getUserId();
        boolean result=accountService.expense(userId,price);
        if(result){
            result=userRoomCardMapper.save(userRoomCard);
        }
        return result;
    }

    @Override
    public List<UserRoomCard> getUserRoomCard(int userid) {
        return userRoomCardMapper.seletctByUserId(userid);
    }

    @Override
    public boolean expenseUsrRoomCard(int userid, int id) {
        return userRoomCardMapper.deleteByUserIdAndId(userid,id);
    }
}
