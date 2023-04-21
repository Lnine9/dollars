package com.dollars.main.netty.protocol.message.response;

import com.dollars.main.netty.protocol.message.MessageType;

public class ChatSendResponseMessage extends Response<String> {

    public ChatSendResponseMessage(String result, boolean success) {
        super(result, success, "");
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CHAT_SEND_RESP;
    }
}
