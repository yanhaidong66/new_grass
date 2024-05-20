package top.haidong556.chat.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.haidong556.chat.gateway.entity.JwtUserMessage;
import top.haidong556.chat.gateway.util.Authenticator;
import top.haidong556.chat_server.common.entity.WebsocketChatMessage;

import java.math.BigInteger;
import java.security.KeyFactory;

@Component
@RefreshScope
public class WebsocketVerificationFilter implements GatewayFilter, Ordered {
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 从 ServerWebExchange 中获取请求体（WebSocket 消息的有效负载）
        String jsonMessage = exchange.getRequest().getBody().toString();

        // 将 JSON 格式的 WebSocket 消息转换为 WebsocketChatMessage 对象
        WebsocketChatMessage message = WebsocketChatMessage.fromJson(jsonMessage);
        String authorization = message.getAuthorization();
        JwtUserMessage jwtUserMessage = Authenticator.verifyAndGetMessage(authorization);
        if(!message.getSenderName().equals(jwtUserMessage.getUserName())||message.getSenderId()!= jwtUserMessage.getUserId()){
            throw new RuntimeException("user information in token don't equals sender information");
        }

        // 将 WebsocketChatMessage 对象加载到 ServerWebExchange 中
        exchange.getAttributes().put("jwtUserMessage", jwtUserMessage);

        // 调用后续的过滤器链
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 999;
    }
}
