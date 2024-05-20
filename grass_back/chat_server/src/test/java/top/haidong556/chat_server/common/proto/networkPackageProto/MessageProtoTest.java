package top.haidong556.chat_server.common.proto.networkPackageProto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MessageProtoTest {
    private static byte[] encode(PackageProto.Package p){
        return p.toByteArray();
    }
    private static PackageProto.Package decode(byte[] body) throws InvalidProtocolBufferException {
        return PackageProto.Package.parseFrom(body);
    }
    private static PackageProto.Package createPackage(){
        PackageProto.Package.Builder builder= PackageProto.Package.newBuilder();
        builder.setType("mm");
        builder.setMessageNum(2);
        builder.setTimestamp(System.currentTimeMillis());

        PackageProto.Message.Builder messageBuilder = PackageProto.Message.newBuilder();
        messageBuilder.setMessageId(1);
        messageBuilder.setMessageSenderId(22);
        messageBuilder.setMessageReceiverId(33);
        messageBuilder.setMessageConversationId(1);
        messageBuilder.setMessageContext("haidongefsadgdfslikjglkdfgmbdfhgfdghjfghfjghjghfjfdghj");
        messageBuilder.setMessageCreateTime(System.currentTimeMillis());
        PackageProto.Message m1=messageBuilder.build();

        PackageProto.Message.Builder messageBuilder1 = PackageProto.Message.newBuilder();
        messageBuilder1.setMessageId(2);
        messageBuilder1.setMessageConversationId(2);
        messageBuilder1.setMessageSenderId(22);
        messageBuilder1.setMessageReceiverId(33);
        messageBuilder1.setMessageContext("haidong2");
        messageBuilder1.setMessageCreateTime(System.currentTimeMillis());
        PackageProto.Message m2=messageBuilder1.build();
        List<PackageProto.Message> l=new ArrayList<>();
        l.add(m1);
        l.add(m2);
        builder.addAllMessage(l);

        return builder.build();
    }
    @Test
    void test() throws InvalidProtocolBufferException {
        PackageProto.Package package1=createPackage();
        System.out.println(package1.toString());
        System.out.println();
        System.out.println(decode(encode(package1)).toString());
    }

}