package top.haidong556.oauth.security.key;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import top.haidong556.oauth.entity.Jwt;
import top.haidong556.oauth.entity.MyUser;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;

/**
 * 工具类
 * 可以创建jwtToken
 * 可以获得当前jwt的RSA公钥
 * 可以验证jwtToken的合法性
 * 可以获得jwtToken的信息
 */
@PropertySource("classpath:key-config.properties")
public class JwtFactory {
    private static final String ALGORITHM="RSA256";
    private static final String TYPE="JWT";
    private static final String ISSUER="grass_oauth";

    @Value("${client-key-expired-millis}")
    private static long EXPIRED=100000;
    private static final  JwtKeyProvider keyProvider= JwtKeyProvider.getInstance();

    public static Jwt createJwt(MyUser user,String requester, String authority){
        HashMap<String, Object> head=new HashMap<>();
        head.put("type",TYPE);
        head.put("algorithm",ALGORITHM);

        HashMap<String,String> payload=new HashMap<>();
        long currentTimeMillis = System.currentTimeMillis();
        long expiredAt=currentTimeMillis+EXPIRED;

        payload.put("audience",requester);
        payload.put("userId",String.valueOf(user.getId()));
        payload.put("userName",user.getUserName());
        payload.put("email",user.getEmail());
        payload.put("authority",authority);
        payload.put("issuer",ISSUER);
        payload.put("issuedAt",String.valueOf(currentTimeMillis));
        payload.put("expiresAt",String.valueOf(expiredAt) );

        JWTCreator.Builder jwtBuilder=JWT.create();
        String jwt=jwtBuilder
                .withHeader(head)
                .withPayload(payload)
                .sign(Algorithm.RSA256(keyProvider.getNowPublicKey(), keyProvider.getNowPrivateKey()));

        return new Jwt(jwt);

    }
    public static PublicKey getNowPublicKey(){
        return keyProvider.getNowPublicKey();
    }
    public static String getNowPublicKeyJson(){return keyProvider.getNowPublicKeyJson();}
    public static String getPrePublicKeyJson(){return keyProvider.getPrePublicKeyJson();}
    public static String getNowPublicKeyProperties(){return keyProvider.getNowPublicKeyProperties();}
    public static String getPrePublicKeyProperties(){return keyProvider.getPrePublicKeyProperties();}



    /**
     * 验证token  合法性
     */
    public static boolean verify(String token) {
        DecodedJWT nowKeyDecodedJwt = null;
        DecodedJWT preKeyDecodedJwt = null;
        Algorithm nowAlgorithm = Algorithm.RSA256(keyProvider.getNowPublicKey(), null);
        Algorithm preAlgorithm = Algorithm.RSA256(keyProvider.getPrePublicKey(), null);
        try {
            nowKeyDecodedJwt = JWT.require(nowAlgorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e1) {
            System.out.println("使用当前keyPair验证失败");
            try {
                preKeyDecodedJwt = JWT.require(preAlgorithm)
                        .build()
                        .verify(token);
                return true;
            } catch (JWTVerificationException e2) {
                System.out.println("使用当前的和上一个的keyPair都验证失败");
                return false;
            }
        }
    }


    /**
     * 获取token信息方法
     */
    public static String getTokenInfo(Jwt token){
        DecodedJWT nowKeyDecodedJwt = null;
        DecodedJWT preKeyDecodedJwt = null;
        Algorithm nowAlgorithm = Algorithm.RSA256(keyProvider.getNowPublicKey(), null);
        Algorithm preAlgorithm = Algorithm.RSA256(keyProvider.getPrePublicKey(), null);
        try {
            nowKeyDecodedJwt = JWT.require(nowAlgorithm)
                    .build()
                    .verify(token.toString());
            return nowKeyDecodedJwt.getClaims().toString();
        } catch (JWTVerificationException e1) {
            System.out.println("使用当前keyPair验证失败");
            try {
                preKeyDecodedJwt = JWT.require(preAlgorithm)
                        .build()
                        .verify(token.toString());
                return preKeyDecodedJwt.getClaims().toString();
            } catch (JWTVerificationException e2) {
                System.out.println("使用当前的和上一个的keyPair都验证失败");
                return "token验证失败";
            }
        }

    }
    public static void updateJwt(){
        keyProvider.updateKeyPair();
    }

}
