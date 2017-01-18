package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.UserRoomCard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LHZ on 2017/1/18.
 */
@Mapper
public interface UserRoomCardMapper {
    boolean save(UserRoomCard userRoomCard);
    List<UserRoomCard> seletctByUserId(int userid);
    boolean deleteByUserIdAndId(int userid,int id);
}
