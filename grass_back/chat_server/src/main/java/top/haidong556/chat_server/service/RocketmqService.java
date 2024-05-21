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
import top.haidong556.chat_server.config.MyConfiguration;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
            consumer.subscribe(MyConfiguration.ROCKETMQ_TOPIC, MyConfiguration.ROCKETMQ_CHAT_MESSAGE_MYSELF_TAG);
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    MessagesPackage messagesPackage = MessagesPackage.decode(msg.getBody());
                    if (messagesPackage.getType().equals("receiveMessage")){
                        List<top.haidong556.chat_server.entity.Message> messages = messagesPackage.getMessages();
                        Map<Long,List<top.haidong556.chat_server.entity.Message>> map=new HashMap<>();
                        for (top.haidong556.chat_server.entity.Message m: messages) {
                            long messageReceiverId = m.getMessageReceiverId();
                            if (map.containsKey(messageReceiverId)==true){
                                map.get(messageReceiverId).add(m);
                            }else{
                                List<top.haidong556.chat_server.entity.Message> l=new LinkedList<>();
                                l.add(m);
                                map.put(messageReceiverId,l);
                            }
                        }
                        for(Map.Entry<Long, List<top.haidong556.chat_server.entity.Message>> entry : map.entrySet()){
                            Long receiverId = entry.getKey();
                            List<top.haidong556.chat_server.entity.Message> messageList = entry.getValue();
                            MessagesPackage mp=new MessagesPackage();
                            mp.setType("receiveMessage");
                            mp.setMessages(messageList);
                            mp.setTimestamp(System.currentTimeMillis());
                            mp.setMessageNum(messageList.size());
                            ConcurrentHashMap<Long, Channel> userChannelMap = globalContext.getUserChannelMap();
                            if(userChannelMap.containsKey(receiverId)==true){
                                sendToUserByMyself(mp,receiverId);
                            }
                            else{
                                //SendNotifications(mp,receiverId);
                            }
                        }

                    }


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
    public void sendChatMessage(MessagesPackage mp,String topicTag) {
        Message message = new Message(MyConfiguration.ROCKETMQ_TOPIC, topicTag,mp.encode());
        try {
            SendResult sendResult = producer.send(message);
            System.out.printf("Message sent: %s%n", sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToUserByMyself(MessagesPackage mp,long receiverId){
        Channel channel = globalContext.getUserChannelMap().get(receiverId);
        channel.writeAndFlush(mp.encode());
    }


}
