package top.haidong556.chat_server.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import top.haidong556.chat_server.common.proto.networkPackageProto.PackageProto;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.util.ArrayList;
import java.util.List;

public class PackageCodec {
    private PackageCodec(){}

    public static byte[] encode(MessagesPackage p) {
        Builder builder=new Builder();
        PackageProto.Package packageProto = builder.setMessages(p.getMessages())
                .setTimestamp(p.getTimestamp())
                .setType(p.getType())
                .setMessageNum(p.getMessageNum())
                .buildPackageProto();

        return packageProto.toByteArray();

    }
    public static MessagesPackage decode(byte[] body) throws InvalidProtocolBufferException {
        PackageProto.Package packageProto = PackageProto.Package.parseFrom(body);
        Builder builder=new Builder();
        List<Message> messages=new ArrayList<>();
        List<PackageProto.Message> messageProtoList = packageProto.getMessageList();
        for(PackageProto.Message m:messageProtoList){
            Message message=new Message();
            message.setMessageContent(m.getMessageContext());
            message.setMessageConversationId(m.getMessageConversationId());
            message.setMessageCreateTime(m.getMessageCreateTime());
            message.setMessageRead(m.getMessageRead());
            message.setMessageReceiverId(m.getMessageReceiverId());
            message.setMessageSenderId(m.getMessageSenderId());
            message.setMessageId(m.getMessageId());
            messages.add(message);
        }
        MessagesPackage messagesPackage = builder
                .setMessageNum(packageProto.getMessageNum())
                .setType(packageProto.getType())
                .setTimestamp(packageProto.getTimestamp())
                .setMessages(messages)
                .buildMessagesPackage();
        return messagesPackage;

    }

    private static PackageProto.Package createPackageProto(Builder b){

        PackageProto.Package.Builder packageBuilder= PackageProto.Package.newBuilder();
        packageBuilder.setType(b.type);
        packageBuilder.setMessageNum(b.messageNum);
        packageBuilder.setTimestamp(b.timestamp);

        int messageNum=b.messageNum;
        List<Message> messages=b.messages;
        List<PackageProto.Message> messageProtoList=new ArrayList<>();

        for (Message m : messages) {
            PackageProto.Message.Builder messageBuilder = PackageProto.Message.newBuilder();
            messageBuilder.setMessageId(m.getMessageId())
                    .setMessageCreateTime(m.getMessageCreateTime())
                    .setMessageContext(m.getMessageContent())
                    .setMessageReceiverId(m.getMessageReceiverId())
                    .setMessageSenderId(m.getMessageSenderId())
                    .setMessageConversationId(m.getMessageConversationId())
                    .setMessageRead(m.isMessageRead());

            PackageProto.Message message=messageBuilder.build();
            messageProtoList.add(message);
        }

        packageBuilder.addAllMessage(messageProtoList);

        return packageBuilder.build();
    }

    private static class Builder{
        private List<Message> messages;
        private int messageNum;
        private long timestamp;
        private String type;

        public Builder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }
        public Builder setMessageNum(int messageNum) {
            this.messageNum = messageNum;
            return this;
        }
        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public PackageProto.Package buildPackageProto(){
            return PackageCodec.createPackageProto(this);
        }
        public MessagesPackage buildMessagesPackage(){
            MessagesPackage messagesPackage=new MessagesPackage();
            messagesPackage.setTimestamp(this.timestamp);
            messagesPackage.setMessages(this.messages);
            messagesPackage.setType(this.type);
            messagesPackage.setMessageNum(this.messageNum);
            return messagesPackage;
        }

    }
}
