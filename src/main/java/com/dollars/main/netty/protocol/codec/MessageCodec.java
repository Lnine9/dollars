package com.dollars.main.netty.protocol.codec;

import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class MessageCodec extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> list) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(CodecConfig.MAGIC);
        buffer.writeByte(CodecConfig.VERSION);
        buffer.writeByte(msg.getMessageType().getId());
        buffer.writeByte(CodecConfig.SERIALIZE.getId());
        buffer.writeByte(0);//占位
        byte[] bytes = MsgSerializer.getSerializerById(CodecConfig.SERIALIZE.getId()).serialize(msg);

        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
        list.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> list) throws Exception {
        int magic = msg.readInt();
        int version = msg.readByte();
        int typeId = msg.readByte();
        MessageType type = MessageType.getById(typeId);
        if (type == null) {
            return;
        }
        Class<? extends Message> clazz = type.getClazz();
        int serialize = msg.readByte();
        msg.readByte();//占位
        int len = msg.readInt();
        byte[] bytes = new byte[len];
        msg.readBytes(bytes);
        Message res = MsgSerializer.getSerializerById(serialize).deserialize(bytes, clazz);
        list.add(res);
    }

}
