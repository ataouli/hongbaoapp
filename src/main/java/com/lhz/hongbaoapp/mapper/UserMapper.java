package com.lhz.hongbaoapp.mapper;

import com.lhz.hongbaoapp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LHZ on 2017/1/6.
 */
@Mapper
public interface UserMapper {
    boolean insertUser(User user);
    User SelectByUsername(String username);
}
