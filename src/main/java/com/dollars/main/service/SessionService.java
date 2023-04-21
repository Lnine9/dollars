package com.dollars.main.service;

import com.dollars.main.entity.User;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

public interface SessionService {
    void bindChannel(User user, Channel channel);

    void unbind(Channel channel);

    boolean hasLogin(Channel channel);

    User getUser(Channel channel);

    Channel getChannel(Long userId);

    void bindChannelGroup(Long groupId, ChannelGroup channelGroup);

    ChannelGroup getChannelGroup(Long groupId);
}
