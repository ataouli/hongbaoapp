package com.lhz.hongbaoapp.common;

import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by LHZ on 2017/1/9.
 */
@Component
public class BeanFactory {
    private static EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IMUserAPI imUserAPI(){
        IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        return user;
    }

    @Bean
    public SendMessageAPI sendMessageAPI(){
        SendMessageAPI sendMessageAPI= (SendMessageAPI) factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
        return sendMessageAPI;
    }

    @Bean
    public FileAPI fileAPI(){
        FileAPI fileAPI= (FileAPI) factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
        return fileAPI;
    }

    @Bean
    public ChatRoomAPI chatRoomAPI(){
        ChatRoomAPI chatRoomAPI= (ChatRoomAPI) factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
        return chatRoomAPI;
    }
}
