package top.haidong556.oauth.security.key;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import top.haidong556.oauth.entity.Jwt;
import top.haidong556.oauth.entity.MyUser;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;

class JwtFactoryTest {
    private static MyUser user;
    private static Jwt jwt;
    @BeforeAll
    static void init(){
        user=new MyUser("haidong","32","dsaf@qq",System.currentTimeMillis(),System.currentTimeMillis());
    }

    @BeforeEach
    void createJwt(){
        jwt = JwtFactory.createJwt(user,"chat_server","all");
    }
    @Test
    void testCreateJwt() {
        LinkedList<GrantedAuthority> l=new LinkedList<>();
        l.add(new SimpleGrantedAuthority("read"));
        l.add(new SimpleGrantedAuthority("write"));

        Jwt jwt = JwtFactory.createJwt(user,"chat_server","all");
        System.out.println(jwt);
        System.out.println(JwtFactory.getTokenInfo(jwt));
    }
    @Test
    void testGetPublicKey() {
        while(true){

            PublicKey key=JwtFactory.getNowPublicKey();
            System.out.println(key.toString());
            System.out.println("------------");
            System.out.println(JwtFactory.getNowPublicKeyJson());
        }
    }

    @Test
    void getPublicKeyJson() {
    }

    @Test
    void getPublicKeyProperties() {
    }

    @Test
    void testVerify() {
        System.out.println(JwtFactory.verify("dskfj"));
        System.out.println(JwtFactory.verify(jwt.toString()));
    }

    @Test
    void testGetTokenInfo() {
        System.out.println(JwtFactory.getTokenInfo(jwt));
    }

    @Test
    void updateJwt() {
    }
}