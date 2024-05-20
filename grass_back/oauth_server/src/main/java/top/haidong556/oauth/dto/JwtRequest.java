package top.haidong556.oauth.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String userName;
    private String email;
    private String passWord;
    private String requester;
    private String authorities;
}
