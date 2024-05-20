package top.haidong556.chat_server.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Conversation;

import java.util.Arrays;

class ConversationRepositoryTest {
    private static ConversationRepository conversationRepository;
    @BeforeAll
    static void init(){
        conversationRepository=ConversationRepository.getInstance();
    }

    @Test
    void testAddConversation() {
        Conversation c=new Conversation(1,1,System.currentTimeMillis(),System.currentTimeMillis()+11);
        conversationRepository.addConversation(c);
    }

    @Test
    void getConversationsBySenderId() {
        System.out.println(conversationRepository.getConversationsBySenderId(1));
    }


    @Test
    void testGetConversationsByReceiverId() {
        System.out.println(conversationRepository.getConversationsByReceiverId(1));
    }
}