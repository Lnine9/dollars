package com.dollars.main.netty.protocol.message.request;

import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatSendRequestMessage extends Message {

    private final Long from;
    private final Long to;
    private final String msg;

    @Override
    public MessageType getMessageType() {
        return MessageType.CHAT_SEND_REQ;
    }
}
