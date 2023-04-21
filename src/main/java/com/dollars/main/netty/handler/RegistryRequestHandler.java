package com.dollars.main.netty.handler;

import com.dollars.main.entity.User;
import com.dollars.main.netty.protocol.message.request.RegistryRequestMessage;
import com.dollars.main.netty.protocol.message.response.ErrorResponseMessage;
import com.dollars.main.service.SessionService;
import com.dollars.main.service.UserService;
import com.dollars.main.service.impl.SessionServiceImpl;
import com.dollars.main.service.impl.UserServiceImpl;
import com.dollars.main.util.SpringBeanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistryRequestHandler extends SimpleChannelInboundHandler<RegistryRequestMessage> {

    private final SessionService sessionService = SpringBeanUtil.getBean(SessionServiceImpl.class);

    private final UserService userService = SpringBeanUtil.getBean(UserServiceImpl.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegistryRequestMessage msg) throws Exception {
        Long userId = msg.getUserId();
        User user = userService.getById(userId);
        if (user == null){
            ctx.writeAndFlush(new ErrorResponseMessage("用户不存在"));
            return;
        }
        Channel channel = ctx.channel();
        sessionService.bindChannel(user, channel);
        log.info("Channel注册成功 " + "[userID:" + userId + ", channelID:"+ channel.id() + "]");
    }
}
