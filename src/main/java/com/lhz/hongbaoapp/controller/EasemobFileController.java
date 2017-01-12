package com.lhz.hongbaoapp.controller;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by LHZ on 2017/1/12.
 */
@RestController
@RequestMapping("/easemob/haoniusoft/hongbaoapp")
public class EasemobFileController {
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private HttpSession session;
    @Autowired
    public FileAPI fileAPI;
    //上传语音/图片文件
    @RequestMapping(value = "/chatfiles",method = RequestMethod.POST)
    public ResponseEntity createNewIMUserSingle(@RequestParam("file") MultipartFile file){
        String username=session.getAttribute("username")+"";
        String filePath=System.getProperty("java.io.tmpdir")+"/hongbaoapp/"+format.format(new Date())+"/"+username;
        File logFile=new File(filePath);
        logFile.mkdirs();
        String uuid=UUID.randomUUID().toString();
        logFile=new File(logFile.getAbsolutePath()+"/"+ uuid);
        try {
            file.transferTo(logFile);
            ResponseWrapper rw= (ResponseWrapper) fileAPI.uploadFile(logFile);
            return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
        } catch (IOException e) {
            LOGGER.error(String.format("%s上传文件出错,文件名：%s",username,file.getOriginalFilename()),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("上传文件出错");
        }finally {
            logFile.delete();
        }
    }
    //下载语音/图片文件
    @RequestMapping(value = "/chatfiles/{uuid}",method = RequestMethod.POST)
    public ResponseEntity downloadFile(@RequestHeader("share-secret") String shareSecret,@PathVariable("uuid") String uuid){
        ResponseWrapper rw= (ResponseWrapper) fileAPI.downloadFile(uuid,shareSecret,false);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }
    //下载缩略图
    @RequestMapping(value = "/chatfiles/{uuid}",method = RequestMethod.POST,headers = "thumbnail")
    public ResponseEntity downloadFile(@RequestHeader("share-secret") String shareSecret,@RequestHeader("isThumbnail") boolean isThumbnail,@PathVariable("uuid") String uuid){
        ResponseWrapper rw= (ResponseWrapper) fileAPI.downloadFile(uuid,shareSecret,isThumbnail);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());
    }

}
