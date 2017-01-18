package com.lhz.hongbaoapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.comm.body.ChatRoomBody;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.lhz.hongbaoapp.entity.Chatroom;
import com.lhz.hongbaoapp.entity.RoomCard;
import com.lhz.hongbaoapp.entity.UserRoomCard;
import com.lhz.hongbaoapp.mapper.ChatroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LHZ on 2017/1/18.
 */
public class ChatroomServiceImpl implements ChatroomService{
    @Autowired
    ChatroomMapper chatroomMapper;
    @Autowired
    RoomCardService roomCardService;
    @Autowired
    UserRoomCardService userRoomCardService;
    @Autowired
    private ChatRoomAPI chatRoomAPI;
    @Transactional
    @Override
    public boolean createChatroom(UserRoomCard userRoomCard, Chatroom chatroom) {
        boolean result=userRoomCardService.expenseUsrRoomCard(userRoomCard.getUserId(),userRoomCard.getId());
        if(result){
            ChatRoomBody body=new ChatRoomBody(chatroom.getName(), chatroom.getDescription(),null, chatroom.getOwner(),null);
            ResponseWrapper rw= (ResponseWrapper) chatRoomAPI.createChatRoom(body);
            if(rw.hasError()){
                throw new RuntimeException("创建房间失败");
            }
            JSONObject data=JSON.parseObject(rw.getResponseBody().toString());
            data= (JSONObject) JSON.toJSON(data.get("data"));
            String chatroomId=data.get("id")+"";
            chatroom.setChatroomId(chatroomId);
            RoomCard roomCard=roomCardService.getRoomCardById(userRoomCard.getRoomCardId());
            chatroom.setActiveTime(roomCard.getActiveTime());
            result=chatroomMapper.save(chatroom);
        }
        if(result){
            return  true;
        }else{
            throw new RuntimeException("创建房间失败");
        }
    }

    @Override
    public boolean joinChatroom(String chatroomid,String username) {
        ResponseWrapper rw= (ResponseWrapper) chatRoomAPI.addSingleUserToChatRoom(chatroomid,username);
        return !rw.hasError();
    }

    @Override
    public int getExcessTime(String chatroomid) {
        Chatroom chatroom=chatroomMapper.selectByChatroomId(chatroomid);
        if(chatroom==null){
            return 0;
        }else{
            long start=chatroom.getCreateTime().getTime();
            int passTime= (int) (System.currentTimeMillis()-start);
            int activeTime=chatroom.getActiveTime();
            int excessTime=activeTime-passTime;
            return excessTime;
        }
    }
    @Transactional
    @Override
    public boolean pay(String chatroomId,UserRoomCard userRoomCard) {
        boolean result=userRoomCardService.expenseUsrRoomCard(userRoomCard.getUserId(),userRoomCard.getId());
        if(result){
            RoomCard roomCard=roomCardService.getRoomCardById(userRoomCard.getRoomCardId());
            result=chatroomMapper.updateActiveTime(chatroomId,roomCard.getActiveTime());
        }
        if(result){
            return true;
        }else{
            throw new RuntimeException("创建房间失败");
        }
    }
}
