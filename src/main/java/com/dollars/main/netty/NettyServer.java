package com.dollars.main.netty;

import com.dollars.main.netty.handler.*;
import com.dollars.main.netty.protocol.codec.WebSocketFrameMessageCodec;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyServer implements InitializingBean {

    @Value("${netty.port}")
    private int port;

    @Override
    public void afterPropertiesSet() throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new HeartBeatHandler()) //心跳处理
                                .addLast(new HttpServerCodec()) //http编解码
                                .addLast(new HttpObjectAggregator(10000000)) //http消息聚合
                                .addLast(new HttpContentCompressor()) // Http压缩
                                .addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new HttpRequestHandler())// 处理握手事件
                                .addLast(new WebSocketFrameMessageCodec()) // 自定义协议编码解码器
                                .addLast(new HeartBeatRequestHandler()) // 心跳回应处理
                                .addLast(new ChatSendHandler()) // 消息发送处理
                                .addLast(new RegistryRequestHandler()) //注册Channel处理
                                .addLast(new ExceptionHandler()); // 错误处理
                    }
                })
                .bind(port);
    }
}
