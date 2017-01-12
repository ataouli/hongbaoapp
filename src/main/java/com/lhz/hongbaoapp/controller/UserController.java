package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LHZ on 2016/11/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method =RequestMethod.POST )
    public ResponseEntity register(@RequestBody User user){
        Map<String,Object> map=new HashMap<>();
        if(!verifyUser(user)){
            map.put("data","参数不合法");
            return ResponseEntity.ok(map);
        }
        if(userService.existUsername(user.getUsername())){
            map.put("data","用户名已存在");
            return ResponseEntity.ok(map);
        }
        boolean result=userService.register(user);
        if(result){
            map.put("data","注册成功");
        }else{
            map.put("data","注册失败");
        }
        return ResponseEntity.ok(map);
    }

    //参数校验
    private boolean verifyUser(User user){
        if(user==null||StringUtils.isEmpty(user.getUsername())
                ||StringUtils.isEmpty(user.getPassword())
                ||StringUtils.isEmpty(user.getPhone())){
            return false;
        }else{
            return true;
        }
    }
}
