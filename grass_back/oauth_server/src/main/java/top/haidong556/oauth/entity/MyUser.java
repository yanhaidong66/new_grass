package top.haidong556.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
@Data
public class MyUser {
    private long id;
    private String userName;
    private String password;
    private String email;
    private String authorities;
    private long createTime;
    private long modifiedTime;

    public MyUser() {}

    public MyUser(String userName, String password, String email, String authorities, long createTime, long modifiedTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public MyUser(String userName, String password, String email, long createTime, long modifiedTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public MyUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
        long current=System.currentTimeMillis();
        this.createTime = current;
        this.modifiedTime = createTime;
    }

    public MyUser(long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public MyUser(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


}
