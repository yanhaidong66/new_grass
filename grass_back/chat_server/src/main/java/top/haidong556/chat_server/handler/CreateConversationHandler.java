package top.haidong556.chat_server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import top.haidong556.chat_server.common.UniqueIDGenerator;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;
import top.haidong556.chat_server.service.ConversationService;
import top.haidong556.chat_server.service.impl.ConversationServiceImpl;

import java.util.List;

public class CreateConversationHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    private ConversationService conversationService=ConversationServiceImpl.getInstance();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        List<Message> messages = messagesPackage.getMessages();
        if(messagesPackage.getType().equals("message")){
            for(Message message:messages){
                long messageConversationId = message.getMessageConversationId();
                if(messageConversationId==0||conversationService.getConversationsById(messageConversationId)==null){
                    long messageSenderId = message.getMessageSenderId();
                    long messageReceiverId = message.getMessageReceiverId();
                    long time = System.currentTimeMillis();
                    Conversation conversation=new Conversation(messageSenderId,messageReceiverId,time,time);
                    conversation.setConversationId(UniqueIDGenerator.generateConversationId(messageSenderId));
                    conversationService.addConversation(conversation);
                    System.out.println("create conversation id:"+conversation.getConversationId());
                    message.setMessageConversationId(conversation.getConversationId());
                }

            }

        }

        channelHandlerContext.fireChannelRead(messagesPackage);
    }
}
