package com.dollars.main.netty.protocol.message.response;

import com.dollars.main.netty.protocol.message.MessageType;

public class ErrorResponseMessage extends Response<Object>{

    public ErrorResponseMessage() {
        super(null, false, "");
    }

    public ErrorResponseMessage(String msg){
        super(null, false, msg);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.ERROR_RESP;
    }
}
