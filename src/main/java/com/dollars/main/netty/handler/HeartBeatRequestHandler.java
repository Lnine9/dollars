package com.dollars.main.netty.handler;

import com.dollars.main.netty.protocol.message.request.HeartBeatRequestMessage;
import com.dollars.main.netty.protocol.message.response.HeartBeatResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestMessage msg) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponseMessage());
    }


}
