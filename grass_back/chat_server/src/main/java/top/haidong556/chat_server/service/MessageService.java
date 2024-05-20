package top.haidong556.chat_server.service;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.mapper.MessageMapper;

import java.util.List;

public interface MessageService {

    public List<Message> getAllMessagesBySenderId(long senderId);
    public List<Message> getAllMessagesByReceiverId(long receiverId);
    public void addMessage(Message message);
    public List<Message> getMessagesByConversationId(long conversationId);
}
