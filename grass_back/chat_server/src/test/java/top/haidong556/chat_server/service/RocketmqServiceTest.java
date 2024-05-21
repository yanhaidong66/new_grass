package top.haidong556.chat_server.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.config.MyConfiguration;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RocketmqServiceTest {
    static RocketmqService rocketmqService=RocketmqService.getInstance();
    private static MessagesPackage messagesPackage;
    @BeforeAll
    static void  init(){
        List<Message> messageList=new ArrayList<Message>();
        Message m1=new Message(2,2,3,3,3,34,43,"34",true);
        Message m2=new Message(2,2,3,3,3,34,43,"34",true);
        messageList.add(m1);
        messageList.add(m2);
        messagesPackage=new MessagesPackage();
        messagesPackage.setMessageNum(2);
        messagesPackage.setMessages(messageList);
        messagesPackage.setType("message");
        messagesPackage.setTimestamp(System.currentTimeMillis());
    }

    @Test
    void getInstance() {
    }

    @Test
    void run() {
    }

    @Test
    void sendMySQLWriteFailureMessage() throws InterruptedException {
        Thread thread=new Thread(rocketmqService);
        thread.start();
        thread.join();
        rocketmqService.sendMySQLWriteFailureMessage("aaaaa");
    }

    @Test
    void sendChatMessage() throws InterruptedException {
        Thread thread=new Thread(rocketmqService);
        thread.start();
        thread.join();

        rocketmqService.sendChatMessage(messagesPackage, MyConfiguration.ROCKETMQ_CHAT_MESSAGE_MYSELF_TAG);
    }
}