package top.haidong556.chat_server.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MySqlSessionFactory {
    private static final MySqlSessionFactory instance=new MySqlSessionFactory();
    private static SqlSessionFactory sqlSessionFactory;
    private static String configPath="mybatis-config.xml";

    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }
    private MySqlSessionFactory(){};
    public static MySqlSessionFactory getInstance(){
        return instance;
    }

    public SqlSession getSession(boolean b){
        return sqlSessionFactory.openSession(b);
    }


}
