package top.haidong556.chat_server.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Message;
import top.haidong556.chat_server.entity.MessagesPackage;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class PackageCodecTest {
    private static MessagesPackage messagesPackage;
    @BeforeAll
    static void  init(){
        List<Message> messageList=new ArrayList<Message>();
        Message m1=new Message(2,2,3,3,3,34,43,"34",true);
        Message m2=new Message(2,2,3,3,3,34,43,"34",true);
        messageList.add(m1);
        messageList.add(m2);
        messagesPackage=new MessagesPackage();
        messagesPackage.setMessageNum(2);
        messagesPackage.setMessages(messageList);
        messagesPackage.setType("message");
        messagesPackage.setTimestamp(System.currentTimeMillis());
    }

    @Test
    void encode() {
        System.out.println("编码前: " + messagesPackage.toString());

        // 编码 messagesPackage 得到字节数组 encode
        byte[] encode = PackageCodec.encode(messagesPackage);
        System.out.println("protobuf编码后： "+encode);

        // 将字节数组进行 Base64 编码
        String base64String = Base64.getEncoder().encodeToString(encode);

        System.out.println("Base64 编码后: " + base64String);
    }

    @Test
    void decode() throws InvalidProtocolBufferException {
        byte[] encoded = PackageCodec.encode(messagesPackage);
        System.out.println("解码前："+encoded);

        System.out.println("解码后"+PackageCodec.decode(encoded));

    }
}