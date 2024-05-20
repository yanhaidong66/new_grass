package top.haidong556.chat_server.service.impl;

import org.springframework.stereotype.Service;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.repository.MessageRepository;
import top.haidong556.chat_server.service.MessageService;

import java.util.List;
public class MessageServiceImpl implements MessageService {

    public static final MessageServiceImpl instance=new MessageServiceImpl();
    private MessageRepository messageRepository=MessageRepository.getInstance();

    private MessageServiceImpl(){};


    public static MessageService getInstance() {
        return instance;
    }

    @Override
    public List<Message> getAllMessagesBySenderId(long senderId) {
        return messageRepository.getAllMessagesBySenderId(senderId);
    }

    @Override
    public List<Message> getAllMessagesByReceiverId(long receiverId) {
        return messageRepository.getAllMessagesByReceiverId(receiverId);
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.addMessage(message);
    }

    @Override
    public List<Message> getMessagesByConversationId(long conversationId) {
        return messageRepository.getMessagesByConversationId(conversationId);
    }
}
