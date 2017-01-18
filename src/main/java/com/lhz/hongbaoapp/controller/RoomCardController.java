package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.common.ResponseBean;
import com.lhz.hongbaoapp.entity.RoomCard;
import com.lhz.hongbaoapp.service.RoomCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LHZ on 2017/1/15.
 */
@RestController
@RequestMapping("/roomCard")
public class RoomCardController {
    @Autowired
    private HttpSession session;
    @Autowired
    RoomCardService roomCardService;

    @RequestMapping(value = "",method = RequestMethod.POST )
    public ResponseEntity saveAllRoomCard(
            @RequestParam("name") String name,
            @RequestParam("price") float price){
        Map<String, Object> map = new HashMap<>();
        RoomCard roomCard=new RoomCard(name,price);
        boolean result=roomCardService.saveRoomCard(roomCard);
        if (result) {
            ResponseBean bean=new ResponseBean(true,"添加成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"添加失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET )
    public ResponseEntity getAllRoomCard(){
        List<RoomCard> list=roomCardService.getAllRoomCard();
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
    public ResponseEntity getRoomCardById(@PathVariable int id){
        RoomCard roomCard=roomCardService.getRoomCardById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data",roomCard);
        return ResponseEntity.ok(map);
    }

}
