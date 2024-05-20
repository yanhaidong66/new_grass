package org.example;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("localhost:9876");
        producer.setSendMsgTimeout(10000); // Set the timeout to 10 seconds
        producer.setRetryTimesWhenSendFailed(3); // Set the retry times to 3
        producer.start();

        while(true) {
            try {
                Message msg = new Message("test", "TagA", ("Hello RocketMQ " ).getBytes("UTF-8"));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

    }
}
