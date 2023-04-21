package com.dollars.main.netty.protocol.message;

import java.io.Serializable;

public abstract class Message implements Serializable {

    public abstract MessageType getMessageType();

}
