package com.dollars.main.netty.protocol.message.request;

import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.MessageType;
import lombok.Data;

@Data
public class RegistryRequestMessage extends Message {

    Long userId;

    @Override
    public MessageType getMessageType() {
        return MessageType.REGISTRY_REQ;
    }
}
