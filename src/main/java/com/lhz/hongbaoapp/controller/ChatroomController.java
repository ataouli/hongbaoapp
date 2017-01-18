package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.model.ChatroomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by LHZ on 2017/1/13.
 */
@RestController
@RequestMapping("/chatrooms")
public class ChatroomController {
    @Autowired
    private HttpSession session;

    //创建房间（聊天室）
    @RequestMapping(value = "",method = RequestMethod.POST )
    public ResponseEntity createChatRoom(@RequestBody ChatroomModel chatroomModel){
        session.setAttribute("username","lhz");
        String username=session.getAttribute("username")+"";
        chatroomModel.setOwner(username);

        return null;
    }

    //加入聊天室
    @RequestMapping(value = "/2",method = RequestMethod.POST )
    public ResponseEntity addSingleUserToChatRoom(@RequestParam String password){
        return null;
    }

    //获取聊天室的剩余时间
    @RequestMapping(value = "/remainingTime",method = RequestMethod.GET )
    public ResponseEntity getRemainingTime(){
        return null;
    }

    //房间充值
    @RequestMapping(value = "/{chatroomid}/recharge",method = RequestMethod.POST )
    public ResponseEntity recharge() {
        return null;
    }

}
