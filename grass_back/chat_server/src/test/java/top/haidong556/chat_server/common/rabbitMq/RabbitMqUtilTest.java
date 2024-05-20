package top.haidong556.chat_server.common.rabbitMq;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

class RabbitMqUtilTest {

    @Test
    void getConnection() throws Exception {
        RabbitMqUtil.getConnection();
        sleep(100000);
    }

    @Test
    void send() throws Exception {
        Channel channel = RabbitMqUtil.getChannel();


        RabbitMqUtil.send("haidong","test");
    }
}