package com.dollars.main.netty.handler;

import com.dollars.main.netty.protocol.message.request.ChatSendRequestMessage;
import com.dollars.main.netty.protocol.message.response.ChatSendResponseMessage;
import com.dollars.main.service.SessionService;
import com.dollars.main.service.impl.SessionServiceImpl;
import com.dollars.main.util.SpringBeanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatSendHandler extends SimpleChannelInboundHandler<ChatSendRequestMessage> {

    private final SessionService service = SpringBeanUtil.getBean(SessionServiceImpl.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatSendRequestMessage msg) throws Exception {
        System.out.println(msg);
        ChatSendResponseMessage res = new ChatSendResponseMessage("对方当前不在线", false);
        Channel to = service.getChannel(msg.getTo());
        if (to != null){
            to.writeAndFlush(new ChatSendRequestMessage(msg.getFrom(), msg.getTo(), msg.getMsg()));
            res = new ChatSendResponseMessage("发送成功", true);
        }
        ctx.writeAndFlush(res);
    }
}
