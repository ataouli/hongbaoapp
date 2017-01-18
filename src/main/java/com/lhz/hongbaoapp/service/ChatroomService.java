package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.Chatroom;
import com.lhz.hongbaoapp.entity.UserRoomCard;

/**
 * Created by LHZ on 2017/1/17.
 */
public interface ChatroomService {
    //创建聊天室
    boolean createChatroom(UserRoomCard userRoomCard,Chatroom chatroom);
    //加入聊天室
    boolean joinChatroom(String username,String chatroomid);
    //获取房间剩余时间
    int getExcessTime(String chatroomid);
    //续时
    boolean pay(String chatroomId,UserRoomCard userRoomCard);

}
