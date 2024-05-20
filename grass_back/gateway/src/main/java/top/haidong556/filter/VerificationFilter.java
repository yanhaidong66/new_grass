package top.haidong556.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.haidong556.chat_server.common.exception.chat.gateway.TokenAuthenticationException;
import top.haidong556.chat_server.entity.JwtUserMessage;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@Component
@RefreshScope
public class VerificationFilter
        implements GatewayFilter, Ordered {
    @Value("${nowPublicKey.modulus:-1}")
    private BigInteger nowModulus;
    @Value("${nowPublicKey.exponent:-1}")
    private BigInteger nowExponent;
    @Value("${nowPublicKey.algorithm:}")
    private String nowAlgorithm;
    @Value("${nowPublicKey.expired:-1}")
    private long nowExpired;
    @Value("${nowPublicKey.createTime:-1}")
    private long nowCreateTime;
    @Value("${nowPublicKey.format:}")
    private String nowFormat;
    @Value("${nowPublicKey.issuer:")
    private String nowIssuer;

    @Value("${prePublicKey.modulus:-1}")
    private BigInteger preModulus;
    @Value("${prePublicKey.exponent:-1}")
    private BigInteger preExponent;
    @Value("${prePublicKey.algorithm:}")
    private String preAlgorithm;
    @Value("${prePublicKey.expired:-1}")
    private long preExpired;
    @Value("${prePublicKey.createTime:-1}")
    private long preCreateTime;
    @Value("${prePublicKey.format:}")
    private String preFormat;
    @Value("${prePublicKey.issuer:")
    private String preIssuer;
    private static KeyFactory keyFactory;

    static {
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
            throws TokenAuthenticationException {

        //return chain.filter(exchange);
        String token = getToken(exchange);
        JwtUserMessage jwtUserMessage = verifyAndGetMessage(token);
        System.out.println("---------------"+jwtUserMessage);
        //将请求体换成jwtusermessage

        return chain.filter(exchange);
//        Mono<String> body=readRequestBody(exchange);
//        String jwt = ".."; // 从请求体中获取 JWT
//        if (verify(jwt)) {
//            // 验证成功，继续处理请求
//            return chain.filter(exchange);
//        } else {
//            // 验证失败，返回错误响应
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
    }

    private String getToken(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
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

        return authorizationParts[1];
    }


    @Override
    public int getOrder() {
        return 1;
    }

    public JwtUserMessage verifyAndGetMessage(String token) {

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
     * @return void
     */
    private JwtUserMessage loadJwtUserMessage(DecodedJWT decodedJWT, JwtUserMessage user) {


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

    public static Mono<String> readRequestBody(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Flux<DataBuffer> body = request.getBody();

        // 使用 DataBufferUtils.join 将 Flux<DataBuffer> 中的数据合并为一个 DataBuffer
        Mono<DataBuffer> join = DataBufferUtils.join(body);

        // 使用 DataBufferUtils.release 来释放 DataBuffer 资源
        return join.flatMap(buffer -> {
            try {
                // 从 DataBuffer 中读取字节数组并转换为字符串
                byte[] bytes = new byte[buffer.readableByteCount()];
                buffer.read(bytes);
                String bodyString = new String(bytes, StandardCharsets.UTF_8);

                return Mono.just(bodyString);
            } finally {
                // 释放 DataBuffer 资源
                DataBufferUtils.release(buffer);
            }
        });
    }
}

