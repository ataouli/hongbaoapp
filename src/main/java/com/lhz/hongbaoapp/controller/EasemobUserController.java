package com.lhz.hongbaoapp.controller;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.ModifyNicknameBody;
import com.easemob.server.example.comm.body.ResetPasswordBody;
import com.easemob.server.example.comm.body.UserNamesBody;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.model.UserModel;
import com.lhz.hongbaoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;

/**
 * Created by LHZ on 2017/1/9.
 */
@RestController
@RequestMapping("/easemob/haoniusoft/hongbaoapp")
public class EasemobUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private IMUserAPI imUserAPI;
    //注册 IM 用户[单个]
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity createNewIMUserSingle(@RequestBody UserModel userModel){
        String username=userModel.getUsername();
        verifyUsername(username);
        IMUserBody body=new IMUserBody(username,username,username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.createNewIMUserSingle(body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //获取 IM 用户[单个]
    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public ResponseEntity getIMUserByUserName(@PathVariable String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getIMUserByUserName(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //修改用户昵称
    @RequestMapping(value = "/users/{username}",method = RequestMethod.PUT)
    public ResponseEntity modifyIMUserNickNameWithAdminToken(@PathVariable String username){
        verifyUsername(username);
        User user=userService.selectUserByUsername(username);
        ModifyNicknameBody  body=new ModifyNicknameBody(user.getNickname());
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.modifyIMUserNickNameWithAdminToken(username,body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //重置 IM 用户密码
    @RequestMapping(value = "/users/{username}/password",method = RequestMethod.PUT)
    public ResponseEntity modifyIMUserPasswordWithAdminToken(@PathVariable String username,@RequestBody String newpassword){
        verifyUsername(username);
        ResetPasswordBody  body=new ResetPasswordBody(newpassword);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.modifyIMUserPasswordWithAdminToken(username,body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //给 IM 用户添加好友
    @RequestMapping(value = "/users/{owner_username}/contacts/users/{friend_username}",method = RequestMethod.PUT)
    public ResponseEntity addFriendSingle(@PathVariable("owner_username") String username,@PathVariable("friend_username") String friendName){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.addFriendSingle(username,friendName);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //解除 IM 用户的好友关系
    @RequestMapping(value = "/users/{owner_username}/contacts/users/{friend_username}",method = RequestMethod.DELETE)
    public ResponseEntity deleteFriendSingle(@PathVariable("owner_username") String username,@PathVariable("friend_username") String friendName ){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.deleteFriendSingle(username,friendName);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //查看好友
    @RequestMapping(value = "/users/{owner_username}/contacts/users",method = RequestMethod.GET)
    public ResponseEntity getFriends(@PathVariable("owner_username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getFriends(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //获取 IM 用户的黑名单
    @RequestMapping(value = "/users/{owner_username}/blocks/users",method = RequestMethod.GET)
    public ResponseEntity getBlackList(@PathVariable("owner_username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getBlackList(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //往 IM 用户的黑名单中加人
    @RequestMapping(value = "/users/{owner_username}/blocks/users",method = RequestMethod.POST)
    public ResponseEntity addToBlackList(@PathVariable("owner_username") String username,@RequestBody String[] usernames){
        verifyUsername(username);
        UserNamesBody body=new UserNamesBody(usernames);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.addToBlackList(username,body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //从 IM 用户的黑名单中减人
    @RequestMapping(value = "/users/{owner_username}/blocks/users/{blocked_username}",method = RequestMethod.DELETE)
    public ResponseEntity removeFromBlackList(@PathVariable("owner_username") String username,@PathVariable("blocked_username") String blackListName){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.removeFromBlackList(username,blackListName);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //查看用户在线状态
    @RequestMapping(value = "/users/{username}/status",method = RequestMethod.GET)
    public ResponseEntity getIMUserStatus(@PathVariable String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getIMUserStatus(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //查询离线消息数
    @RequestMapping(value = "/users/{owner_username}/offline_msg_count",method = RequestMethod.GET)
    public ResponseEntity getOfflineMsgCount(@PathVariable("owner_username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getOfflineMsgCount(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //查询某条离线消息状态
    @RequestMapping(value = "/users/{username}/offline_msg_status/{msg_id}",method = RequestMethod.GET)
    public ResponseEntity getSpecifiedOfflineMsgStatus(@PathVariable("username") String username,@PathVariable("msg_id") String msgId){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.getSpecifiedOfflineMsgStatus(username,msgId);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //用户账号禁用
    @RequestMapping(value = "/users/{username}/deactivate",method = RequestMethod.POST)
    public ResponseEntity deactivateIMUser(@PathVariable("username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.deactivateIMUser(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //用户账号解禁
    @RequestMapping(value = "/users/{username}/activate",method = RequestMethod.POST)
    public ResponseEntity activateIMUser(@PathVariable("username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.activateIMUser(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //强制用户下线
    @RequestMapping(value = "/users/{username}/disconnect",method = RequestMethod.GET)
    public ResponseEntity disconnectIMUser(@PathVariable("username") String username){
        verifyUsername(username);
        ResponseWrapper rw= (ResponseWrapper) imUserAPI.disconnectIMUser(username);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }

    //校验用户的合法性
    private void verifyUsername(String username){
        String seesionUsername=session.getAttribute("username") + "";
        Assert.isTrue(seesionUsername.equals(username),String.format("%s非法获取%s数据",seesionUsername,username));
    }
}
