package com.lhz.hongbaoapp.service;

/**
 * Created by LHZ on 2017/1/18.
 */
public interface AccountService {
    //获取账户余额
    float getUserMoney(int userId);
    //充值
    boolean pay(int userId,float money);
    //消费
    boolean expense(int userId,float money);
}
