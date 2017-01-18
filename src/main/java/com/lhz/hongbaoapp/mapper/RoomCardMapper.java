package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.RoomCard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LHZ on 2017/1/18.
 */
@Mapper
public interface RoomCardMapper {
    boolean save(RoomCard roomCard);
    List<RoomCard> selectAll();
    RoomCard selectById(int id);
}
