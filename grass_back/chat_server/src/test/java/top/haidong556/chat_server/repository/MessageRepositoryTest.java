package top.haidong556.chat_server.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Message;

import java.util.List;

class MessageRepositoryTest {
    private static MessageRepository messageRepository;
    @BeforeAll
    static void init(){
        messageRepository=MessageRepository.getInstance();
    }


    @Test
    void testGetAllMessagesBySenderId() {
        List<Message> messageList=messageRepository.getAllMessagesBySenderId(1);
        System.out.println(messageList);
    }

    @Test
    void testAddMessage() {
        Message m=new Message(2,2,3,3,3,34,43,"34",true);
        messageRepository.addMessage(m);
    }

    @Test
    void getMessagesByConversationId() {
        List<Message> messageList=messageRepository.getMessagesByConversationId(1);
        System.out.println(messageList);

    }
}