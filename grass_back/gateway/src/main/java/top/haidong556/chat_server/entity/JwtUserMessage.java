package top.haidong556.chat_server.entity;

import lombok.Data;

@Data
public class JwtUserMessage {
    private String audience;
    private long userId;
    private String userName;
    private String email;
    private String[] authorities;
    private String issuer;
    private long issuedAt;
    private long expiresAt;
    public String toJson(){

    }
}
