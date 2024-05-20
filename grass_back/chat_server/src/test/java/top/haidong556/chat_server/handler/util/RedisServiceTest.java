package top.haidong556.chat_server.handler.util;

import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.service.RedisService;

class RedisServiceTest {

    static RedisService redisService = RedisService.getInstance();
    @Test
    void getInstance() {
    }

    @Test
    void writeMessage() throws Exception {
        redisService.writeMessage(new Message(2,2,3,3,3,34,43,"34",true));

    }

}