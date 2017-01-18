package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.common.ResponseBean;
import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.entity.UserRoomCard;
import com.lhz.hongbaoapp.service.UserRoomCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LHZ on 2016/11/10.
 */
@RestController
@RequestMapping("/userRoomCard")
public class UserRoomCardController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoomCardController.class);
    @Autowired
    UserRoomCardService userRoomCardService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity buyRoomCard(@RequestParam("roomCardId") int roomCardId) {
        Map<String, Object> map = new HashMap<>();
        User user= (User) session.getAttribute("user");
        UserRoomCard userRoomCard=new UserRoomCard(user.getId(),roomCardId);
        boolean result=userRoomCardService.buyUserRoomCard(userRoomCard);
        if (result) {
            ResponseBean bean=new ResponseBean(true,"购买成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"购买失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getByUserId() {
        Map<String, Object> map = new HashMap<>();
        User user= (User) session.getAttribute("user");
        List<UserRoomCard> list= userRoomCardService.getUserRoomCard(user.getId());
        map.put("data",list);
        return ResponseEntity.ok(map);
    }
}
