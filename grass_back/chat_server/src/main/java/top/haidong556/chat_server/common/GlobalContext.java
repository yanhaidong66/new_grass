package top.haidong556.chat_server.common;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class GlobalContext {
    private static final GlobalContext instance=new GlobalContext();
    private static final ConcurrentHashMap<Long, Channel> userChannelMap=new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Channel,Long> channelUserMap=new ConcurrentHashMap<>();

    private GlobalContext(){}
    public static GlobalContext getInstance(){
        return instance;
    }

    // 获取 userChannelMap
    public ConcurrentHashMap<Long, Channel> getUserChannelMap() {
        return userChannelMap;
    }
    public ConcurrentHashMap<Channel,Long> getChannelUserMap(){
        return channelUserMap;
    }


}
