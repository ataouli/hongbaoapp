package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LHZ on 2017/1/18.
 */
@Mapper
public interface AccountMapper {
    boolean save(Account account);
    Account selectByUserId(int userId);
    boolean updateMoney(int userId,float money);
}
