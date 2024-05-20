package top.haidong556.chat_server.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import top.haidong556.chat_server.common.proto.messageProto.MessageProto;
import top.haidong556.chat_server.entity.Message;

public class MessageCodec {
    private MessageCodec(){}
    public static Message decode(byte[] body) throws InvalidProtocolBufferException {
        MessageProto.Message messageProto = MessageProto.Message.parseFrom(body);
        Builder builder=new Builder();
        Message message = builder.setMessageContent(messageProto.getMessageContext())
                .setMessageConversationId(messageProto.getMessageConversationId())
                .setMessageRead(messageProto.getMessageRead())
                .setMessageId(messageProto.getMessageId())
                .setMessageCreateTime(messageProto.getMessageCreateTime())
                .setMessageReceiverId(messageProto.getMessageReceiverId())
                .setMessageSenderId(messageProto.getMessageSenderId())
                .buildMessage();
        return message;

    }

    public static byte[] encode(Message message){
        Builder builder=new Builder();
        MessageProto.Message messageProto = builder.setMessageSenderId(message.getMessageSenderId())
                .setMessageContent(message.getMessageContent())
                .setMessageReceiverId(message.getMessageReceiverId())
                .setMessageCreateTime(message.getMessageCreateTime())
                .setMessageId(message.getMessageId())
                .setMessageRead(message.isMessageRead())
                .setMessageConversationId(message.getMessageConversationId())
                .buildMessageProto();
        return messageProto.toByteArray();
    }

    private static class Builder{
        private long messageId;
        private long messageConversationId;
        private long messageCreateTime;
        private long messageSenderId;
        private long messageReceiverId;
        private String messageContent;
        private boolean messageRead;

        public MessageProto.Message buildMessageProto(){
            MessageProto.Message.Builder protoBuilder = MessageProto.Message.newBuilder();
            MessageProto.Message proto = protoBuilder.setMessageContext(messageContent)
                    .setMessageConversationId(messageConversationId)
                    .setMessageRead(messageRead)
                    .setMessageSenderId(messageSenderId)
                    .setMessageReceiverId(messageReceiverId)
                    .setMessageConversationId(messageConversationId)
                    .build();
            return proto;
        }
        public Message buildMessage(){
            Message message=new Message();
            message.setMessageRead(messageRead);
            message.setMessageSenderId(messageSenderId);
            message.setMessageConversationId(messageConversationId);
            message.setMessageContent(messageContent);
            message.setMessageCreateTime(messageCreateTime);
            message.setMessageId(messageId);
            message.setMessageReceiverId(messageReceiverId);
            return message;
        }
        public Builder setMessageContent(String messageContext) {
            this.messageContent = messageContext;
            return this;
        }

        public Builder setMessageRead(boolean messageRead) {
            this.messageRead = messageRead;
            return this;
        }

        public Builder setMessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setMessageConversationId(long messageConversationId) {
            this.messageConversationId = messageConversationId;
            return this;
        }

        public Builder setMessageCreateTime(long messageCreateTime) {
            this.messageCreateTime = messageCreateTime;
            return this;
        }

        public Builder setMessageSenderId(long messageSenderId) {
            this.messageSenderId = messageSenderId;
            return this;
        }

        public Builder setMessageReceiverId(long messageReceiverId) {
            this.messageReceiverId = messageReceiverId;
            return this;
        }


    }
}
