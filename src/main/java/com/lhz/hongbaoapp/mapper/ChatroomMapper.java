package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.Chatroom;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LHZ on 2017/1/18.
 */
@Mapper
public interface ChatroomMapper {
    boolean save(Chatroom chatroom);
    Chatroom selectById(int id);
    Chatroom selectByChatroomId(String chatroomId);
    boolean updateActiveTime(String chatroom_id,int time);
}
