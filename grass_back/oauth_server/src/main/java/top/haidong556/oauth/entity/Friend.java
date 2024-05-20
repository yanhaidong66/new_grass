package top.haidong556.oauth.entity;

import lombok.Data;

@Data
public class Friend {
    private long userId;
    private long friendId;
    private String friendName;
}
