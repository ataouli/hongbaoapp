package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LHZ on 2017/1/17.
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private HttpSession session;
    @RequestMapping(value = "/code/{phone}", method = RequestMethod.GET)
    public ResponseEntity register(@PathVariable String phone) {
        Map<String, Object> map = new HashMap<>();
        Pattern pattern = Pattern.compile(Constant.phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        if(matcher.matches()){
            //TODO 发送验证码
            session.setAttribute(Constant.phoneCode,"");
            map.put("result",true);
            map.put("message","发送成功");
        }else{
            map.put("result",false);
            map.put("message","手机号格式错误");
        }
        return ResponseEntity.ok(map);
    }
}
