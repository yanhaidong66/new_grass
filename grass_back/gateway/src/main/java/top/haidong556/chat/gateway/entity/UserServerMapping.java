package top.haidong556.chat.gateway.entity;

import lombok.Data;

@Data
public class UserServerMapping {
    private long userId;
    private String userName;
    private int serverId;
}
