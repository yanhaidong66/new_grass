package top.haidong556.chat_server.repository;

import org.springframework.stereotype.Repository;
import top.haidong556.chat_server.config.JedisPool;
import top.haidong556.chat_server.entity.UserServerMapping;
@Repository
public class MappingRepository {
    JedisPool jedisPool=JedisPool.getInstance();



    public int addMapping(UserServerMapping mapping){

        int effectRows=
    }

    public UserServerMapping getMappingByUserId(long userId){

    }
    public UserServerMapping getMappingByUserName(String userName){

    }
}
