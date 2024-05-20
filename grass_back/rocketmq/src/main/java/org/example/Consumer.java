package org.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // Initialize Consumer and set Consumer Goup Name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Set the address of NameServer
        consumer.setNamesrvAddr("localhost:9876");
        // Subscribe One or more of topics，and specify the tag filtering conditions, here specify * means receive all tag messages
        consumer.subscribe("test", "*");
        // Register a callback interface to handle messages received from the Broker
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // Return to the message consumption status, ConsumeConcurrentlyStatus.CONSUME_SUCCESS for successful consumption
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // Start Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}