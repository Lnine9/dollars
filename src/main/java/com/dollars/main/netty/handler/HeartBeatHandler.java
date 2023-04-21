package com.dollars.main.netty.handler;


import java.util.concurrent.TimeUnit;

import com.dollars.main.service.SessionService;
import com.dollars.main.service.impl.SessionServiceImpl;
import com.dollars.main.util.SpringBeanUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartBeatHandler extends IdleStateHandler{

    private static final int READER_IDLE_TIME = 20;

    private final SessionService sessionService = SpringBeanUtil.getBean(SessionServiceImpl.class);

    public HeartBeatHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info(READER_IDLE_TIME + "秒内未读到数据，关闭Channel : "+ctx.channel().id());
        sessionService.unbind(ctx.channel());
        ctx.channel().close();
    }
}
