package com.dollars.main.netty.protocol.message;

import com.dollars.main.netty.protocol.message.request.ChatSendRequestMessage;
import com.dollars.main.netty.protocol.message.request.HeartBeatRequestMessage;
import com.dollars.main.netty.protocol.message.request.RegistryRequestMessage;
import com.dollars.main.netty.protocol.message.response.ChatSendResponseMessage;
import com.dollars.main.netty.protocol.message.response.ErrorResponseMessage;
import com.dollars.main.netty.protocol.message.response.HeartBeatResponseMessage;

public enum MessageType {
    ERROR_RESP(0, ErrorResponseMessage.class),
    CHAT_SEND_REQ(1, ChatSendRequestMessage.class),
    CHAT_SEND_RESP(2, ChatSendResponseMessage.class),
    REGISTRY_REQ(3,RegistryRequestMessage.class),
    HEARTBEAT_REQ(4,HeartBeatRequestMessage.class),
    HEARTBEAT_RESP(5, HeartBeatResponseMessage.class);

    private final int id;
    private final Class<? extends Message> clazz;

    MessageType(int id, Class<? extends Message> clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public Class<? extends Message> getClazz() {
        return clazz;
    }

    public static MessageType getById(int id){
        for (MessageType type : MessageType.values()) {
            if (type.getId() == id){
                return type;
            }
        }
        return null;
    }
}
