package top.haidong556.oauth.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import top.haidong556.chat_server.common.util.MySqlSessionFactory;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.UserMapper;


@Repository
public class UserRepository {

    private SqlSessionFactory sqlSessionFactory= MySqlSessionFactory.getFactory();
    public UserRepository(){}
    public MyUser getUserByUserName(String userName) throws Exception {
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        MyUser user= sqlSession.getMapper(UserMapper.class).getUserByUserName(userName);
        sqlSession.commit();
        sqlSession.close();
        return user;
    };
    public int addUser(MyUser user)throws Exception{
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        int effectRows=sqlSession.getMapper(UserMapper.class).addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return effectRows;
    };

    public int deleteUser(String userName)throws Exception{
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int effectRows=sqlSession.getMapper(UserMapper.class).deleteUser(userName);
        sqlSession.commit();
        sqlSession.close();
        return effectRows;
    }

    public int updatePasswordByUserName(String userName,String password)throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int effectRows=sqlSession.getMapper(UserMapper.class).updatePasswordByUserName(userName,password);
        sqlSession.commit();
        sqlSession.close();
        return effectRows;
    }
    public MyUser getUserById(int id)throws Exception{
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        MyUser user=sqlSession.getMapper(UserMapper.class).getUserById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
    public MyUser getUserByEmail(String email)throws Exception{
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        MyUser user=sqlSession.getMapper(UserMapper.class).getUserByEmail(email);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
}
