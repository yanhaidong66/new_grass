package top.haidong556.chat_server.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;
import top.haidong556.chat_server.common.codec.MessageCodec;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private long messageId;
    private long messageConversationId;
    private long messageCreateTime;
    private long messageSenderId;
    private int messageSenderChatServerId;
    private long messageReceiverId;
    private int messageReceiverChatServerId;
    private String MessageContent;
    private boolean messageRead;

    // Convert object to JSON string
    public String toJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // Convert JSON string to object
    public static Message fromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Message.class);
    }

    public byte[] encode(){
        return MessageCodec.encode(this);
    }
    public static Message decode(byte[] m){
        try {
            return MessageCodec.decode(m);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }


}
