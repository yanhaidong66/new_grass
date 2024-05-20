package top.haidong556.chat_server.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.chat_server.config.MySqlSessionFactory;
import top.haidong556.chat_server.entity.Conversation;
import top.haidong556.chat_server.mapper.ConversationMapper;

import java.util.List;

public class ConversationRepository {
    private MySqlSessionFactory sqlSessionFactory;
    private static ConversationRepository instance;
    static {
        instance=new ConversationRepository();
        instance.sqlSessionFactory=MySqlSessionFactory.getInstance();
    }
    private ConversationRepository(){}

    public static ConversationRepository getInstance(){
        return instance;
    }

    public boolean addConversation(Conversation conversation){
        SqlSession session = sqlSessionFactory.getSession(false);
        session.getMapper(ConversationMapper.class).addConversation(conversation);
        session.commit();
        return true;
    }
    public List<Conversation> getConversationsBySenderId(long senderId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Conversation> conversations = session.getMapper(ConversationMapper.class).getConversationsBySenderId(senderId);
        session.commit();
        session.close();
        return conversations;
    }
    public List<Conversation> getConversationsByReceiverId(long receiverId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Conversation> conversations = session.getMapper(ConversationMapper.class).getConversationsByReceiverId(receiverId);
        session.commit();
        session.close();
        return conversations;
    }

}
