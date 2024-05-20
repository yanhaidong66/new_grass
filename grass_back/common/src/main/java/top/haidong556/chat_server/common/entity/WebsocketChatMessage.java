package top.haidong556.chat_server.common.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class WebsocketChatMessage {
    private String type;
    private String authorization;
    private long senderId;
    private String senderName;
    private long receiverId;
    private String receiverName;
    private String content;
    private long timestamp;
    private boolean haveVerify=false;

    public WebsocketChatMessage(){}

    public WebsocketChatMessage(String type, String authorization, long senderId, String senderName, long receiverId, String receiverName, String content, long timestamp) {
        this.type=type;
        this.authorization=authorization;
        this.senderId=senderId;
        this.senderName=senderName;
        this.receiverId=receiverId;
        this.receiverName=receiverName;
        this.content=content;
        this.timestamp=timestamp;
    }

    public static WebsocketChatMessage fromJson(String jsonUser) {
        JSONObject json = JSONObject.parseObject(jsonUser);

        String type = json.getString("type");
        String authorization = json.getString("authorization");
        JSONObject senderJson = json.getJSONObject("sender");
        long senderId = senderJson.getLongValue("id");
        String senderName = senderJson.getString("name");
        JSONObject recipientJson = json.getJSONObject("recipient");
        long receiverId = recipientJson.getLongValue("id");
        String receiverName = recipientJson.getString("name");
        String content = json.getString("content");
        long timestamp = json.getLongValue("timestamp");

        return new WebsocketChatMessage(type, authorization, senderId, senderName, receiverId, receiverName, content, timestamp);
    }
}
