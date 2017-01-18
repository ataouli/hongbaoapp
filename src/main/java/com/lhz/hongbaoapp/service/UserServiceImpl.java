package com.lhz.hongbaoapp.service;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.ModifyNicknameBody;
import com.easemob.server.example.comm.body.ResetPasswordBody;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LHZ on 2016/12/26.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    private IMUserAPI imUserAPI;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean existUsername(String username) {
        return userMapper.selectByUsername(username)!=null;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Transactional
    @Override
    public boolean register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userMapper.save(user)){
            IMUserBody body=new IMUserBody(user.getUsername(),user.getPassword(),"");
            ResponseWrapper rw= (ResponseWrapper) imUserAPI.createNewIMUserSingle(body);
            if(rw.hasError()){
                throw new RuntimeException();
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    @Transactional
    @Override
    public boolean changePassword(String username,String newpassword) {
        newpassword=passwordEncoder.encode(newpassword);
        if(userMapper.updatePassword(username,newpassword)){
            ResetPasswordBody body=new ResetPasswordBody(newpassword);
            ResponseWrapper rw= (ResponseWrapper) imUserAPI.modifyIMUserPasswordWithAdminToken(username,body);
            if(rw.hasError()){
                throw new RuntimeException();
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    @Transactional
    @Override
    public boolean changePhone(String username, String phone) {
        return userMapper.updatePhone(username,phone);
    }

    @Override
    public boolean changeNicename(String username, String nicename) {
        ModifyNicknameBody body=new ModifyNicknameBody(nicename);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.modifyIMUserNickNameWithAdminToken(username,body);
        if(rw.hasError()){
            return false;
        }else{
            return true;
        }
    }
}
