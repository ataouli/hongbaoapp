package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LHZ on 2017/1/18.
 */
@Mapper
public interface UserMapper {
    boolean save(User user);
    User selectByUsername(String username);
    boolean updatePassword(String username,String password);
    boolean updatePhone(String username,String phone);
}
