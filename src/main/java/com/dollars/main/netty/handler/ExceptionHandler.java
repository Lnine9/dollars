package com.dollars.main.netty.handler;

import com.dollars.main.netty.protocol.message.response.ErrorResponseMessage;
import com.dollars.main.netty.protocol.message.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Arrays;

/**
 * Netty异常处理机制
 * @author holiday
 * 2020-12-02
 */
@Sharable
@Slf4j
public class ExceptionHandler extends ChannelDuplexHandler {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("全局异常处理");
        ctx.writeAndFlush(new ErrorResponseMessage());
    }
}
