package com.lhz.hongbaoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LHZ on 2017/1/15.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    //充值
    @RequestMapping(value = "/recharge",method = RequestMethod.POST )
    public ResponseEntity recharge(@RequestParam double money){
        return null;
    }

    //转账
    @RequestMapping(value = "/transfer",method = RequestMethod.POST )
    public ResponseEntity recharge(@RequestParam double money,@RequestParam String userid){
        return null;
    }
}
