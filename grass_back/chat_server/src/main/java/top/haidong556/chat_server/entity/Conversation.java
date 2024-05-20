package top.haidong556.chat_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Conversation {
    private long conversationId;
    private long conversationSenderId;
    private long conversationReceiverId;
    private long conversationCreateTime;
    private long conversationModifyTime;

    public Conversation(){}

    public Conversation(long conversationId, long conversationSenderId, long conversationReceiverId, long conversationCreateTime, long conversationModifyTime) {
        this.conversationId = conversationId;
        this.conversationSenderId = conversationSenderId;
        this.conversationReceiverId = conversationReceiverId;
        this.conversationCreateTime = conversationCreateTime;
        this.conversationModifyTime = conversationModifyTime;
    }

    public Conversation(long conversationSenderId, long conversationReceiverId, long conversationCreateTime, long conversationModifyTime) {
        this.conversationSenderId = conversationSenderId;
        this.conversationReceiverId = conversationReceiverId;
        this.conversationCreateTime = conversationCreateTime;
        this.conversationModifyTime = conversationModifyTime;
    }
}
