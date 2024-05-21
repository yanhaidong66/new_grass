package top.haidong556.chat_server.mapper;

import top.haidong556.chat_server.entity.Conversation;

import java.util.List;

public interface ConversationMapper {
    public void addConversation(Conversation conversation);
    public List<Conversation> getConversationsBySenderId(long senderId);
    public List<Conversation> getConversationsByReceiverId(long receiverId);

    public Conversation getConversationById(long conversationId);
}
