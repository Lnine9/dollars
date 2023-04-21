package com.dollars.main.netty.protocol.message.response;

import com.dollars.main.netty.protocol.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Response<T> extends Message {

    private final T result;
    private final boolean success;
    private final String msg;

}
