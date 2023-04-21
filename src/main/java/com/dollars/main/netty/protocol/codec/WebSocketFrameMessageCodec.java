package com.dollars.main.netty.protocol.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

public class WebSocketFrameMessageCodec extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> list) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = MsgSerializer.Json.serialize(msg);
        buffer.writeBytes(bytes);
        list.add(new TextWebSocketFrame(buffer));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> list) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        msg.retain();
        JSONObject obj = JSON.parseObject(bytes);
        int type = obj.getInteger("messageType");
        Class<? extends Message> clazz = MessageType.getById(type).getClazz();
        Message res = JSON.parseObject(bytes, clazz);
        list.add(res);
    }
}
