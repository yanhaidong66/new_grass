//package top.haidong556.chat_server.repository;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import top.haidong556.chat_server.entity.Message;
//
//class RedisRepositoryTest {
//    static Message message;
//    @BeforeAll
//    static void init(){
//        Message m=new Message();
//        m.setMessageReceiverId(111);
//        m.setMessageCreateTime(System.currentTimeMillis());
//        m.setMessageContent("haidong");
//        m.setMessageSenderId(22);
//        m.setMessageRead(false);
//        m.setMessageConversationId(12);
//        m.setMessageId(23);
//        message=m;
//    }
//
//    @Test
//    void testWrite() throws JsonProcessingException {
//        RedisRepository.writeMessage(message);
//
//    }
//    @Test
//    void testGet() throws JsonProcessingException {
//        System.out.println(RedisRepository.getMessage(23));
//    }
//}