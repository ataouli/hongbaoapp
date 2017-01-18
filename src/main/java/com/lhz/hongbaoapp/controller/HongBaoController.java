package com.lhz.hongbaoapp.controller;

import com.easemob.server.example.api.SendMessageAPI;
import com.lhz.hongbaoapp.entity.HongBao;
import com.lhz.hongbaoapp.service.HongBaoService;
import com.lhz.hongbaoapp.util.HongBaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by LHZ on 2017/1/12.
 */
@RestController
@RequestMapping("/hongbao")
public class HongBaoController {
    @Autowired
    private HongBaoService hongBaoService;
    @Autowired
    private SendMessageAPI sendMessageAPI;

    @RequestMapping(value = "/send",method = RequestMethod.POST )
    public ResponseEntity send(@RequestBody HongBao hongBao,@PathVariable String usernmae){
        if(!hongBaoService.save(hongBao)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("发送失败");
        }
        HongBaoUtil.setHongbao(hongBao);
        return ResponseEntity.ok("");
        /*String[] target={hongBao.getRoomName()};
        String from=hongBao.getFrom();
        Map<String, String> ext=new HashMap<>();
        ext.put("type","hongbao");
        String msg=String.format("来自%s的红包",from);
        MessageBody body=new TextMessageBody("chatrooms",target,from,ext,msg);
        ResponseWrapper rw= (ResponseWrapper) sendMessageAPI.sendMessage(body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());*/
    }

    @RequestMapping(value = "/receive/{username}",method = RequestMethod.POST )
    public ResponseEntity receive(@RequestBody HongBao hongBao, @PathVariable String usernmae){
        Assert.isTrue(hongBao.verify());

        Map<String,String> ext=HongBaoUtil.receiveHongbao(hongBao);
        if(ext.isEmpty()){
            return ResponseEntity.ok("很遗憾，你来晚了！");
        }else{
            return ResponseEntity.ok(ext);
        }
        /*String[] target={hongBao.getRoomID()};
        String from=hongBao.getFrom();
        ext.put("type","hongbao");
        String msg=String.format("%s领取了%s的红包",usernmae,from);
        MessageBody body=new TextMessageBody("chatrooms",target,from,ext,msg);
        ResponseWrapper rw= (ResponseWrapper) sendMessageAPI.sendMessage(body);
        return ResponseEntity.status(rw.getResponseStatus()).body(rw.getResponseBody());*/
    }
}
