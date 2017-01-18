package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.Account;
import com.lhz.hongbaoapp.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LHZ on 2017/1/18.
 */
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountMapper accountMapper;

    @Override
    public float getUserMoney(int userId) {
        Account account=accountMapper.selectByUserId(userId);
        if(account==null){
            return 0;
        }else{
            return account.getMoney();
        }
    }

    @Override
    public boolean pay(int userId, float money) {
        Account account=accountMapper.selectByUserId(userId);
        if(account==null){
            Account taccount=new Account(userId,money);
            return accountMapper.save(taccount);
        }else{
            float tmoney=account.getMoney()+money;
            return accountMapper.updateMoney(userId,tmoney);
        }
    }

    @Override
    public boolean expense(int userId, float money) {
        Account account=accountMapper.selectByUserId(userId);
        if(account!=null&&account.getMoney()>=money){
            float tmoney=account.getMoney()-money;
            return accountMapper.updateMoney(userId,tmoney);
        }else{
            return false;
        }
    }
}
