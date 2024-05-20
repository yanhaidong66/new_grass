package top.haidong556.chat_server.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.service.ConversationService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ConversationServiceImplTest {

    @Test
    public void testAddConversation() {
        ConversationService conversationService = ConversationServiceImpl.getInstance();
        Conversation conversation = new Conversation(1, 100, 200, System.currentTimeMillis(), System.currentTimeMillis());
        conversationService.addConversation(conversation);
    }

    @Test
    public void testGetConversationsBySenderId() {
        ConversationService conversationService = ConversationServiceImpl.getInstance();
        long senderId = 100;
        System.out.println(conversationService.getConversationsBySenderId(senderId));
    }

    @Test
    public void testGetConversationsByReceiverId() {
        ConversationService conversationService = ConversationServiceImpl.getInstance();
        long receiverId = 200;
        System.out.println(conversationService.getConversationsByReceiverId(receiverId));
    }
}
