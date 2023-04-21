package com.dollars.main.netty.protocol.codec;

import com.dollars.main.netty.protocol.message.Message;
import com.dollars.main.netty.protocol.message.request.ChatSendRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(CodecConfig.MAX_FRAME_LENGTH, CodecConfig.LENGTH_FIELD_OFFSET, 4, 0, 0),
                new LoggingHandler(),
                new MessageCodec()
        );

        ChatSendRequestMessage msg = new ChatSendRequestMessage(1l, 0l, "ttt");

        List<Object> list = new ArrayList<>();

        try {
            new MessageCodec().encode(null, msg, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        channel.writeInbound(list.get(0));
    }
}
