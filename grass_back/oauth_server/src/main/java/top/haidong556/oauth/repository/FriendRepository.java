package top.haidong556.oauth.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import top.haidong556.chat_server.common.util.MySqlSessionFactory;
import top.haidong556.oauth.entity.Friend;
import top.haidong556.oauth.mapper.FriendMapper;

import java.util.List;

@Repository
public class FriendRepository {
    private SqlSessionFactory sqlSessionFactory= MySqlSessionFactory.getFactory();
    public Friend getFriend(Friend friend)throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            FriendMapper friendMapper = sqlSession.getMapper(FriendMapper.class);
            Friend mapperFriend = friendMapper.getFriend(friend);
            sqlSession.commit();
            return mapperFriend;
        }
    }

    public int addFriend(Friend friend) throws Exception{
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            FriendMapper friendMapper = sqlSession.getMapper(FriendMapper.class);
            int effectRows = friendMapper.addFriend(friend);
            sqlSession.commit();
            return effectRows;
        }
    }

    public int deleteFriend(Friend friend)throws Exception{
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            FriendMapper friendMapper = sqlSession.getMapper(FriendMapper.class);
            int effectRows = friendMapper.deleteFriend(friend);
            sqlSession.commit();
            return effectRows;
        }
    }

    public List<Friend> getAllFriendsByUserId(long userId)throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            FriendMapper friendMapper = sqlSession.getMapper(FriendMapper.class);
            return friendMapper.getAllFriendsByUserId(userId);
        }
    }


}
