package top.haidong556.chat_server.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.chat_server.entity.Message;

class MessageCodecTest {
    private static Message message;
    @BeforeAll
    static void init(){
        message=new Message(2,2,3,3,3,34,43,"34",true);
    }


    @Test
    void encode() {
        System.out.println("编码前："+message);
        System.out.println("编码后："+MessageCodec.encode(message));

    }

    @Test
    void decode() throws InvalidProtocolBufferException {
        byte[] bytes=MessageCodec.encode(message);
        System.out.println("解码前："+bytes);
        System.out.println("解码后："+MessageCodec.decode(bytes));

    }
}