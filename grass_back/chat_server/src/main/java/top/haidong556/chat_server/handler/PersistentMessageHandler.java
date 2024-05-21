package top.haidong556.chat_server.handler;

import io.lettuce.core.RedisException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.haidong556.chat_server.common.UniqueIDGenerator;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;
import top.haidong556.chat_server.repository.ConversationRepository;
import top.haidong556.chat_server.repository.MessageRepository;
import top.haidong556.chat_server.service.MessageService;
import top.haidong556.chat_server.service.RedisService;
import top.haidong556.chat_server.service.RocketmqService;
import top.haidong556.chat_server.service.impl.MessageServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class PersistentMessageHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    MessageService messageService= MessageServiceImpl.getInstance();
    RedisService redisService=RedisService.getInstance();
    RocketmqService rocketmqService=RocketmqService.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        List<Message> messages = messagesPackage.getMessages();
        for(Message m:messages){
            m.setMessageId(UniqueIDGenerator.generateMessageId(m.getMessageSenderId()));
            try{
                redisService.writeMessage(m);
                messageService.addMessage(m);
            }catch (Exception e){
                if (e instanceof RedisException) {
                    // Redis报错，直接返回错误
                    System.err.println("Redis error: " + e.getMessage());
                    return; // 或者根据你的需求处理错误，例如记录日志或者返回特定的错误码
                }
                if (e instanceof SQLException) {
                    // MySQL报错，给消息队列发送
                    rocketmqService.sendMySQLWriteFailureMessage(e.toString());
                } else {
                    // 其他异常，记录日志或采取适当措施
                    System.err.println("Unknown error: " + e.getMessage());
                }

            }

        }
        channelHandlerContext.fireChannelRead(messagesPackage);
    }
}
