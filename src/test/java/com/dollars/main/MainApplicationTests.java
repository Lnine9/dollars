package com.dollars.main;

import com.alibaba.fastjson.JSON;
import com.dollars.main.entity.User;
import com.dollars.main.mappers.UserMapper;
import com.dollars.main.netty.protocol.codec.CodecConfig;
import com.dollars.main.netty.protocol.codec.MessageCodec;
import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.request.ChatSendRequestMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(userMapper.getContacts(1L));
    }

}
