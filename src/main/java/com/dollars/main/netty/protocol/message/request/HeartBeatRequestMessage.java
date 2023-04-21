package com.dollars.main.netty.protocol.message.request;

import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.MessageType;
import lombok.Data;

@Data
public class HeartBeatRequestMessage extends Message {

    int ping;

    @Override
    public MessageType getMessageType() {
        return MessageType.HEARTBEAT_REQ;
    }
}
