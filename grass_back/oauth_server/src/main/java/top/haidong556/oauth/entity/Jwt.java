package top.haidong556.oauth.entity;

import lombok.Data;

public class Jwt {
    private String jwt;
    public Jwt(){}
    public Jwt(String jwt){
        this.jwt=jwt;
    }
    @Override
    public String toString() {
        return jwt;
    }

}
