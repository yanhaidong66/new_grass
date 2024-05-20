package top.haidong556.chat.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import top.haidong556.chat.gateway.entity.JwtUserMessage;
import top.haidong556.chat_server.common.exception.chat.gateway.TokenAuthenticationException;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@Component
@RefreshScope
public class Authenticator {
    @Value("${nowPublicKey.modulus:-1}")
    private static BigInteger nowModulus;
    @Value("${nowPublicKey.exponent:-1}")
    private static BigInteger nowExponent;
    @Value("${nowPublicKey.algorithm:}")
    private static String nowAlgorithm;
    @Value("${nowPublicKey.expired:-1}")
    private static long nowExpired;
    @Value("${nowPublicKey.createTime:-1}")
    private static long nowCreateTime;
    @Value("${nowPublicKey.format:}")
    private static String nowFormat;
    @Value("${nowPublicKey.issuer:")
    private static String nowIssuer;

    @Value("${prePublicKey.modulus:-1}")
    private static BigInteger preModulus;
    @Value("${prePublicKey.exponent:-1}")
    private static BigInteger preExponent;
    @Value("${prePublicKey.algorithm:}")
    private static String preAlgorithm;
    @Value("${prePublicKey.expired:-1}")
    private static long preExpired;
    @Value("${prePublicKey.createTime:-1}")
    private static long preCreateTime;
    @Value("${prePublicKey.format:}")
    private static String preFormat;
    @Value("${prePublicKey.issuer:")
    private static String preIssuer;
    private static KeyFactory keyFactory;
    static {
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param authorization authorization字段
     * @return JwtUserMessage
     */
    public static JwtUserMessage verifyAndGetMessage(String authorization) {
        String[] authorizationParts = authorization.split("\\s+");

        if (authorization == null) {
            throw new TokenAuthenticationException(TokenAuthenticationException.TokenAuthenticationExceptionType.TOKEN_AUTHORIZATION_NULL);
        }
        if (!"Bearer".equals(authorizationParts[0])) {
            throw new TokenAuthenticationException(TokenAuthenticationException.TokenAuthenticationExceptionType.TOKEN_AUTHORIZATION_TYPE_NOT_BEARER);
        }
        if (authorizationParts.length < 2) {
            throw new TokenAuthenticationException(TokenAuthenticationException.TokenAuthenticationExceptionType.TOKEN_AUTHORIZATION_FORMAT_WRONG);
        }
        String token=authorizationParts[1];

        JwtUserMessage user = new JwtUserMessage();
        RSAPublicKeySpec nowPublicKeySpec = new RSAPublicKeySpec(nowModulus, nowExponent);
        RSAPublicKeySpec prePublicKeySpec = new RSAPublicKeySpec(preModulus, preExponent);
        RSAPublicKey prePublicKey = null;
        RSAPublicKey nowPublicKey = null;
        DecodedJWT nowKeyDecodedJwt = null;
        DecodedJWT preKeyDecodedJwt = null;

        try {
            nowPublicKey = (RSAPublicKey) keyFactory.generatePublic(nowPublicKeySpec);
            prePublicKey = (RSAPublicKey) keyFactory.generatePublic(prePublicKeySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }


        try {
            nowKeyDecodedJwt = JWT.require(Algorithm.RSA256(nowPublicKey, null))
                    .build()
                    .verify(token);

            JwtUserMessage jwtUserMessage = loadJwtUserMessage(nowKeyDecodedJwt, user);
            return jwtUserMessage;
        } catch (JWTVerificationException e1) {
            System.out.println("使用当前keyPair验证失败");
            try {
                preKeyDecodedJwt = JWT.require(Algorithm.RSA256(prePublicKey, null))
                        .build()
                        .verify(token);
                return loadJwtUserMessage(preKeyDecodedJwt, user);
            } catch (JWTVerificationException e2) {
                System.out.println("使用当前的和上一个的keyPair都验证失败");
            }
            return user;
        }
    }
    /**
     * 将解码后的jwt信息填充到jwtUserMessage中
     *
     * @param decodedJWT
     * @param user
     * @return JwtUserMessage
     */
    private static JwtUserMessage loadJwtUserMessage(DecodedJWT decodedJWT, JwtUserMessage user) {


        String userName = decodedJWT.getClaim("userName").asString();
        if(userName!=null)
            user.setUserName(userName);
        String audience = decodedJWT.getClaim("audience").asString();
        if (audience != null)
            user.setAudience(audience);

        String userId = decodedJWT.getClaim("userId").asString();
        if (userId != null)
            user.setUserId(Long.parseLong(userId));

        String issuer = decodedJWT.getClaim("issuer").asString();
        if (issuer != null)
            user.setIssuer(issuer);

        String email = decodedJWT.getClaim("email").asString();
        if (email != null)
            user.setEmail(email);

        String authority = decodedJWT.getClaim("authority").asString();
        if (authority != null)
            user.setAuthorities(authority.split(","));

        String issuedAt = decodedJWT.getClaim("issuedAt").asString();
        if (issuedAt != null)
            user.setIssuedAt(Long.parseLong(issuedAt));

        String expiresAt = decodedJWT.getClaim("expiresAt").asString();
        if (expiresAt != null)
            user.setExpiresAt(Long.parseLong(expiresAt));

        return user;
    }

}
