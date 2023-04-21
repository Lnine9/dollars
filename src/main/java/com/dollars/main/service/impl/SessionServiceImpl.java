package com.dollars.main.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dollars.main.entity.User;
import com.dollars.main.service.SessionService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Service;

interface Attributes{
    AttributeKey<User> SESSION = AttributeKey.newInstance("session");
}

@Service
public class SessionServiceImpl implements SessionService {
    /**
     * userID 映射 连接channel
     */
    private final Map<Long, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /**
     * groupId ---> channelgroup 群聊ID和群聊ChannelGroup映射
     */
    private final Map<Long, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    @Override
    public void bindChannel(User user, Channel channel) {
        userIdChannelMap.put(user.getId(), channel);
        channel.attr(Attributes.SESSION).set(user);
    }

    @Override
    public void unbind(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getUser(channel).getId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    @Override
    public boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    @Override
    public User getUser(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    @Override
    public Channel getChannel(Long userId) {
        return userIdChannelMap.get(userId);
    }
    /**
     * 绑定群聊Id  群聊channelGroup
     */
    @Override
    public void bindChannelGroup(Long groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    @Override
    public ChannelGroup getChannelGroup(Long groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
