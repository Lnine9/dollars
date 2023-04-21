package com.dollars.main.netty.protocol.message.response;

import com.dollars.main.netty.protocol.message.MessageType;

public class HeartBeatResponseMessage extends Response<Object>{

    public HeartBeatResponseMessage() {
        super(null, true, "pong");
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.HEARTBEAT_RESP;
    }
}
