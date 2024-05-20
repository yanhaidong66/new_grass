package top.haidong556.chat_server.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.chat_server.config.MySqlSessionFactory;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.mapper.MessageMapper;

import java.util.List;

public class MessageRepository {
    private MySqlSessionFactory sqlSessionFactory;
    private static MessageRepository instance;
    static {
        instance=new MessageRepository();
        instance.sqlSessionFactory=MySqlSessionFactory.getInstance();
    }

    public static MessageRepository getInstance(){
        return instance;
    }
    private MessageRepository(){}
    public List<Message> getAllMessagesBySenderId(long senderId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Message> messages = session.getMapper(MessageMapper.class).getMessagesBySenderId(senderId);
        session.commit();
        session.close();
        return messages;
    }
    public List<Message> getAllMessagesByReceiverId(long receiverId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Message> messages = session.getMapper(MessageMapper.class).getMessagesByReceiverId(receiverId);
        session.commit();
        session.close();
        return messages;
    }
    public boolean addMessage(Message message){
        SqlSession session = sqlSessionFactory.getSession(false);
        session.getMapper(MessageMapper.class).addMessage(message);
        session.commit();
        session.close();
        return true;
    }
    public List<Message> getMessagesByConversationId(long conversationId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Message> messages = session.getMapper(MessageMapper.class).getMessagesByConversationId(conversationId);
        session.commit();
        session.close();
        return messages;
    }
}
