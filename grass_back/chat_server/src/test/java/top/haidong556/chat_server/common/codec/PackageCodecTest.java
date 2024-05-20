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
        messagesPackage.setType("new message");
        messagesPackage.setTimestamp(System.currentTimeMillis());
    }

    @Test
    void encode() {
        System.out.println("编码前:"+messagesPackage.toString());
        // 编码 messagesPackage 得到字节数组 encode
        byte[] encode = PackageCodec.encode(messagesPackage);

// 获取 encode 的长度
        int originalLength = encode.length;

// 创建新的字节数组，大小为 encode.length + 4（假设长度字段为 int，占用 4 个字节）
        byte[] newBytes = new byte[originalLength + 2];

// 在新的字节数组的前面添加表示长度的字段
        ByteBuffer buffer = ByteBuffer.wrap(newBytes);
        buffer.putInt(originalLength);

// 将 encode 复制到新的字节数组的后面
        System.arraycopy(encode, 0, newBytes, 2, originalLength);

// 对新的字节数组进行 Base64 编码
        String base64EncodedMessage = Base64.getEncoder().encodeToString(newBytes);

        System.out.println("编码后：" + base64EncodedMessage);

    }

    @Test
    void decode() throws InvalidProtocolBufferException {
        byte[] encoded = PackageCodec.encode(messagesPackage);
        System.out.println("解码前："+encoded);

        System.out.println("解码后"+PackageCodec.decode(encoded));

    }
}