package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.RoomCard;
import com.lhz.hongbaoapp.mapper.RoomCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LHZ on 2017/1/17.
 */
@Service
public class RoomCardServiceImpl implements RoomCardService{
    @Autowired
    private RoomCardMapper roomCardMapper;

    @Override
    public boolean saveRoomCard(RoomCard roomCard) {
        return roomCardMapper.save(roomCard);
    }

    @Override
    public List<RoomCard> getAllRoomCard() {
        return roomCardMapper.selectAll();
    }

    @Override
    public RoomCard getRoomCardById(int id) {
        return roomCardMapper.selectById(id);
    }
}
