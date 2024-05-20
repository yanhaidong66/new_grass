package top.haidong556.chat_server.entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Data;
import top.haidong556.chat_server.common.codec.MessageCodec;
import top.haidong556.chat_server.common.codec.PackageCodec;

import java.util.List;
@Data
public class MessagesPackage {
    private List<Message> messages;
    private int messageNum;
    private long timestamp;
    private String type;

    public MessagesPackage(){}


    public String toString() {
        ObjectMapper mapper=new ObjectMapper();
        String json=null;
        try {
            json= mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
    public static MessagesPackage fromJson(String json){
        ObjectMapper mapper=new ObjectMapper();
        MessagesPackage messagesPackage=null;
        try {
             messagesPackage= mapper.readValue(json, MessagesPackage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return messagesPackage;
    }

    public byte[] encode(){
        return PackageCodec.encode(this);
    }
    public static MessagesPackage decode(byte[] m){
        try {
            return PackageCodec.decode(m);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }


    public MessagesPackage(List<Message> messages, int messageNum, long timestamp, String type) {
        this.messages = messages;
        this.messageNum = messageNum;
        this.timestamp = timestamp;
        this.type = type;
    }


}
