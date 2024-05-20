package top.haidong556.chat.gateway.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import top.haidong556.chat.gateway.entity.UserServerMapping;
import top.haidong556.chat.gateway.mapper.MappingMapper;
import top.haidong556.chat_server.common.util.MySqlSessionFactory;

@Repository
public class MappingRepository {

    private SqlSessionFactory sqlSessionFactory= MySqlSessionFactory.getFactory();

    public int addMapping(UserServerMapping mapping){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        int effectRows = sqlSession.getMapper(MappingMapper.class).addUserServerMapping(mapping);
        sqlSession.commit();
        sqlSession.close();
        return effectRows;
    }

    public UserServerMapping getMappingByUserId(long userId){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        UserServerMapping mappingByUserId = sqlSession.getMapper(MappingMapper.class).getMappingByUserId(userId);
        sqlSession.commit();
        sqlSession.close();
        return mappingByUserId;
    }
    public UserServerMapping getMappingByUserName(String userName){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        UserServerMapping mappingByUserId = sqlSession.getMapper(MappingMapper.class).getMappingByUserName(userName);
        sqlSession.commit();
        sqlSession.close();
        return mappingByUserId;
    }
    public int countUsersUsingChatServer(int serverId){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        int countUsersUsingChatServer = sqlSession.getMapper(MappingMapper.class).countUsersUsingChatServer(serverId);
        sqlSession.commit();
        sqlSession.close();
        return countUsersUsingChatServer;
    }
}