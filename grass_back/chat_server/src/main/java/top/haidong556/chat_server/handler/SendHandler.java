package top.haidong556.chat_server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import top.haidong556.chat_server.common.GlobalContext;
import top.haidong556.chat_server.config.MyConfiguration;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;
import top.haidong556.chat_server.service.ConsistentHashLoadBalancerService;
import top.haidong556.chat_server.service.RocketmqService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SendHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    private RocketmqService rocketmqService=RocketmqService.getInstance();
    private MyConfiguration config=MyConfiguration.getInstance();
    private GlobalContext globalContext=GlobalContext.getInstance();
    private ConsistentHashLoadBalancerService consistentHashLoadBalancerService=ConsistentHashLoadBalancerService.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        if (messagesPackage.getType().equals("message")){
            List<Message> messages = messagesPackage.getMessages();
            Map<Long,List<Message>> map=new HashMap<>();
            for (Message m: messages) {
                long messageReceiverId = m.getMessageReceiverId();
                if (map.containsKey(messageReceiverId)==true){
                    map.get(messageReceiverId).add(m);
                }else{
                    List<Message> l=new LinkedList<>();
                    l.add(m);
                    map.put(messageReceiverId,l);
                }
            }
            for(Map.Entry<Long, List<Message>> entry : map.entrySet()){
                Long receiverId = entry.getKey();
                List<Message> messageList = entry.getValue();
                MessagesPackage mp=new MessagesPackage();
                mp.setType("receiveMessage");
                mp.setMessages(messageList);
                mp.setTimestamp(System.currentTimeMillis());
                mp.setMessageNum(messageList.size());
                ConcurrentHashMap<Long, Channel> userChannelMap = globalContext.getUserChannelMap();
                if(userChannelMap.containsKey(receiverId)==true){
                    sendToUserByMyself(mp,receiverId);
                }else{
                    sendToMessageQueue(mp,receiverId);
                }
            }

        }
        channelHandlerContext.fireChannelRead(messagesPackage);

    }

    private void sendToMessageQueue(MessagesPackage mp,long receiverId){
        String topicTag = consistentHashLoadBalancerService.getNode(receiverId);
        rocketmqService.sendChatMessage(mp,topicTag);
    }
    private void sendToUserByMyself(MessagesPackage mp,long receiverId){
        Channel channel = globalContext.getUserChannelMap().get(receiverId);
        channel.writeAndFlush(mp);
    }
}
