package top.haidong556.chat_server.service.impl;

import org.springframework.stereotype.Service;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.repository.ConversationRepository;
import top.haidong556.chat_server.service.ConversationService;

import java.util.List;
public class ConversationServiceImpl implements ConversationService {

    static {
        instance=new ConversationServiceImpl();
        conversationRepository=ConversationRepository.getInstance();
    }
    private static final ConversationServiceImpl instance;
    private static final ConversationRepository conversationRepository;
    private ConversationServiceImpl(){}

    public static ConversationService getInstance(){
        return instance;
    }
    @Override
    public void addConversation(Conversation conversation) {
        conversationRepository.addConversation(conversation);

    }

    @Override
    public List<Conversation> getConversationsBySenderId(long senderId) {
        return conversationRepository.getConversationsBySenderId(senderId);
    }

    @Override
    public List<Conversation> getConversationsByReceiverId(long receiverId) {
        return conversationRepository.getConversationsByReceiverId(receiverId);
    }
}
