package top.haidong556.chat_server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import top.haidong556.chat_server.config.MyConfiguration;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SendHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    private MyConfiguration config=MyConfiguration.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        if (messagesPackage.getType().equals("message")){
            List<Message> messages = messagesPackage.getMessages();
            for (Message m: messages) {
                MessagesPackage mp=new MessagesPackage();
                mp.setType("receiveMessage");
                List<Message> l=new LinkedList();
                l.add(m);
                mp.setMessages(l);
                mp.setTimestamp(System.currentTimeMillis());
                mp.setMessageNum(1);
                //sendToMessageQueue(mp);
                sendToChatServer(mp);
            }
        }
        channelHandlerContext.fireChannelRead(messagesPackage);

    }

    private void sendToMessageQueue(MessagesPackage mp){

    }
    private void sendToChatServer(MessagesPackage mp){
        Message m=mp.getMessages().get(0);
        int messageReceiverChatServerId = m.getMessageReceiverChatServerId();
        if(messageReceiverChatServerId==config.MYSELF_CHAT_SERVER_ID){
            long messageReceiverId = m.getMessageReceiverId();
            //Channel channel = userChannelMap.get(messageReceiverId);
            //channel.writeAndFlush(mp);
        }else{
            //发送给消息队列
        }

    }
}
