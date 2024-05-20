package top.haidong556.chat_server.service;

import io.netty.channel.Channel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import top.haidong556.chat_server.common.GlobalContext;
import top.haidong556.chat_server.common.codec.MessageCodec;
import top.haidong556.chat_server.common.codec.PackageCodec;
import top.haidong556.chat_server.config.MyConfiguration;

import java.util.List;

public class RocketmqService implements Runnable{
    private GlobalContext globalContext=GlobalContext.getInstance();
    private DefaultMQProducer producer;
    private DefaultMQPushConsumer consumer;
    private static final RocketmqService instance=new RocketmqService();
    private RocketmqService(){
        producer = new DefaultMQProducer(MyConfiguration.ROCKETMQ_PRODUCER_GROUP);
        producer.setNamesrvAddr(MyConfiguration.ROCKETMQ_NAMESRV_ADDR);


        consumer = new DefaultMQPushConsumer(MyConfiguration.ROCKETMQ_CONSUMER_GROUP);
        consumer.setNamesrvAddr(MyConfiguration.ROCKETMQ_NAMESRV_ADDR);

        try {
            consumer.subscribe(MyConfiguration.ROCKETMQ_TOPIC, MyConfiguration.ROCKETMQ_CHAT_MESSAGE_TAG+MyConfiguration.MYSELF_CHAT_SERVER_ID);
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String s = new String(msg.getBody());
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), );
                    Channel channel = globalContext.getUserChannelMap().get();
                    channel.writeAndFlush();
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

    }
    public static RocketmqService getInstance(){
        return instance;
    }

    @Override
    public void run() {

        try {
            consumer.start();
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Consumer Started.%n");
        System.out.println("Producer Started.");
    }


    // Send MySQL write failure message
    public void sendMySQLWriteFailureMessage(String errorMessage) {
        Message message = new Message("MySQLWriteFailures", MyConfiguration.ROCKETMQ_MYSQL_FAIL_TAG, errorMessage.getBytes());
        try {
            SendResult sendResult = producer.send(message);
            System.out.printf("Message sent: %s%n", sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //发送聊天消息
    public void sendChatMessage(top.haidong556.chat_server.entity.Message m) {
        Message message = new Message(MyConfiguration.ROCKETMQ_TOPIC, MyConfiguration.ROCKETMQ_CHAT_MESSAGE_TAG+m.getMessageReceiverId(), MessageCodec.encode(m) );
        try {
            SendResult sendResult = producer.send(message);
            System.out.printf("Message sent: %s%n", sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
