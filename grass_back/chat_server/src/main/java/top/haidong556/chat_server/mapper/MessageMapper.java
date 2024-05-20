package top.haidong556.chat_server.mapper;

import top.haidong556.chat_server.entity.Message;

import java.util.List;

public interface MessageMapper {
public Message getMessageById();
public void addMessage(Message message);
public List<Message> getMessagesByConversationId(long conversationId);
public List<Message> getMessagesBySenderId(long senderId);
public List<Message> getMessagesByReceiverId(long receiverId);

}
