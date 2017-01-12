package com.lhz.hongbaoapp.controller;

import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.lhz.hongbaoapp.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by LHZ on 2017/1/12.
 */
@RestController
@RequestMapping("/easemob/haoniusoft/hongbaoapp")
public class EasemobSendMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private HttpSession session;
    @Autowired
    public SendMessageAPI sendMessageAPI;

    //发送文本消息
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody UserModel userModel){
        String username=userModel.getUsername();
        IMUserBody body=new IMUserBody(username,username,username);
        ResponseWrapper rw= (ResponseWrapper) sendMessageAPI.sendMessage(body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
}
