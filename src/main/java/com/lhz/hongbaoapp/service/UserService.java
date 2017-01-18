package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.User;

/**
 * Created by LHZ on 2016/12/26.
 */
public interface UserService {
    //判断用户名是否存在
    boolean existUsername(String username);
    //查询用户信息
    User selectUserByUsername(String username);
    //注册
    boolean register(User user);
    //修改密码
    boolean changePassword(String username,String newpassword);
    //修改手机号
    boolean changePhone(String username,String phone);
    //修改用户昵称
    boolean changeNicename(String username,String nicename);
}
