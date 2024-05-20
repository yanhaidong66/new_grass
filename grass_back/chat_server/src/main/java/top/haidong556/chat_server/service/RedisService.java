package top.haidong556.chat_server.service;

import redis.clients.jedis.Jedis;

import top.haidong556.chat_server.common.chat.chat_server.config.JedisPool;
import top.haidong556.chat_server.entity.Message;

public class RedisService {

    private final static JedisPool jedisPool=JedisPool.getInstance();
    private final static RedisService instance=new RedisService();

    private RedisService(){}
    public static RedisService getInstance(){
        return instance;
    }

    public void writeMessage(Message message) throws Exception {

        Jedis jedis = jedisPool.getJedis();
        String json = message.toJson();
        jedis.set("message"+message.getMessageId(),json);
        jedis.close();
    }
    public Message getMessage(Long messageId) throws Exception {
        Jedis jedis = jedisPool.getJedis();
        String json = jedis.get("message" + messageId);
        Message message = Message.fromJson(json);
        jedis.close();
        return message;
    }


}
