package com.lhz.hongbaoapp.service;

import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by LHZ on 2016/12/26.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean existUsername(String username) {
        return userMapper.SelectByUsername(username)!=null;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.SelectByUsername(username);
    }

    @Override
    public boolean register(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insertUser(user);
    }
}
