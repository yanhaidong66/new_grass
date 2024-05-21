package top.haidong556.chat_server.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.mapper.ConversationMapper;

import java.util.List;

public interface ConversationService {
    public void addConversation(Conversation conversation);
    public List<Conversation> getConversationsBySenderId(long senderId);
    public List<Conversation> getConversationsByReceiverId(long receiverId);
    public Conversation getConversationsById(@Param("conversationId") long conversationId);
}
