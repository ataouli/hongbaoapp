package com.lhz.hongbaoapp.controller;

import com.lhz.hongbaoapp.common.Constant;
import com.lhz.hongbaoapp.common.ResponseBean;
import com.lhz.hongbaoapp.entity.User;
import com.lhz.hongbaoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LHZ on 2016/11/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("code") String code) {
        Map<String, Object> map = new HashMap<>();
        /*Object sessionCode=session.getAttribute(Constant.phoneCode);
        if(sessionCode==null||!(sessionCode+"").equals(code)){
            ResponseBean bean=new ResponseBean(false,"验证码错误");
            map.put("data",bean);
            return ResponseEntity.ok(map);
        }*/
        Pattern pattern = Pattern.compile(Constant.usernameRegex);
        Matcher matcher = pattern.matcher(username);
        if(!matcher.matches()){
            ResponseBean bean=new ResponseBean(false,"用户名只能是大小写字母/数字/下划线/横线/英文句号");
            map.put("data",bean);
            return ResponseEntity.ok(map);
        }
        User user=new User(username,password,phone);
        if (userService.existUsername(user.getUsername())) {
            ResponseBean bean=new ResponseBean(false,"用户名已存在");
            map.put("data",bean);
            return ResponseEntity.ok(map);
        }
        boolean result = userService.register(user);
        if (result) {
            ResponseBean bean=new ResponseBean(true,"注册成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"注册失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }


    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@RequestParam("newpassword") String newpassword) {
        String username = session.getAttribute("username") + "";
        Map<String, Object> map = new HashMap<>();
        boolean result=userService.changePassword(username,newpassword);
        if (result) {
            ResponseBean bean=new ResponseBean(true,"修改成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"修改失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }


    @RequestMapping(value = "/phone", method = RequestMethod.PUT)
    public ResponseEntity changePhone(@RequestParam("phone") String phone,
                                      @RequestParam("code") String code) {
        Map<String, Object> map = new HashMap<>();
        /*Object sessionCode=session.getAttribute(Constant.phoneCode);
        if(sessionCode==null||!(sessionCode+"").equals(code)){
            ResponseBean bean=new ResponseBean(false,"验证码错误");
            map.put("data",bean);
            return ResponseEntity.ok(map);
        }*/
        String username = session.getAttribute("username") + "";
        boolean result=userService.changePhone(username,phone);
        if (result) {
            ResponseBean bean=new ResponseBean(true,"修改成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"修改失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/nickname", method = RequestMethod.PUT)
    public ResponseEntity changePhone(@RequestParam("nickname") String nickname){
        String username = session.getAttribute("username") + "";
        boolean result=userService.changeNicename(username,nickname);
        Map<String, Object> map = new HashMap<>();
        if (result) {
            ResponseBean bean=new ResponseBean(true,"修改成功");
            map.put("data",bean);
        } else {
            ResponseBean bean=new ResponseBean(false,"修改失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/image", method = RequestMethod.PUT)
    public ResponseEntity changePhone(HttpServletRequest request, @RequestParam("image") MultipartFile file){
        String username=session.getAttribute("username")+"";
        Map<String, Object> map = new HashMap<>();
        try{
            String imagePath=request.getServletContext().getRealPath("/")+"/image/"+username;
            File logFile=new File(imagePath);
            logFile.mkdirs();
            logFile=new File(logFile.getAbsolutePath()+"/head.png");
            file.transferTo(logFile);
            ResponseBean bean=new ResponseBean(true,"修改成功");
            map.put("data",bean);
        }catch (Exception e){
            logger.error(String.format("%s上传文件出错,文件名：%s",username,file.getOriginalFilename()),e);
            ResponseBean bean=new ResponseBean(true,"修改失败");
            map.put("data",bean);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUsername(){
        session.setAttribute("username","lhz");
        String username=session.getAttribute("username")+"";
        User user=userService.selectUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("data",user);
        return ResponseEntity.ok(map);
    }
}
