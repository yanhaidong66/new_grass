package top.haidong556.chat.gateway.filter;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.haidong556.chat.gateway.entity.JwtUserMessage;
import top.haidong556.chat.gateway.util.Authenticator;
import top.haidong556.chat_server.common.exception.chat.gateway.TokenAuthenticationException;

/**
 * @when 除了登录的消息外，其余消息都需要使用
 * @description 传来的消息中包含Authorization字段，这个过滤器将会使用nacos的公钥验证字段的有效性，如果有效，把userId和userName添加到请求头的字段中
 */
@Component
@RefreshScope
public class HttpVerificationFilter
        implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws TokenAuthenticationException {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");

        JwtUserMessage jwtUserMessage = Authenticator.verifyAndGetMessage(authorization);

        exchange.getAttributes().put("jwtUserMessage", jwtUserMessage);
        // 使用修改后的交换对象继续过滤器链
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 999;
    }

}

